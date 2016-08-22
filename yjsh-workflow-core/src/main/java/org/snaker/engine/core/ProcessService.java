/* Copyright 2013-2015 www.snakerflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snaker.engine.core;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.engine.IProcessService;
import org.snaker.engine.SnakerDBService;
import org.snaker.engine.SnakerException;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.cache.Cache;
import org.snaker.engine.cache.CacheManager;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Process;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.helper.DateHelper;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.ProcessModel;
import org.snaker.engine.parser.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.fund.util.FundPage;

/**
 * 流程定义业务类
 * 
 * @author yuqs
 * @since 1.0
 */
@Service("processService")
@Transactional
public class ProcessService extends AccessService implements IProcessService/*, CacheManagerAware*/ {
	private static final Logger log = LoggerFactory.getLogger(ProcessService.class);
	private static final String DEFAULT_SEPARATOR = ".";

	@Autowired
	private SnakerDBService snakerDb;
	
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 流程定义对象cache名称
	 */
	private static final String CACHE_ENTITY = "snaker.process.entity";
	/**
	 * 流程id、name的cache名称
	 */
	private static final String CACHE_NAME = "snaker.process.name";

	
	/**
	 * 实体cache(key=name,value=entity对象)
	 */
	private Cache<String, Process> entityCache;
	/**
	 * 名称cache(key=id,value=name对象)
	 */
	private Cache<String, String> nameCache;
	
	@Transactional(readOnly=true)
	public void check(Process process, String idOrName) {
		AssertHelper.notNull(process, "指定的流程定义[id/name=" + idOrName + "]不存在");
		if (process.getState() != null && process.getState() == 0) {
			throw new IllegalArgumentException(
					"指定的流程定义[id/name=" + idOrName + ",version=" + process.getVersion() + "]为非活动状态");
		}
	}

	/**
	 * 保存process实体对象
	 */
	public void saveProcess(Process process) {
		snakerDb.saveProcess(process);// access().saveProcess(process);
	}

	/**
	 * 更新process的类别
	 */
	public void updateType(String id, String type) {
		Process entity = getProcessById(id);
		entity.setType(type);
		snakerDb.updateProcessType(id, type);// access().updateProcessType(id, type);
		cache(entity);
	}

	/**
	 * 根据id获取process对象 先通过cache获取，如果返回空，就从数据库读取并put
	 */
	@Transactional(readOnly=true)
	public Process getProcessById(String id) {
		AssertHelper.notEmpty(id);
		Process entity = null;
		String processName;
		Cache<String, String> nameCache = ensureAvailableNameCache();
		Cache<String, Process> entityCache = ensureAvailableEntityCache();
		if (nameCache != null && entityCache != null) {
			processName = nameCache.get(id);
			if (StringHelper.isNotEmpty(processName)) {
				entity = entityCache.get(processName);
			}
		}
		if (entity != null) {
			if (log.isDebugEnabled()) {
				log.debug("obtain process[id={}] from cache.", id);
			}
			return entity;
		}
		entity = snakerDb.getProcess(id);// access().getProcess(id);
		if (entity != null) {
			if (log.isDebugEnabled()) {
				log.debug("obtain process[id={}] from database.", id);
			}
			cache(entity);
		}
		return entity;
	}

	/**
	 * 根据name获取process对象 先通过cache获取，如果返回空，就从数据库读取并put
	 */
	@Transactional(readOnly=true)
	public Process getProcessByName(String name) {
		return getProcessByVersion(name, null);
	}

	/**
	 * 根据name获取process对象 先通过cache获取，如果返回空，就从数据库读取并put
	 */
	@Transactional(readOnly=true)
	public Process getProcessByVersion(String name, Integer version) {
		AssertHelper.notEmpty(name);
		if (version == null) {
			version = snakerDb.getLatestProcessVersion(name);// access().getLatestProcessVersion(name);
		}
		if (version == null) {
			version = 0;
		}
		Process entity = null;
		String processName = name + DEFAULT_SEPARATOR + version;
		Cache<String, Process> entityCache = ensureAvailableEntityCache();
		if (entityCache != null) {
			entity = entityCache.get(processName);
		}
		if (entity != null) {
			if (log.isDebugEnabled()) {
				log.debug("obtain process[name={}] from cache.", processName);
			}
			return entity;
		}

		List<Process> processs = snakerDb.getProcesss(new QueryFilter().setName(name).setVersion(version));//access().getProcesss(null, new QueryFilter().setName(name).setVersion(version));
		if (processs != null && !processs.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug("obtain process[name={}] from database.", processName);
			}
			entity = processs.get(0);
			cache(entity);
		}
		return entity;
	}

	/**
	 * 根据流程定义xml的输入流解析为字节数组，保存至数据库中，并且put到缓存中
	 * 
	 * @param input
	 *            定义输入流
	 */
	public String deploy(InputStream input) {
		return deploy(input, null);
	}

	/**
	 * 根据流程定义xml的输入流解析为字节数组，保存至数据库中，并且put到缓存中
	 * 
	 * @param input
	 *            定义输入流
	 * @param creator
	 *            创建人
	 */
	public String deploy(InputStream input, String creator) {
		AssertHelper.notNull(input);
		try {
			byte[] bytes = StreamHelper.readBytes(input);
			ProcessModel model = ModelParser.parse(bytes);
			System.out.println("snakerDb:"+snakerDb);
			Integer version = snakerDb.getLatestProcessVersion(model.getName());//access().getLatestProcessVersion(model.getName());
			Process entity = new Process();
			entity.setId(StringHelper.getPrimaryKey());
			if (version == null || version < 0) {
				entity.setVersion(0);
			} else {
				entity.setVersion(version + 1);
			}
			entity.setState(STATE_ACTIVE);
			entity.setModel(model);
			entity.setContent(bytes);
			entity.setCreateTime(DateHelper.getTime());
			entity.setCreator(creator);
			saveProcess(entity);
			cache(entity);
			return entity.getId();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new SnakerException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 根据流程定义id、xml的输入流解析为字节数组，保存至数据库中，并且重新put到缓存中
	 * 
	 * @param input
	 *            定义输入流
	 */
	public void redeploy(String id, InputStream input) {
		AssertHelper.notNull(input);
		Process entity = snakerDb.getProcess(id);//access().getProcess(id);
		AssertHelper.notNull(entity);
		try {
			byte[] bytes = StreamHelper.readBytes(input);
			ProcessModel model = ModelParser.parse(bytes);
			String oldProcessName = entity.getName();
			entity.setModel(model);
			entity.setContent(bytes);
			snakerDb.updateProcess(entity);//access().updateProcess(entity);
			if (!oldProcessName.equalsIgnoreCase(entity.getName())) {
				Cache<String, Process> entityCache = ensureAvailableEntityCache();
				if (entityCache != null) {
					entityCache.remove(oldProcessName + DEFAULT_SEPARATOR + entity.getVersion());
				}
			}
			cache(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new SnakerException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 根据processId卸载流程
	 */
	public void undeploy(String id) {
		Process entity = snakerDb.getProcess(id);//access().getProcess(id);
		entity.setState(STATE_FINISH);
		snakerDb.updateProcess(entity);//access().updateProcess(entity);
		cache(entity);
	}

	/**
	 * 级联删除指定流程定义的所有数据
	 */
	public void cascadeRemove(String id) {
		Process entity = snakerDb.getProcess(id);//access().getProcess(id);
		List<HistoryOrder> historyOrders = snakerDb.findByProcessId(id);//access().getHistoryOrders(null, new QueryFilter().setProcessId(id));

		for (HistoryOrder historyOrder : historyOrders) {
			ServiceContext.getEngine().order().cascadeRemove(historyOrder.getId());
		}
		snakerDb.deleteProcess(entity);//access().deleteProcess(entity);
		clear(entity);
	}

	/**
	 * 查询流程定义
	 */
	@Transactional(readOnly=true)
	public List<Process> getProcesss(QueryFilter filter) {
		if (filter == null)
			filter = new QueryFilter();
		return snakerDb.getProcesss(filter);//access().getProcesss(null, filter);
	}

	/**
	 * 分页查询流程定义
	 */
	@Transactional(readOnly=true)
	public Page<Process> getProcesss( QueryFilter filter, Pageable page) {
		AssertHelper.notNull(filter);
		return snakerDb.getProcesss(filter, page); //access().getProcesss(page, filter);
	}

	/**
	 * 缓存实体
	 * 
	 * @param entity
	 *            流程定义对象
	 */
	@Transactional(readOnly=true)
	private void cache(Process entity) {
		Cache<String, String> nameCache = ensureAvailableNameCache();
		Cache<String, Process> entityCache = ensureAvailableEntityCache();
		if (entity.getModel() == null && entity.getContent() != null) {
			entity.setModel(ModelParser.parse(entity.getContent()));
		}
		String processName = entity.getName() + DEFAULT_SEPARATOR + entity.getVersion();
		if (nameCache != null && entityCache != null) {
			if (log.isDebugEnabled()) {
				log.debug("cache process id is[{}],name is[{}]", entity.getId(), processName);
			}
			entityCache.put(processName, entity);
			nameCache.put(entity.getId(), processName);
		} else {
			if (log.isDebugEnabled()) {
				log.debug("no cache implementation class");
			}
		}
	}

	/**
	 * 清除实体
	 * 
	 * @param entity
	 *            流程定义对象
	 */
	@Transactional(readOnly=true)
	private void clear(Process entity) {
		Cache<String, String> nameCache = ensureAvailableNameCache();
		Cache<String, Process> entityCache = ensureAvailableEntityCache();
		String processName = entity.getName() + DEFAULT_SEPARATOR + entity.getVersion();
		if (nameCache != null && entityCache != null) {
			nameCache.remove(entity.getId());
			entityCache.remove(processName);
		}
	}
	@Transactional(readOnly=true)
	private Cache<String, Process> ensureAvailableEntityCache() {
		Cache<String, Process> entityCache = ensureEntityCache();
		if (entityCache == null && this.cacheManager != null) {
			entityCache = this.cacheManager.getCache(CACHE_ENTITY);
		}
		return entityCache;
	}
	@Transactional(readOnly=true)
	private Cache<String, String> ensureAvailableNameCache() {
		Cache<String, String> nameCache = ensureNameCache();
		if (nameCache == null && this.cacheManager != null) {
			nameCache = this.cacheManager.getCache(CACHE_NAME);
		}
		return nameCache;
	}
	@Transactional(readOnly=true)
	public Cache<String, Process> ensureEntityCache() {
		return entityCache;
	}
	@Transactional(readOnly=true)
	public void setEntityCache(Cache<String, Process> entityCache) {
		this.entityCache = entityCache;
	}
	@Transactional(readOnly=true)
	public Cache<String, String> ensureNameCache() {
		return nameCache;
	}
	@Transactional(readOnly=true)
	public void setNameCache(Cache<String, String> nameCache) {
		this.nameCache = nameCache;
	}
}
