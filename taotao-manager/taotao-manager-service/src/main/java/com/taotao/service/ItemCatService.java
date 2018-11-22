package com.taotao.service;

import java.util.List;

import com.taotao.com.pojo.EUTreeNode;

public interface ItemCatService {

	public List<EUTreeNode> getCatList(long parentId);
}

