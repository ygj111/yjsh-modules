package com.hhh.fund.ui.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the ui_test database table.
 * 
 */
@Entity
@Table(name="ui_test")
@NamedQuery(name="UiTest.findAll", query="SELECT u FROM UiTest u")
public class UiTest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
	private String id;

	private String name;

	private int num;

	private String realname;

	public UiTest() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}