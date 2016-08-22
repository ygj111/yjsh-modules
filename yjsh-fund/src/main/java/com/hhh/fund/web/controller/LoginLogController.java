package com.hhh.fund.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.LoginLogBean;

@RestController
@RequestMapping("/admin/loginlog")
public class LoginLogController {
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 根据时间删除登录日志
	 * @param time
	 * @return
	 */
	@RequestMapping(value="/delete")
	public int delLoginLogByTime(String time){
		ucs.deleteLoginLogByTime(StringUtil.parstDate(time));
		return 1;
	}
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delete(@PathVariable String id){
		ucs.deleteLoginLogById(id);
		return 1;
	}
	
	/**
	 * 查询指定用户的登录日志
	 * @param username
	 * @param top 取最近的条数
	 * @return
	 */
	@RequestMapping(value="/username/{username}",method=RequestMethod.GET)
	public List<LoginLogBean> findLoginLogByUsername(@PathVariable String username, Integer top){
		if(top == null)
			top = 10;
		return ucs.findLoginLogByUsername(username, top);
	}
	
	/**
	 * 查询所有登录日志
	 * @param username
	 * @param top
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}",method=RequestMethod.POST)
	public FundPage<LoginLogBean> listLoginLog(@PathVariable Integer pageno, @PathVariable Integer pagesize, 
			String stime, String etime, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		Date sdate = null, edate = null;
		if(stime != null && !"".equals(stime)){
			sdate = StringUtil.parstDate(stime+" 00:00:00");
		}
		if(etime != null && !"".equals(etime)){
			edate = StringUtil.parstDate(etime+" 23:59:59");
		}
		FundPage<LoginLogBean> page = ucs.findLoginLogAll(customerId, sdate, edate, new PageRequest(pageno, pagesize));
		return page;
	}
	
}
