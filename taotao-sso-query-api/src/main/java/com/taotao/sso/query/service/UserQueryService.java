package com.taotao.sso.query.service;
import com.taotao.sso.query.bean.TbUser;

public interface UserQueryService {
	public TbUser getUserByToken(String token);
}
