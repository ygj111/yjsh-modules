package com.hhh.workflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.entity.WorkItem;
import org.snaker.engine.model.ProcessModel;
import org.snaker.engine.model.TaskModel;
import org.snaker.engine.model.TaskModel.TaskType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.util.FundPage;
import com.hhh.security.util.ShiroUtils;
import com.hhh.workflow.mode.HistoryTaskBean;
import com.hhh.workflow.mode.TaskBean;
import com.hhh.workflow.mode.TaskModelBean;
import com.hhh.workflow.service.EngineService;

@RestController
@RequestMapping(value = "/workflow")
public class UserFlowController {

	public static final String PARA_PROCESSID = "processId";
	public static final String PARA_ORDERID = "orderId";
	public static final String PARA_TASKID = "taskId";
	public static final String PARA_TASKNAME = "taskName";
	public static final String PARA_METHOD = "method";
	public static final String PARA_NEXTOPERATOR = "nextOperator";
	public static final String PARA_NODENAME = "nodeName";
	public static final String PARA_CCOPERATOR = "ccOperator";

	@Autowired
	private EngineService engine;
	
	/**
	 * 当前登录用户的已办任务
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/history/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public FundPage<WorkItem> historyTaskList(@PathVariable Integer pageno, @PathVariable Integer pagesize) {
		Page<WorkItem> page = engine.getEngine().query().getHistoryWorkItems(new QueryFilter().setOperator(ShiroUtils.getUsername()), new PageRequest(pageno, pagesize));
		return new FundPage<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
	}
	
	/**
	 * 待办任务
	 * @return
	 */
	@RequestMapping(value = "/todolist", method=RequestMethod.GET)
	public Map<String,Object> homeTaskList() {
		String[] assignees = new String []{ShiroUtils.getUsername()};
		
		Page<WorkItem> majorWorks = engine.getEngine().query().getWorkItems(new QueryFilter()
				.setOperators(assignees)
				.setTaskType(TaskType.Major.ordinal()), new PageRequest(0, 13));
		Page<WorkItem> aidantWorks = engine.getEngine().query()
				.getWorkItems(new QueryFilter()
				.setOperators(assignees)
				.setTaskType(TaskType.Aidant.ordinal()), new PageRequest(0, 8));
		Page<HistoryOrder> ccWorks = engine.getEngine().query()
				.getCCWorks(new QueryFilter()
				.setOperators(assignees)
				.setState(1), new PageRequest(0, 8));
		Map<String, Object> map = new HashMap<>();
		map.put("majorWorks", new FundPage<WorkItem>(majorWorks.getTotalPages(), majorWorks.getTotalElements(), majorWorks.getContent()));
		map.put("aidantWorks", new FundPage<WorkItem>(aidantWorks.getTotalPages(), aidantWorks.getTotalElements(), aidantWorks.getContent()));
		map.put("ccWorks", new FundPage<HistoryOrder>(ccWorks.getTotalPages(), ccWorks.getTotalElements(), ccWorks.getContent()));
		return map;
	}
	
	/**
     * 抄送实例已读, 刷新待办事务列表
     * @param id
     * @param url
     * @return
     */
    @RequestMapping(value = "/ccread", method=RequestMethod.GET)
    public String ccread(String id) {
    	String[] assignees = new String []{ShiroUtils.getUsername()};
        engine.getEngine().order().updateCCStatus(id, assignees);
        return "true";
    }
    
    /**
     * 通用流程的执行
     * @param request
     * @return
     */
    @RequestMapping(value = "/process", method=RequestMethod.POST)
    public String process(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<String> paraNames = request.getParameterNames();
        while (paraNames.hasMoreElements()) {
            String element = paraNames.nextElement();
            int index = element.indexOf("_");
            String paraValue = request.getParameter(element);
            if(index == -1) {
                params.put(element, paraValue);
            } else {
                char type = element.charAt(0);
                String name = element.substring(index + 1);
                Object value = null;
                switch(type) {
                    case 'S':
                        value = paraValue;
                        break;
                    case 'I':
                        value = ConvertUtils.convert(paraValue, Integer.class);
                        break;
                    case 'L':
                        value = ConvertUtils.convert(paraValue, Long.class);
                        break;
                    case 'B':
                        value = ConvertUtils.convert(paraValue, Boolean.class);
                        break;
                    case 'D':
                        value = ConvertUtils.convert(paraValue, Date.class);
                        break;
                    case 'N':
                        value = ConvertUtils.convert(paraValue, Double.class);
                        break;
                    default:
                        value = paraValue;
                        break;
                }
                params.put(name, value);
            }
        }
        String processId = request.getParameter(PARA_PROCESSID);
        String orderId = request.getParameter(PARA_ORDERID);
        String taskId = request.getParameter(PARA_TASKID);
        String nextOperator = request.getParameter(PARA_NEXTOPERATOR);
        if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
            engine.startAndExecute(processId, ShiroUtils.getUsername(), params);
        } else {
            String methodStr = request.getParameter(PARA_METHOD);
            int method;
            try {
                method = Integer.parseInt(methodStr);
            } catch(Exception e) {
                method = 0;
            }
            switch(method) {
                case 0://任务执行
                	engine.execute(taskId, ShiroUtils.getUsername(), params);
                    break;
                case -1://驳回、任意跳转
                	engine.executeAndJump(taskId, ShiroUtils.getUsername(), params, request.getParameter(PARA_NODENAME));
                    break;
                case 1://转办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                    	engine.transferMajor(taskId, ShiroUtils.getUsername(), nextOperator.split(","));
                    }
                    break;
                case 2://协办
                    if(StringUtils.isNotEmpty(nextOperator)) {
                    	engine.transferAidant(taskId, ShiroUtils.getUsername(), nextOperator.split(","));
                    }
                    break;
                default:
                	engine.execute(taskId, ShiroUtils.getUsername(), params);
                    break;
            }
        }
        String ccOperator = request.getParameter(PARA_CCOPERATOR);
        if(StringUtils.isNotEmpty(ccOperator)) {
        	engine.getEngine().order().createCCOrder(orderId, ShiroUtils.getUsername(), ccOperator.split(","));
        }
        return "true";
    }
    
    /**
     * 根据名称启动一个流程
     * @param model
     * @param processname 流程名称
     * @return
     */
    @RequestMapping(value="/start/{processname}", method=RequestMethod.GET)
    public Map<String, Object> startProcess(@PathVariable String processname){
    	Process process = engine.getEngine().process().getProcessByName(processname);
    	Map<String, Object> map = new HashMap<>();
    	List<TaskModel> models = process.getModel().getModels(TaskModel.class);
        Map<String, String> viewModel = new HashMap<>();
        TaskModel model = models.get(0);
        viewModel.put("name", model.getName());
        viewModel.put("displayName", model.getDisplayName());
        viewModel.put("form", model.getForm());
    	map.put("node", viewModel);
    	map.put("processId", process.getId());
    	return map;
    }
    
    /**
     * 通用的流程展现页面入口
     */
    @RequestMapping(value = "/commflowdisplay", method=RequestMethod.GET)
    public Map<String, Object> all(String processId, String orderId, String taskId) {
    	Process process = null;
    	Map<String, Object> model = new HashMap<>();
    	model.put("processId", processId);
		model.put("orderId", orderId);
		model.put("taskId", taskId);
        if(StringUtils.isNotEmpty(processId)) {
        	process = engine.getEngine().process().getProcessById(processId);
        	
        }
        if(StringUtils.isNotEmpty(orderId)) {
            List<HistoryTask> historyTasks = engine.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
            List<HistoryTaskBean> hlist = new ArrayList<>();
            for(HistoryTask ht : historyTasks){
            	HistoryTaskBean htbean = new HistoryTaskBean();
            	BeanUtils.copyProperties(ht, htbean);
            	hlist.add(htbean);
            }
            model.put("histtask", hlist);//历史任务
        }
        if(StringUtils.isNotEmpty(taskId)) {
        	Task task = engine.getEngine().query().getTask(taskId);
        	TaskBean tb = new TaskBean();
        	BeanUtils.copyProperties(task, tb);
            model.put("task", tb);
            List<TaskModel> list = process.getModel().getTaskModels();
            for(TaskModel m : list){
            	if(m.getName().equals(task.getTaskName())){
            		TaskModelBean node = new TaskModelBean();
            		BeanUtils.copyProperties(m, node);
            		model.put("node", node);
            		break;
            	}
            }
        }
		
        return model;
    }
	
}
