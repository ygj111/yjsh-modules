package com.hhh.fund.ui.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hhh.fund.ui.dao.UiTestDao;
import com.hhh.fund.ui.entity.UiTest;
import com.hhh.fund.web.model.UiTestBean;

@Service
@Transactional
public class TestUIServiceImpl {
	@Autowired
	private UiTestDao dao;
	
	public UiTestBean findUiTest(String id){
		UiTest uitest = dao.findById(id);
		UiTestBean uibean = new UiTestBean();
		BeanUtils.copyProperties(uitest, uibean);
		return uibean;
	}
	public void save(UiTestBean bean){
		UiTest ui = new UiTest();
		System.out.println(bean.getId()+","+bean.getName()+","+bean.getRealname()+","+bean.getNum());
		BeanUtils.copyProperties(bean, ui);
		System.out.println(ui.getId()+"serv");
		dao.save(ui);
	}
	/**
	 * 获取分页数据
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(propagation =Propagation.NOT_SUPPORTED)
	public List<UiTestBean> getAllApp(int pageNum, int pageSize) {
		PageRequest pageReq = new PageRequest(pageNum - 1, pageSize);
		System.out.println("1111");
		Page<UiTest> ssApps = dao.findAll(pageReq);
		List<UiTest> list= ssApps.getContent();
		List<UiTestBean> beanList = new ArrayList<UiTestBean>();
		for(UiTest ui:list){
			UiTestBean uiBean = new UiTestBean();
			System.out.println(ui.getNum()+"hehe");
			BeanUtils.copyProperties(ui, uiBean);
			System.out.println(uiBean.getNum()+"bean");
			beanList.add(uiBean);
		}
		UiTest uu= list.get(0);
		
		System.out.println(list.size()+"!!!"+uu.getNum());
			return beanList;
	}
	
	public String testObject(Object obj){
		UiTestBean ui =(UiTestBean)obj;
		String id = ui.getName();
		UiTest uiTest = new UiTest();
		BeanUtils.copyProperties(ui, uiTest);
		dao.save(uiTest);
		return id;
	}
}
