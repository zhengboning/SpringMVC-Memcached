package com.test.web;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.utils.MemcachedUtils;

@Controller
@RequestMapping("/loginController")
public class Login {
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "username") String userid,@RequestParam(value = "password") String passwd, HttpSession session){
		ModelAndView m = new ModelAndView();
		m.setViewName("../index");		
		MemcachedUtils.set("kkk", "zhengbn",new Date(1000 * 60));
		Object ss = MemcachedUtils.get("kkk");	
		System.out.println(ss.toString());	
		m.addObject("errMsg","ÖØÐÂµÇÂ¼£¡");			
		return m;	
	}
	
	//
	
}
