package com.hhh.workflow.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.SnakerEngine;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.model.ProcessModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.workflow.mode.SnakerHelper;
import com.hhh.workflow.service.EngineService;


@Controller
@RequestMapping(value = "/admin/flow")
public class ProcessController {
	
	@Autowired
	private EngineService engine;
	
	@RequestMapping(value = "/designer", method = RequestMethod.GET)
	public String processDesigner(String processId, Model model) {
		if (null != processId && !"".equals(processId)) {
			Process process = engine.getEngine().process().getProcessById(processId);
			AssertHelper.notNull(process);
			ProcessModel processModel = process.getModel();
			if (processModel != null) {
				String json = SnakerHelper.getModelJson(processModel);
				model.addAttribute("process", json);
			}
			model.addAttribute("processId", processId);
		}
		return "flow/processDesigner";
	}
	
	@ResponseBody
	@RequestMapping(value = "/processtojson", method=RequestMethod.GET)
	public Object json(String processId, String orderId) {
        Process process = engine.getEngine().process().getProcessById(processId);
        AssertHelper.notNull(process);
        ProcessModel model = process.getModel();
        Map<String, String> jsonMap = new HashMap<String, String>();
        if(model != null) {
            jsonMap.put("process", SnakerHelper.getModelJson(model));
        }

		if(StringUtils.isNotEmpty(orderId)) {
			List<Task> tasks = engine.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
			List<HistoryTask> historyTasks = engine.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
			jsonMap.put("state", SnakerHelper.getStateJson(model, tasks, historyTasks));
		}
		return jsonMap;
	}
	
	@RequestMapping(value = "/display", method=RequestMethod.GET)
	public String display(Model model, String processId, String orderId) {
        model.addAttribute("processId", processId);
        
		HistoryOrder order = engine.getEngine().query().getHistOrder(orderId);
		model.addAttribute("order", order);
		
		List<HistoryTask> tasks = engine.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
		model.addAttribute("tasks", tasks);
		return "flow/processView";
	}
}
