package com.taotao.portal.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.com.utils.HttpClientUtil;
import com.taotao.portal.service.ItemService;

public class ItemMQHandler {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private ItemService itemService;
	
	public void execute(String msg) {
		try {
			JsonNode jsonNode = MAPPER.readTree(msg);
			
			
			HttpClientUtil.doGet("http://localhost:8081/rest/item/delitemcase/"+jsonNode.get("itemId"));
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
