package com.taotao.portal.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * implements BeanFactoryAware实现多例模式
 * @author wangyy 
 * @date 2018年11月25日
 */
@Service
public class HttpClientApiService implements BeanFactoryAware{

	private BeanFactory beanFactory;//bean工厂单例
	
	
//	@Autowired
//	private CloseableHttpClient httpClient;
	
	@Autowired
	private RequestConfig requestConfig;
    /**
     * 无参的get请求
     * @author wangyy 
     * @date 2018年11月25日
     */
	public String doGet(String url) throws ClientProtocolException, IOException  {
        
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = getHttpClient().execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //System.out.println("内容长度："+content.length());
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
		return null;
	}
	
	/**
	 * 带参数的get请求
	 * @author wangyy 
	 * @date 2018年11月25日
	 */
	public String doGet(String url,Map<String, String> params) throws URISyntaxException, ClientProtocolException, IOException {
		URIBuilder builder = new URIBuilder(url);
		for(Map.Entry<String, String> entry : params.entrySet()){
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		return doGet(builder.build().toString());
	}
	
	
	public String doPost(String url, Map<String, String> param) throws UnsupportedEncodingException {
		String resultString = "";
		// 创建Http Post请求
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		// 创建参数列表
		if (param != null) {
			List<NameValuePair> paramList = new ArrayList<>();
			for (String key : param.keySet()) {
				paramList.add(new BasicNameValuePair(key, param.get(key)));
			}
			// 模拟表单
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
			httpPost.setEntity(entity);
		}
		CloseableHttpResponse response = null;		
		try {
			// 执行http请求
			response = getHttpClient().execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
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

		return resultString;
	}

	public String doPost(String url) throws UnsupportedEncodingException {
		return doPost(url, null);
	}
	
	public String doPostJson(String url, String json) {
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(requestConfig);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = getHttpClient().execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

	private CloseableHttpClient getHttpClient(){
		return this.beanFactory.getBean(CloseableHttpClient.class);
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
	}
	
}
