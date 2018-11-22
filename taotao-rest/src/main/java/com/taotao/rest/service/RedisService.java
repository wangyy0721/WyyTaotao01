package com.taotao.rest.service;

import com.taotao.com.pojo.TaotaoResult;

public interface RedisService {
	
	public TaotaoResult syncContent(long contentCid);
}
