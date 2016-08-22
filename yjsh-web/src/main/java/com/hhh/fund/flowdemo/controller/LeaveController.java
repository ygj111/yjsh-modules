package com.hhh.fund.flowdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.Process;
import org.snaker.engine.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.web.model.DepartmentBean;
import com.hhh.fund.web.model.DisplayField;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;
import com.hhh.workflow.mode.HistoryOrderBean;
import com.hhh.workflow.service.EngineService;


/**
 * 请假流程Controller
 * 流程特点:多级审批流程、decision节点的表达式写法->${day > 2 ? 'transition5' : 'transition4'}
 * 业务数据:
 *     所有节点的业务数据均以json格式保存在order、task表的variable字段中
 *     建议业务数据以独立的表保存，通过orderId来关联
 */
@RestController
@RequestMapping(value = "/demo/leave")
public class LeaveController {
	@Autowired
	private EngineService engine;
	
	@Autowired
	private UserCenterService userService;
	/**
	 * 请假申请路由方法
	 */
	@RequestMapping(value = "apply", method= RequestMethod.GET)
	public Map<String, Object> apply(String processId, String orderId, String taskId, String taskName) {
		//将请求参数继续传递给视图页面
		Map<String, Object> model = new HashMap<>();
		model.put("processId", processId);
		model.put("orderId", orderId);
		model.put("taskId", taskId);
		Process process = engine.getEngine().process().getProcessById(processId);
		//设置操作人为当前登录用户，请假流程演示时，将申请人、部门经理审批人、总经理审批人都设置为当前用户
		//可通过修改申请页面的部门经理、总经理输入框来改变下一步的处理人
		DisplayField field = new DisplayField(ShiroUtils.getUsername(), ShiroUtils.getFullname());
		model.put("operator", field);
		
		model.put("manager", getApproveUser(process, field.getId(), "approveDept"));
		return model;
	}
	
	/**
	 * 部门经理审批路由方法
	 */
	@RequestMapping(value = "approveDept", method= RequestMethod.GET)
	public  Map<String, Object> approveDept(String processId, String orderId, String taskId, String taskName) {
		Map<String, Object> model = new HashMap<>();
		model.put("processId", processId);
		model.put("orderId", orderId);
		model.put("taskId", taskId);
		Process process = engine.getEngine().process().getProcessById(processId);
		
		model.put("manager", getApproveUser(process, ShiroUtils.getUsername(), "approveBoss"));
		return model;
	}
	
	private List<DisplayField> getApproveUser(Process process, String userName, String taskName){
		//根据taskId是否为空来标识当前请求的页面是否为活动任务的节点页面
		List<TaskModel> tasks = process.getModel().getTaskModels();
		String roleId = "";
		for(TaskModel task : tasks){
			if(taskName.equals(task.getName())){
				roleId = task.getAssignee();
				break;
			}
		}
		List<DepartmentBean> departs = userService.findDepartByUsername(userName);
		List<DisplayField> list = new ArrayList<>();
		if(departs != null && !departs.isEmpty()){
			DepartmentBean depart = departs.get(0);
			List<UserBean> users = userService.getUserByDepartIdAndRoleId(depart.getId(), roleId);
			while(users == null || users.isEmpty()){
				if("0".equals(depart.getParentId()))
					break;
				depart = userService.findDepartById(depart.getParentId());
				users = userService.getUserByDepartIdAndRoleId(depart.getId(), roleId);
			}
			if(users != null){
				for(UserBean bean : users){
					list.add(new DisplayField(bean.getUsername(), bean.getDisplayName()));
				}
			}
		}
		return list;
	}
	
	/**
	 * 总经理审批路由方法
	 */
	@RequestMapping(value = "approveBoss", method= RequestMethod.GET)
	public Map<String, Object> approveBoss(String processId, String orderId, String taskId, String taskName) {
		Map<String, Object> model = new HashMap<>();
		model.put("processId", processId);
		model.put("orderId", orderId);
		model.put("taskId", taskId);
		return model;
	}
	
	/**
	 * 请假流程列实例列表
	 * @param pageno
	 * @param pagesize
	 * @param pname
	 * @return
	 */
	@RequestMapping(value = "/list/{pageno}/pagesize/{pagesize}/{pname}", method = RequestMethod.GET)
	public FundPage<HistoryOrderBean> order(@PathVariable Integer pageno, @PathVariable Integer pagesize,
			@PathVariable String pname) {
		QueryFilter filter = new QueryFilter();
		filter.setOperator(ShiroUtils.getUsername());
		if(pname != null && !"".equals(pname))
			filter.setName(pname);
		FundPage< HistoryOrderBean> page = engine.getEngine().query().getHistoryOrders(filter, new PageRequest(pageno, pagesize));
		return page;
	}
}
