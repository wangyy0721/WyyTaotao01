package com.taotao.rest.service;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	public TaotaoResult getItemBaseInfo(long itemId);
	public TaotaoResult getItemDesc(long itemId);
	public TaotaoResult getItemParam(long itemId);
	public void delItemCase(long itemId);
}
