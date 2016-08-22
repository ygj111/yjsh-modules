package com.hhh.sample.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhuozhengsoft.pageoffice.FileSaver;

@Controller
@RequestMapping(value = "/samples")
public class PageOfficeController {
	
	@RequestMapping(value = "fillTableData", method = RequestMethod.GET)
	public String fillTableDataInDoc(Model model) {
		ArrayList data = new ArrayList();
		
		String[] rowData = new String[3];
		rowData[0] = "张三";
		rowData[1] = "39";
		rowData[2] = "男";
		data.add(rowData);
		
		rowData = new String[3];
		rowData[0] = "李四";
		rowData[1] = "29";
		rowData[2] = "男";
		data.add(rowData);
		
		rowData = new String[3];
		rowData[0] = "王五";
		rowData[1] = "19";
		rowData[2] = "男";
		data.add(rowData);
		
		model.addAttribute("tableData", data);
		return "pgoffice/word_table_fill";
	}
	
	@RequestMapping(value = "fillDocData", method = RequestMethod.GET)
	public String fillDocData(Model model) {
		
		HashMap bmData = new HashMap();
		bmData.put("PO_name", "小明");
		bmData.put("PO_depart", "技术部");
		
		HashMap tagData = new HashMap();
		tagData.put("[dr]", "DataRegions");
		tagData.put("[dt]", "DataTags");
		
		model.addAttribute("bm_data", bmData);
		model.addAttribute("tag_data", tagData);
		
		return "pgoffice/word_data_fill";
	}
	
	
	@RequestMapping(value = "saveTableDoc", method = RequestMethod.POST)
	public String saveTableDoc(HttpServletRequest request, 
												HttpServletResponse response, Model model) {
		FileSaver fs=new FileSaver(request,response);
		fs.saveToFile(request.getSession().getServletContext().getRealPath("") + "doc/saved_doc.docx");
		fs.close();
		return "pgoffice/show_doc";
	}
}
