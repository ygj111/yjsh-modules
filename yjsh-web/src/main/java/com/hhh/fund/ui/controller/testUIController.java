package com.hhh.fund.ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhh.fund.ui.entity.UiTest;
import com.hhh.fund.ui.service.TestUIServiceImpl;
import com.hhh.fund.usercenter.service.UserCenterService;
import com.hhh.fund.web.model.UiTestBean;
import com.hhh.fund.web.model.UserBean;
import com.hhh.util.pgoffice.PageWord;
import com.hhh.util.platform.common.ui.UIComponent;
import com.hhh.util.platform.common.ui.parse.UIComponentParser;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordreader.WordDocument;



@Controller
@RequestMapping(value="/ui/test")
public class testUIController {
	private static final String PAGE_SIZE = "15";
	
	@Autowired
	private UserCenterService ucs;
	
	@Autowired
	private TestUIServiceImpl uiService;
	
	@RequestMapping(value="/main")
	public String adminMain(){
		return "/ui/uiTest";
	} 
	@RequestMapping(value="save1")
	public void save1(String bean,String entity){
		//System.out.println((String)bean.get("num")+","+entity);
		Object o = null;
		System.out.println("bean"+bean);
/*		bean = bean.replaceAll("&", ",");
		bean = bean.replaceAll("=", ":");*/
		System.out.println(bean);
	    try {
	    	//通过jackson将json对象转成javaBean实体
	    	ObjectMapper objectMapper=new ObjectMapper();
	        Object uiBean = objectMapper.readValue(bean, Class.forName(entity));
	        String hehe = uiService.testObject(uiBean);
	        System.out.println(hehe);
	        System.out.println(uiBean);

	    } catch (JsonParseException e) {

	        e.printStackTrace();

	    } catch (JsonMappingException e) {

	        e.printStackTrace();

	    } catch (IOException e) {

	        e.printStackTrace();

	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="save")
	public void save(String inputName,String inputValue){
		System.out.println("hahaha" );
		//uiService.saveEntity(inputName,inputValue);
		if(inputName != null && !inputName.equals("")){
			String[] inputnames = inputName.split("\\$\\$");
			String[] inputvalues = inputValue.split("\\$\\$");
			String[] entitys = inputnames[0].split("\\.");
			String entity = entitys[0];
			Class cla = null;
			Object obj = new Object();
			try {
				cla = Class.forName("com.hhh.fund.web.model."+entity);
				 obj = cla.newInstance();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			for(int i=0;i<inputnames.length;i++){//切割每个input框
				String name = inputnames[i];
				String value = inputvalues[i];
				if(value == null || value.equals("undefined")){
					value="";
				}
				String[] names = name.split("\\.");//切割出类名和类中属性
				//entity = names[0];//类名
				String field = names[1];//属性名
				String fieldByMethod = field.substring(0, 1).toUpperCase() + field.substring(1);
				try {
					
					Method[] methods = cla.getDeclaredMethods();
					System.out.println(entity+"entity");
					System.out.println(field+"field");
					System.out.println(value+"value");
					
					//cla.getMethod(name, parameterTypes)
					//拿到所有的属性
			        Field fieldClass = cla.getDeclaredField(field);
			        Class type = fieldClass.getType();//属性的类型
			        System.out.println(type.getName().equals("int")+"567");
			        //if(type.getName())
			        fieldClass.setAccessible(true);
			        if(type.getName().equals("int")){//当属性为int型
			        	if(!value.equals("") && value != null){//不为空，将String转int
			        		Method method = obj.getClass().getMethod("set" + fieldByMethod, type);
			        		int val = Integer.parseInt(value);
			        		method.invoke(obj, val);
			        		/*int val = Integer.parseInt(value);
			        		fieldClass.set(obj, val);*/
			        	}
			        }else{
			        	Method method = obj.getClass().getMethod("set" + fieldByMethod, type);
			        	String value1= value;
			            method.invoke(obj, value1);
			        	/*fieldClass.set(obj, value);
			        	 System.out.println(fieldClass.get(obj));*/
			        }
			       
			        //getter(obj,"Sex");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			 //自己保存的方法
	        System.out.println(obj);
	        UiTestBean ui = (UiTestBean)obj;
	        String id = ui.getId();
	        System.out.println(id+"id");
	        uiService.save(ui);
		}
		//ucs.saveUser(userbean);
	}
    
	@RequestMapping(value="createUI")
	@ResponseBody
	public Map createUI(){
		try{
			String a = "$U_CHECKBOX_ON$";
			System.out.println("$U_CHECKBOX_ON$".equals(a));
			InputStream xmlis = getClass().getClassLoader().getResourceAsStream("com/hhh/fund/text1.xml");
			UIComponentParser parser = new UIComponentParser();
			UIComponent uiComponent = parser.parse(xmlis);
			System.out.println("parse");
			HashMap<String,HashMap<String,Object>> specialValue = uiComponent.getSpecialValue();
			HashMap<String, Object> bb = uiComponent.getBindedControls();
			StringBuilder builder = new StringBuilder();
			
			String hehe = uiComponent.toHTML(builder);
			
			System.out.println("haha"+hehe); 
			Map map = new HashMap();
			map.put("str", hehe);
			return map;
		}catch(Exception e) {
			System.out.println("error");
			return null;
		}
	}
	@RequestMapping(value="getAllList")
	@ResponseBody
	public List<UiTestBean> getAllList(@RequestParam(value = "page", defaultValue = "1") int pageNum,
			@RequestParam(value = "pagesize", defaultValue = PAGE_SIZE) int pageSize,Model model){
		List<UiTestBean> pagelist = uiService.getAllApp(pageNum, pageSize);
		
		
		return pagelist	;
		
	}
	
	@RequestMapping(value="findUiTestById")
	@ResponseBody
	public UiTestBean findUiTestById(String id){
		UiTestBean ui = uiService.findUiTest(id);
		return ui;
	}
	@RequestMapping(value="word")
	@ResponseBody
	public void word(HttpServletRequest request, 
			HttpServletResponse response, Model model){
		PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
		poCtrl.setServerPage(request.getContextPath()+"/poserver.zz");
		
		// 定义WordDocument对象
		WordDocument doc = new WordDocument(request, response);
			
		ArrayList data = (ArrayList)request.getAttribute("tableData");
		//PageWord.fillTableData(doc, "PO_table1", 1, 2, data);
		
		poCtrl.setWriter(doc);
		
		poCtrl.setSaveFilePage("save_doc.jsp");
		
		//poCtrl.webOpen(request.getContextPath() + "/doc/table_test.docx", OpenModeType.docNormalEdit, "zhongl");
		poCtrl.setTagId("PageOfficeCtrl1");
	}
}
