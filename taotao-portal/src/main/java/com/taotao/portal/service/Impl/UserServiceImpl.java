package com.taotao.portal.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.com.utils.HttpClientUtil;
//import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.sso.query.bean.TbUser;
import com.taotao.sso.query.service.UserQueryService;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	public String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	
	/**
	 * 2018.11.27不在调用sso进行token信息校验了，使用dubbo服务即下面的新方法
	 */
//	@Override
//	public TbUser getUserByToken(String token) {
//		try {
//			//调用sso系统的服务，根据token取用户信息
//			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
//			//把json转换成TaotaoResult
//			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
//			if (result.getStatus() == 200) {
//				TbUser user = (TbUser) result.getData();
//				return user;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	@Autowired
	private UserQueryService userQueryService;
	
	@Override
	public TbUser getUserByToken(String token) {
//		TaotaoResult result = this.userQueryService.getUserByToken(token);
//		if (result.getStatus() == 200) {
//			TbUser user = (TbUser) result.getData();
//			return user;
//		}
		return this.userQueryService.getUserByToken(token);
	}
}
