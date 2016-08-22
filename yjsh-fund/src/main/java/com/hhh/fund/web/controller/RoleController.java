package com.hhh.fund.web.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.ResourcesType;
import com.hhh.fund.usercenter.State;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.FundPage;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.PermissionBean;
import com.hhh.fund.web.model.RoleBean;


@RestController
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 保存角色
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public int saveRole(RoleBean role, HttpSession session){
		role.setCustomerId(StringUtil.getCustomerId(session));
		ucs.saveRole(role);
		return 1;
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public int delRole(@PathVariable String id){
		ucs.deleteRoleById(id);
		return 1;
	}
	
	@RequestMapping(value="/id/{id}",method=RequestMethod.GET)
	public RoleBean getRole(@PathVariable String id){
		return ucs.findRleById(id);
	}
	
	/**
	 * 查询角色
	 * @return
	 */
	@RequestMapping(value="/list/{pageno}/pagesize/{pagesize}", method=RequestMethod.GET)
	public FundPage<RoleBean> listRoles(@PathVariable Integer pageno, @PathVariable Integer pagesize, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		FundPage<RoleBean> page = ucs.findRoleAll(customerId, new PageRequest(pageno, pagesize));
		return page;
	}
	/**
	 * 保存角色的权限
	 * @param roleId
	 * @param resIds
	 * @param menuIds
	 * @return
	 */
	@RequestMapping(value="/permission", method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public boolean permission(String roleId, String resIds, String menuIds){
		ucs.deletePermissionByRoleId(roleId);
		List<PermissionBean> list = new ArrayList<>();
		if(resIds != null && resIds.length() != 0){
			String ids[] =  resIds.split(",");
			for(String str : ids){
				PermissionBean bean = new PermissionBean();
				bean.setEnable(State.Enable);
				bean.setResId(str);
				bean.setResType(ResourcesType.Resources);
				bean.setRoleId(roleId);
				bean.setEnable(State.Enable);
				list.add(bean);
			}
		}
		if(menuIds != null && menuIds.length() != 0){
			String ids[] =  menuIds.split(",");
			Set<String> set = new HashSet<>();
			for(String str : ids){
				if(!"0".equals(str)){
					setPermission(roleId, list, str, State.Enable);
					//处理被选中菜单的父菜单
					MenuBean menu = ucs.findMeunById(str);
					String path = menu.getPath();
					if(path != null){
						String[] pIds = path.split("/");
						for(String pid : pIds){
							if(menuIds.indexOf(pid) == -1)
								set.add(pid);
						}
					}
					if(menu.isChild()){//处理子菜单,界面是懒加载，选择父节点，子节点自动毛选择
						List<String> submenus = getSubMenu(menu.getCustomerId(), menu.getId());
						for(String resId : submenus){
							if(menuIds.indexOf(resId) == -1)
								setPermission(roleId, list, resId, State.Enable);
						}
					}
				}
			}
			for(String str : set){
				setPermission(roleId, list, str, State.Locked);
			}
		}
		ucs.savePermission(list);
		return true;
	}

	private void setPermission(String roleId, List<PermissionBean> list, String resId, State state) {
		PermissionBean bean = new PermissionBean();
		bean.setResId(resId);
		bean.setResType(ResourcesType.Menu);
		bean.setRoleId(roleId);
		bean.setEnable(state);
		list.add(bean);
	}
	
	/**
	 * 取菜单的所有子菜单
	 * @param menuId
	 * @return
	 */
	private List<String> getSubMenu(String customerId, String menuId){
		List<String> list = new ArrayList<>();
		List<MenuBean> mbs = ucs.findMenuByParentId(customerId, menuId);
		for(MenuBean bean : mbs){
			list.add(bean.getId());
			if(bean.isChild()){
				list.addAll(getSubMenu(bean.getCustomerId(), bean.getId()));
			}
		}
		return list;
	}
	
	/**
	 * 获取角色的权限
	 * @param roleid
	 * @return
	 */
	@RequestMapping(value="/getpermission/{roleid}", method=RequestMethod.GET)
	public Map<String, Set<String>> getPermission(@PathVariable String roleid){
		Set<String> resIds = new HashSet<>(), menuIds = new HashSet<>();
		Map<String, Set<String>> map = new HashMap<>();
		List<PermissionBean> list = ucs.findPermissionByRoleId(roleid);
		if(list != null && !list.isEmpty()){
			for(PermissionBean bean : list){
				if(bean.getEnable().ordinal() == State.Locked.ordinal()){
					continue;
				}
				if(bean.getResType().ordinal() == ResourcesType.Menu.ordinal()){
					menuIds.add(bean.getResId());
				}else if(bean.getResType().ordinal() == ResourcesType.Resources.ordinal()){
					resIds.add(bean.getResId());
				}
			}
		}
		map.put("resIds", resIds);
		map.put("menuIds", menuIds);
		return map;
	}
}
