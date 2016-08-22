package com.hhh.fund.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.ResourcesBean;

@RestController
@RequestMapping("/admin/resources")
public class ResourcesController {
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 保存资源
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public  int saveResource(ResourcesBean resource, HttpSession session){
		resource.setCustomerId(StringUtil.getCustomerId(session));
		ucs.saveResources(resource);
		return 1;
	}
	
	/**
	 * 批量删除资源
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delResources(@PathVariable String id){
			ucs.deleteResourcesById(id);
		return 1;
	}
	
	/**
	 * 查找资源
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public FundPage<ResourcesBean> listResources(@PathVariable Integer pageno, @PathVariable Integer pagesize, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		FundPage<ResourcesBean> page = ucs.findResourcesAll(customerId, new PageRequest(pageno, pagesize));
		return page;
	}
	
	/**
	 * 根据id查找资源
	 * @param id
	 */
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET)
	public ResourcesBean findResourcesById(@PathVariable String id){
		return ucs.findResourcesById(id);
	}
}
