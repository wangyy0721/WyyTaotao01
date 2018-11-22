package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.com.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品分类管理
 * @author Adminstrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
	    List<EUTreeNode> resultList = new ArrayList<>();
		//把列表转为TreeNodeList
	    for(TbItemCat tbItemCat : list){
	    	EUTreeNode node = new EUTreeNode();
	    	node.setId(tbItemCat.getId());
	    	node.setText(tbItemCat.getName());
	    	node.setState(tbItemCat.getIsParent()?"closed":"open");
	    	resultList.add(node);
	    }
		return resultList;
	}


	

}
