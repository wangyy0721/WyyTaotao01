package com.taotao.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.WebUtils;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;

@Controller
public class IndexController {

	
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		
		return "index";
	}
	
//	@RequestMapping("index")
//	public String showINdex(){
//		return "index";
//	}
	
	
//	@RequestMapping(value = "/httpclient/post",method=RequestMethod.POST,
//			produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
//	@ResponseBody
//	public TaotaoResult testBody(String usename,String password){
//		String result = "usename"+usename+"\tpassword"+password;
//		System.out.println(result);
//		return TaotaoResult.ok();
//	}
	//produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8"  纯文本类型
	//produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8   返回json格式
	
	@RequestMapping(value = "/httpclient/post",method=RequestMethod.POST,
			produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String testBody(String username,String password){
				String result = "服务端：username"+username+"\tpassword"+password;
		System.out.println(result);
		return result;
	}
}
