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
import com.hhh.fund.web.model.DictBean;

@RestController
@RequestMapping("/admin/dictonary")
public class DictonaryController{
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 保存数据字典
	 * @param dict
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public int saveDict(DictBean dict, HttpSession session){
		dict.setCustomerId(StringUtil.getCustomerId(session));
		ucs.saveDict(dict);
		return 1;
	}
	
	/**
	 * 删除数据字典
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delDict(@PathVariable Integer id){
		ucs.deleteDict(id);
		return 1;
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET)
	public DictBean getDict(@PathVariable Integer id){
		return ucs.findDictById(id);
	}
	
	/**
	 * 查询数据字典
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}")
	public FundPage<DictBean> listDicts(@PathVariable Integer pageno, @PathVariable Integer pagesize, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		FundPage<DictBean> page = ucs.findDictAll(customerId, new PageRequest(pageno, pagesize));
		return page;
	}
}
