package com.taotao.portal.service;

import com.taotao.sso.query.bean.TbUser;

//import com.taotao.pojo.TbUser;

public interface UserService {
	public TbUser getUserByToken(String token);
}
