package com.hhh.fund.flowdemo.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.LoginLogBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {
	@Autowired
	private UserCenterService uconterService;
	
	/**
	 * 跳转到登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "admin_login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String adminLogin(UserBean user, HttpSession session, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		token.setRememberMe(user.isRememberMe());
		try {
			subject.login(token);
			user = ShiroUtils.getUser();
			session.setAttribute(StringUtil.CUSTOMER_ID, user.getCustomerId());
			LoginLogBean log = new LoginLogBean();
			log.setCustomerId(user.getCustomerId());
			log.setIp(StringUtil.getIpAddress(request));
			log.setLoginName(user.getUsername());
			log.setLoginTime(StringUtil.dateFormat(new Date()));
			uconterService.saveLoginLog(log);
			return "redirect:/admin/main";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/admin/login";
		}
	}
	
	
	@RequestMapping(value = "/keyToLogin", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String keyToLogin(String key, HttpSession session, HttpServletRequest request) {
		if(key == null || key.equals("")){
			return "redirect:/admin/login";
		}
		System.out.println(key+"key");
		UserBean user = uconterService.findUserByKeyId(key);
		if(user == null){//未找见匹配key的用户
			return "redirect:/admin/login?code=1";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getKeyId()+";;;key");
		token.setRememberMe(user.isRememberMe()) ;
		try {
			subject.login(token);
			user = ShiroUtils.getUser();
			session.setAttribute(StringUtil.CUSTOMER_ID, user.getCustomerId());
			LoginLogBean log = new LoginLogBean();
			log.setCustomerId(user.getCustomerId());
			log.setIp(StringUtil.getIpAddress(request));
			log.setLoginName(user.getUsername());
			log.setLoginTime(StringUtil.dateFormat(new Date()));
			uconterService.saveLoginLog(log);
			return "redirect:/admin/main";
		}catch (AuthenticationException e) {
			e.printStackTrace();
			token.clear();
			return "redirect:/admin/login";
		}
	}
}
