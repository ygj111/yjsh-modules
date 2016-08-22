package com.hhh.fund.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.hhh.fund.web.model.DisplayField;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.RoleBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.security.util.ShiroUtils;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	
	private final static String USER_MENU="system_user_menu";
	
	@Autowired
	private UserCenterService ucs;

	/**
	 * 保存用户
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String saveUser(UserBean user, HttpSession session) {
		if (user.getCustomerId() == null || "".equals(user.getCustomerId())) {
			String customerId = StringUtil.getCustomerId(session);
			if (customerId == null || "".equals(customerId)) {
				return "CustomerId不能为空";
			}
			user.setCustomerId(customerId);
		}
		ucs.saveUser(user);
		return "true";
	}

	/**
	 * 删除用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable String id) {
		ucs.deleteUserById(id);
		return "true";
	}

	/**
	 * 根据username取用户
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/id/{username}", method=RequestMethod.GET)
	public UserBean getUser(@PathVariable String username){
		return ucs.findByUsername(username);
	}
	
	@RequestMapping(value="/inrole/{username}", method=RequestMethod.GET)
	public Set<RoleBean> getUserRole(@PathVariable String username){
		return ucs.getUserRole(username);
	}
	
	/**
	 * 保存用户的角色
	 * @param username
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value="/saverole", method=RequestMethod.POST)
	public String saveUserRole(String username, String roleIds){
		String[] ids = null;
		if(roleIds != null && !"".equals(roleIds)){
			ids = roleIds.split(",");
		}
		ucs.saveRoleToUser(username, ids);
		return "";
	}
	
	/**
	 * 查询用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "list/{pageno}/pagesize/{pagesize}", method = RequestMethod.POST)
	public FundPage<UserBean> list(@PathVariable Integer pageno, @PathVariable Integer pagesize, UserBean bean,
			HttpSession session) {
		if (bean == null) {
			bean = new UserBean();
		}
		bean.setCustomerId(StringUtil.getCustomerId(session));
		FundPage<UserBean> page = ucs.findUserAll(bean, new PageRequest(pageno, pagesize));
		return page;
	}

	/**
	 * 返回用户的最基本信息（登录名和显示名）
	 * @param pageno
	 * @param pagesize
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/listbase/{pageno}/pagesize/{pagesize}", method = RequestMethod.GET)
	public FundPage<DisplayField> listbase(@PathVariable Integer pageno, @PathVariable Integer pagesize,
			HttpSession session) {
		
		UserBean bean = new UserBean();
		bean.setCustomerId(StringUtil.getCustomerId(session));
		FundPage<UserBean> page = ucs.findUserAll(bean, new PageRequest(pageno, pagesize));
		List<DisplayField> list = new ArrayList<>();
		if(page.getContent() != null){
			for(UserBean b : page.getContent()){
				DisplayField df = new DisplayField();
				df.setId(b.getUserId());
				if(b.getDisplayName() == null || "".equals(b.getDisplayName())){
					df.setName(b.getUsername());
				}else{
					df.setName(b.getDisplayName());
				}
				list.add(df);
			}
		}
		return new FundPage<DisplayField>(page.getTotalPages(), page.getTotalElements(), list);
	}
	
	/**
	 * 锁/解锁用户
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "lock/{username}", method = RequestMethod.GET)
	public String lockedUser(@PathVariable String username) {
		UserBean bean = ucs.findByUsername(username);
		if (bean.isLocked())
			ucs.unlockingUser(username);
		else
			ucs.lockUser(username);
		return "true";
	}
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value="existusername", method=RequestMethod.POST)
	public boolean existUsername(String username){
		UserBean bean = ucs.findByUsername(username);
		if(bean == null)
			return true;
		else
			return false;
	}
	
	/**
	 * 取登录用户的菜单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/usermenu", method=RequestMethod.GET)
	public List<MenuBean> getUserMenu(HttpSession session){
		Object menu = session.getAttribute(USER_MENU);
		List<MenuBean> list = new ArrayList<>();
		if(menu == null){
			String customerId = StringUtil.getCustomerId(session);
			Set<String> roles = new HashSet<>();
			roles.addAll(ShiroUtils.getRoles());
			
			list = ucs.findUserMenuAll(customerId, roles);
			session.setAttribute(USER_MENU, list);
			return list;
		}else{
			return (List<MenuBean>)menu;
		}
		
	}
}
