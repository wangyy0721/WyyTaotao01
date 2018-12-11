package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public TaotaoResult getItemBaseInfo(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam(@PathVariable Long itemId) {
		TaotaoResult result = itemService.getItemParam(itemId);
		return result;
	}
	
	/**
	 * 后台修改商品信息删除redis中的缓存信息
	 * http://localhost:8081/rest/item/delitemcase/152690887859666
	 * @author wangyy 
	 * @date 2018年11月24日
	 */
	@RequestMapping("/delitemcase/{itemId}")
	@ResponseBody
	public void delItemCase(@PathVariable Long itemId) {
		 itemService.delItemCase(itemId);
	}
}