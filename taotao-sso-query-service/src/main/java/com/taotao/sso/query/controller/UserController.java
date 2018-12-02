package com.taotao.sso.query.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.com.utils.ExceptionUtil;
import com.taotao.sso.query.bean.TbUser;
import com.taotao.sso.query.service.UserQueryService;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserQueryService userQueryService;

    /**
     * 根据token查询用户信息
     * 
     * @param token
     * @return
     */
    @RequestMapping("/token/{token}")
	@ResponseBody
	public Object getUserByToken(@PathVariable String token, String callback) {
    	return userQueryService.getUserByToken(token);
//		TaotaoResult result = null;
//		try {
//			result = userQueryService.getUserByToken(token);
//		} catch (Exception e) {
//			e.printStackTrace();
//			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
//		}
//
//		// 判断是否为jsonp调用
//		if (StringUtils.isBlank(callback)) {
//			return result;
//		} else {
//			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
//			mappingJacksonValue.setJsonpFunction(callback);
//			return mappingJacksonValue;
//		}
	}

}
