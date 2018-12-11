package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamItemService;
/**
 * 商品规格参数展示
 * @author wangyy 
 * @date 2018年11月24日
 */
@Service
public class IntemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public String getItemParamByItemId(Long itemId) {
		TbItemParamItem tbItemParamItem = itemParamItemMapper.selectByPrimaryKey(itemId);
		if (tbItemParamItem == null){
			return "";
		}
		tbItemParamItem.getParamData();
		return null;
	}

}
