package com.hhh.fund.web.model;

import java.io.Serializable;
import java.util.List;

import com.hhh.fund.usercenter.Display;
import com.hhh.fund.usercenter.Whether;
import com.hhh.fund.usercenter.entity.Menu;

public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164178485934086090L;
	
	private String id;
	
	/**
	 * 企业ID
	 */
	private String customerId;
	
	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 菜单的链接
	 */
	private String url;
	
	/**
	 * 上级菜单ID
	 */
	private String parentId;
	
	/**
	 * 分组
	 */
	private int group;
	
	/**
	 * 菜单路径
	 */
	private String path;
	
	/**
	 * 菜单显示顺序
	 */
	private int order;
	
	/**
	 * 是否显示
	 */
	private boolean display;
	/**
	 * 是否有下级
	 */
	private boolean child;
	
	/**
	 * 子菜单
	 */
	private List<MenuBean> subMenu;

	private String icon;
	
	private String style;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display ==  Display.Show ? true : false;
	}

	public boolean isChild() {
		return child;
	}

	public void setChild(Whether w) {
		this.child = w == Whether.Yes ? true: false;
	}

	public void Converter(Menu menu){
		this.setCustomerId(menu.getCustomerId());
		this.setDisplay(menu.getDisplay());
		this.setGroup(menu.getGroup());
		this.setChild(menu.getHasChild());
		this.setId(menu.getId());
		this.setName(menu.getName());
		this.setOrder(menu.getOrder());
		this.setParentId(menu.getParentId());
		this.setUrl(menu.getUrl());
		this.setPath(menu.getPath());
		this.setIcon(menu.getIcon());
		this.setStyle(menu.getStyle());
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public void setChild(boolean child) {
		this.child = child;
	}

	public List<MenuBean> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<MenuBean> subMenu) {
		this.subMenu = subMenu;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
