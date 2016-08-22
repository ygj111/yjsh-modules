package com.hhh.workflow.controller;

import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.WorkItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hhh.fund.util.FundPage;
import com.hhh.security.util.ShiroUtils;
import com.hhh.workflow.mode.HistoryOrderBean;
import com.hhh.workflow.mode.SnakerHelper;
import com.hhh.workflow.service.EngineService;

@RestController
@RequestMapping(value = "/admin/flow/")
public class WorkflowController {
	
	@Autowired
	private EngineService engine;

	/**
	 * 流程定义
	 * @param pageNo
	 * @param displayName
	 * @return
	 */
	@RequestMapping(value = "list/{pageno}/pagesize/{pagesize}", method = RequestMethod.GET)
	public FundPage<Process> processList(@PathVariable Integer pageno, @PathVariable Integer pagesize,
			String displayname) {
		QueryFilter filter = new QueryFilter();
		if (StringHelper.isNotEmpty(displayname)) {
			filter.setDisplayName(displayname);
		}
		Page<Process> p = engine.getEngine().process().getProcesss(filter, new PageRequest(pageno, pagesize));
		return new FundPage<Process>(p.getTotalPages(), p.getTotalElements(), p.getContent());
	}

	/**
	 * 发布流程
	 * @param snakerFile 流程定义文件
	 * @param id 流程ID
	 * @return
	 */
	@RequestMapping(value = "deploy", method = RequestMethod.POST)
	public String processDeploy(@RequestParam(value = "snakerFile") MultipartFile snakerFile, String id) {
		InputStream input = null;
		try {
			input = snakerFile.getInputStream();
			if (StringUtils.isNotEmpty(id)) {
				engine.getEngine().process().redeploy(id, input);
			} else {
				engine.getEngine().process().deploy(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "true";
	}

	/**
	 * 流程编辑
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String processEdit(String id) {
		Process process = engine.getEngine().process().getProcessById(id);
		String content = "";
		if (process.getContent() != null) {
			try {
				content = StringHelper.textXML(new String(process.getContent(), "UTF-8"));
				System.out.println(content);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 根据流程定义ID，删除流程定义
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public boolean processDelete(@PathVariable("id") String id) {
		engine.getEngine().process().undeploy(id);
		return true;
	}

	/**
	 * 保存流程定义[web流程设计器]
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deployXml", method = RequestMethod.POST)
	public boolean deployXml(String model, String id) {
		InputStream input = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n"
					+ SnakerHelper.convertXml(model);
			input = StreamHelper.getStreamFromString(xml);
			if (StringUtils.isNotEmpty(id)) {
				engine.getEngine().process().redeploy(id, input);
			} else {
				engine.getEngine().process().deploy(input);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	/**
	 * 流程实例
	 * @param pageno
	 * @param pagesize
	 * @param pname 流程定义名称
	 * @return
	 */
	@RequestMapping(value = "/orders/{pageno}/pagesize/{pagesize}", method = RequestMethod.GET)
	public FundPage<HistoryOrderBean> order(@PathVariable Integer pageno, @PathVariable Integer pagesize,
			String pname) {
		QueryFilter filter = new QueryFilter();
		FundPage<HistoryOrderBean> page = engine.getEngine().query().getHistoryOrders(filter, new PageRequest(pageno, pagesize));
		return page;
	}
	
}
