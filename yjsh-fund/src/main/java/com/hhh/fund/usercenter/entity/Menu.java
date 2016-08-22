package com.hhh.fund.usercenter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.hhh.fund.usercenter.Display;
import com.hhh.fund.usercenter.Whether;

/**
 * 菜单
 * @author 3hhjj
 *
 */

@Entity
@Table(name="sys_ucenter_menu")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4639470684692204752L;

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@Column(length=32)
	private String id;
	
	/**
	 * 企业ID
	 */
	@Column(length=32)
	private String customerId;
	
	/**
	 * 菜单名称
	 */
	private String name;
	
	/**
	 * 菜单的链接
	 */
	@Column(name="menu_url")
	private String url;
	
	/**
	 * 菜单所在的层级
	 */
	@Column(name="menu_level")
	private int level;
	
	/**
	 * 上级菜单ID
	 */
	@Column(length=32)
	private String parentId;
	
	/**
	 * 分组
	 */
	@Column(name="menu_group")
	private int group;
	
	/**
	 * 菜单显示顺序
	 */
	@Column(name="menu_order")
	private int order;
	
	/**
	 * 菜单路径
	 */
	private String path;
	
	/**
	 * 是否显示
	 */
	@Enumerated(EnumType.ORDINAL)
	@Column(name="menu_display")
	private Display display;

	@Enumerated(EnumType.ORDINAL)
	private Whether hasChild;
	
	@Column(name="menu_icon")
	private String icon;
	
	@Column(name="menu_style")
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

	public void setCustomerId(String orgId) {
		this.customerId = orgId;
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

	public Display getDisplay() {
		return display;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Whether getHasChild() {
		return hasChild;
	}

	public void setHasChild(Whether hasChild) {
		this.hasChild = hasChild;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
