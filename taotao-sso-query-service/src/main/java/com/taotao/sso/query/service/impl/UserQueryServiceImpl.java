package com.taotao.sso.query.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.com.utils.JsonUtils;
import com.taotao.sso.query.bean.TbUser;
import com.taotao.sso.query.dao.JedisClient;
import com.taotao.sso.query.service.UserQueryService;


@Service
public class UserQueryServiceImpl implements UserQueryService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${REDIS_USER_SESSION_KEY}")
	private String REDIS_USER_SESSION_KEY;
	@Value("${SSO_SESSION_EXPIRE}")
	private Integer SSO_SESSION_EXPIRE;
	
	
	@Override
	public TaotaoResult getUserByToken(String token) {
		
		//根据token查询用户信息
		String json = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);
		if(StringUtils.isBlank(json)){
			return TaotaoResult.build(400, "此session已经过期请，重新登录");
		}
		// 更新过期时间
		jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
		// 返回用户信息
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}
	
	
	

}