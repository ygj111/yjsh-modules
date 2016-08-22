package com.hhh.fund.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.util.StringUtil;
import com.hhh.fund.web.model.MenuBean;
import com.hhh.fund.web.model.TreeNode;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {
	@Autowired
	private UserCenterService ucs;
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public TreeNode saveMenu(MenuBean menu, HttpSession session){
		menu.setCustomerId(StringUtil.getCustomerId(session));
		String id = ucs.saveMenu(menu).getId();
		TreeNode node = new TreeNode();
		node.setId(id);
		node.setChildren(false);
		node.setParent(menu.getParentId());
		node.setText(menu.getName());
		return node;
	}
	
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public int delMenu(@PathVariable String id, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		MenuBean bean = ucs.findMeunById(id);
		ucs.deleteMenuById(id);
		MenuBean pbean = ucs.findMeunById(bean.getParentId());
		if(pbean != null){
			List<MenuBean> sublist = ucs.findMenuByParentId(customerId, bean.getParentId());
			if(sublist == null || sublist.isEmpty()){
				pbean.setChild(Whether.No);
				ucs.saveMenu(pbean);
			}
		}
		return 1;
	}
	
	@RequestMapping(value="/id/{id}", method=RequestMethod.GET)
	public MenuBean getMenu(@PathVariable String id){
		return ucs.findMeunById(id);
	}
	
	/**
	 * 取下一级菜单
	 * @param customerId
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/parent", method=RequestMethod.GET)
	public List<TreeNode> findMenuByParentId(String id, HttpSession session){
		String customerId = StringUtil.getCustomerId(session);
		List<TreeNode> list = new ArrayList<>();
		if(id == null || "".equals(id) || "#".equals(id)){
			TreeNode node = new TreeNode();
			node.setId("0");
			node.setText("菜单");
			node.setParent("#");
			list.add(node);
			List<MenuBean> sublist =  ucs.findMenuByParentId(customerId, "0");
			if(sublist != null && !sublist.isEmpty()){
				for(MenuBean mb : sublist){
					node = new TreeNode();
					node.setId(mb.getId());
					node.setText(mb.getName());
					node.setParent(mb.getParentId());
					node.setChildren(mb.isChild());
					list.add(node);
				}
			}
		}else{
			List<MenuBean> sublist =  ucs.findMenuByParentId(customerId, id);
			if(sublist != null && !sublist.isEmpty()){
				for(MenuBean mb : sublist){
					TreeNode node = new TreeNode();
					node.setId(mb.getId());
					node.setText(mb.getName());
					node.setParent(mb.getParentId());
					node.setChildren(mb.isChild());
					list.add(node);
				}
			}
		}
		
		return list;
	}
}
