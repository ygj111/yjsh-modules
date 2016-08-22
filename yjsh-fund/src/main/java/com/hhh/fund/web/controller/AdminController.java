package com.hhh.fund.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value="/main")
	public String adminMain(){
		return "/admin/main";
	}
}
