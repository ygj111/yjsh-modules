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

import java.util.List;


import org.snaker.engine.IManagerService;
import org.snaker.engine.SnakerDBService;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.helper.DateHelper;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理服务类  
 * @author yuqs modify hjj 
 * @since 1.4
 * 
 */
@Service("snakerManagerService")
@Transactional
public class ManagerService extends AccessService implements IManagerService {
	
	@Autowired
	private SnakerDBService snakerDb;
	
	public void saveOrUpdate(Surrogate surrogate) {
		AssertHelper.notNull(surrogate);
		surrogate.setState(STATE_ACTIVE);
		snakerDb.saveSurrogate(surrogate);
	}

	public void deleteSurrogate(String id) {
		Surrogate surrogate = getSurrogate(id);
		AssertHelper.notNull(surrogate);
		snakerDb.deleteSurrogate(surrogate);
	}
	
	@Transactional(readOnly=true)
	public Surrogate getSurrogate(String id) {
		return snakerDb.getSurrogate(id);
	}
	@Transactional(readOnly=true)
	public List<Surrogate> getSurrogate(QueryFilter filter) {
		return snakerDb.getSurrogate(filter);
	}
	@Transactional(readOnly=true)
	public Page<Surrogate> getSurrogate(QueryFilter filter, Pageable pageable) {
		return snakerDb.getSurrogate(filter, pageable);
	}
	@Transactional(readOnly=true)
	public String getSurrogate(String operator, String processName) {
		AssertHelper.notEmpty(operator);
		QueryFilter filter = new QueryFilter().
				setOperator(operator).
				setOperateTime(DateHelper.getTime());
		if(StringHelper.isNotEmpty(processName)) {
			filter.setName(processName);
		}
		
		List<Surrogate> surrogates = getSurrogate(filter);
		if(surrogates == null || surrogates.isEmpty()) return operator;
		StringBuffer buffer = new StringBuffer(50);
		for(Surrogate surrogate : surrogates) {
			String result = getSurrogate(surrogate.getSurrogate(), processName);
			buffer.append(result).append(",");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}
	
}
