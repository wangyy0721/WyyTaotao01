package com.taotao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.glassfish.external.statistics.Statistic;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import com.taotao.com.pojo.EUDataGridResult;
import com.taotao.com.pojo.TaotaoResult;
import com.taotao.com.utils.HttpClientUtil;
import com.taotao.com.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private  TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final ObjectMapper Mapper = new ObjectMapper();
	
	@Override
	public TbItem getItemById(long itemid) {
		TbItemExample example = new TbItemExample();
		//添加查询条件
		Criteria criteria= example.createCriteria();
		criteria.andIdEqualTo(itemid);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}
	/**
	 * 商品列表查询
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 * @see com.taotao.service.ItemService#getItemList(long, long)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		//查询商品列表
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	@Override
	public TaotaoResult createItem(TbItem tbItem,String desc) throws Exception {
		//生成商品ID
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		itemMapper.insert(tbItem);
		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(id, desc);
		if (result.getStatus() != 200) {
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	/**
	 * 添加商品描述
	 * <p>Title: insertItemDesc</p>
	 * <p>Description: </p>
	 * @param desc
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	/**
	 * 修改商品信息
	 * 20181124
	 * （修改后要调用rest将redis中缓存删掉）
	 * 
	 * 
	 */
	@Override
	public TaotaoResult updateItem(TbItem tbItem, String desc) throws Exception {
		//生成商品ID
		
		tbItem.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(tbItem);
		//直接删除redis
	//	HttpClientUtil.doGet("http://localhost:8081/rest/item/delitemcase/"+tbItem.getId());
		
		//使用rabbitMQ  发送消息通知其他用户  解耦
		sendMsg(tbItem.getId());
		return TaotaoResult.ok();
	}
	
	private void sendMsg(Long itemId) {
		try {
			Map<String, Object> msg = new HashMap<String, Object>();
			msg.put("itemId", itemId);
			msg.put("type", "update");
			msg.put("date", System.currentTimeMillis());
			rabbitTemplate.convertAndSend("item.update",Mapper.writeValueAsString(msg));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
