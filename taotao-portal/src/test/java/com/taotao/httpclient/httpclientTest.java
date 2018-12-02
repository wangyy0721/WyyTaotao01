package com.taotao.httpclient;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.portal.service.HttpClientApiService;


public class httpclientTest {

	@Test
	public void testhttpClientPost() throws Exception, IOException {
		  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String url = "http://localhost:8082/httpclient/post.action";
		HttpPost httpPost = new HttpPost(url);
	    CloseableHttpResponse response = httpclient.execute(httpPost);
	    String resultString = EntityUtils.toString(response.getEntity());
	    System.out.println(resultString);
	    response.close();
	    httpclient.close();
    }
	
	@Test
	public void doPost() {
		String url="http://localhost:8082/httpclient/post.action";
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", "张三");
		param.put("password", "123");
		   
		// 创建Httpclient对象 
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"UTF-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println("------------------------------------");
			System.out.println(resultString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void doGetPool() throws ClientProtocolException, IOException {
		System.out.println("=============");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		HttpClientApiService http = (HttpClientApiService) applicationContext.getBean("httpClientApiService");
		String a = http.doGet("http://www.baidu.com/");
		System.out.println("*****"+a);
	}
	
}
