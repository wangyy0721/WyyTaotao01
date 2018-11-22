package com.taotao.service;

import com.taotao.com.pojo.EUDataGridResult;
import com.taotao.com.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemid);
	EUDataGridResult getItemList(int page, int rows);
	TaotaoResult createItem(TbItem tbItem,String desc) throws Exception;
}
