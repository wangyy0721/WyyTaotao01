package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.com.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;


@Controller
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	
	@RequestMapping("query/itemcatid/{cid}")
	public TaotaoResult queryCatalogById(@PathVariable long cid) throws Exception {
		//根据分类id查询列表
		TaotaoResult result = itemParamService.queryCatalogById(cid);
		return result;
	}

}
