package com.hhh.fund.web.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.ResGroupBean;

@RestController
@RequestMapping("/admin/resGroup")
public class ResGroupController {
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 保存资源组
	 * @param resg
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public int saveResGroup(ResGroupBean resg, HttpSession session){
		resg.setCustomerId(StringUtil.getCustomerId(session));
		ucs.saveResGroup(resg);
		return 1;
	}
	
	/**
	 * 删除资源组
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delResGroup(@PathVariable String id){
		ucs.deleteResGroupById(id);
		return 1;
	}
	
	/**
	 * 查找资源组
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}",method=RequestMethod.POST)
	public FundPage<ResGroupBean> listResGroup(@PathVariable Integer pageno, @PathVariable Integer pagesize, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		FundPage<ResGroupBean> page = ucs.findResGroupAll(customerId, new PageRequest(pageno, pagesize));
		return page;
	}
	
	
	/**
	 * 根据id查找资源组
	 * @return
	 */
	@RequestMapping(value="findResGroupById")
	public @ResponseBody ResGroupBean findResGroupById(String id){
		return ucs.findResGroupById(id);
	}
}
