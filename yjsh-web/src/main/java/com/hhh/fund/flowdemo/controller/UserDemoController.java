package com.hhh.fund.flowdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhh.fund.util.LogUploadClient;

@Controller
@RequestMapping(value="/demo")
public class UserDemoController {
	
	@Autowired
	LogUploadClient client;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String loginForm(Model model) {
		return "user/main";
	}
	
	@ResponseBody
	@RequestMapping(value="/testlogupload", method = RequestMethod.GET)
	public String testLogUpload(){
		client.uploadLogException("5191b6589327472cb5d0959d7b7fc083", "yjsh_web", "this is test value");
		return "success";
	}
	
//	@RequestMapping(value="/htmltopdf")
//	public void htmlToPdf(HttpServletRequest request, HttpServletResponse response){
//		String path = request.getParameter("path");  
//        
//          
//        String pdfPath = request.getSession().getServletContext().getRealPath("/tmp");  
//        String pdfName = UUID.randomUUID().toString() + ".pdf";  
//          
//        if(HtmlToPdf.convert(path, pdfPath + "/" + pdfName)){  
//            try {
//				response.sendRedirect(request.getContextPath() + "/tmp/" + pdfName);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}  
//        }
//	}
}
