package com.taotao.sso.query.service;
import com.taotao.com.pojo.TaotaoResult;

public interface UserQueryService {
	public TaotaoResult getUserByToken(String token);
}
