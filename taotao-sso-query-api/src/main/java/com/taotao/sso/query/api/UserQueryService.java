package com.taotao.sso.query.api;

import com.taotao.com.pojo.TaotaoResult;

public interface UserQueryService {
	public TaotaoResult getUserByToken(String token);
}
