/**
 * 
 */
package com.zzrenfeng.zhsx.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * httpclient服务类
 * 
 * @author 田杰熠
 *
 */
public class BaseHttpService {

	/**
	 * 根据参数和url获取远程接口数据-返回数据为_json格式时
	 * 
	 * @param paramMap
	 *            参数集合
	 * @param url
	 *            远程数据接口url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getResponseResult(Map<String, Object> paramMap, String url) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String resResult = getResponseResultStr(paramMap, url);
		ObjectMapper mapper = new ObjectMapper();
		try {
			resMap = mapper.readValue(resResult, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 根据参数和url获取远程接口数据-返回数据为XML格式时
	 * 
	 * @param paramMap
	 * @param url
	 * @return
	 */
	public static Map<String, Object> getResponseResultXml(Map<String, Object> paramMap, String url) {
		String resResult = getResponseResultStr(paramMap, url);
		Map<String, Object> resMap = ReaderXmlUtil.parseXml(resResult);
		return resMap;
	}

	/**
	 * 获取响应的字符串结果
	 * 
	 * @param paramMap
	 *            参数集合
	 * @param url
	 *            远程数据接口url
	 * @return
	 */
	public static String getResponseResultStr(Map<String, Object> paramMap, String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nvps = getNameValuePairListByMapParam(paramMap);

		String resResult = "";
		try {
			if (null != nvps && nvps.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
				CloseableHttpResponse response = httpclient.execute(httpPost);
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						resResult = EntityUtils.toString(entity, "UTF-8");
					}
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resResult;
	}

	/**
	 * 根据参数获取httpclient的参数类型NameValuePair
	 * 
	 * @param paramMap
	 * @return
	 */
	public static List<NameValuePair> getNameValuePairListByMapParam(Map<String, Object> paramMap) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> set = paramMap.keySet();

		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = it.next();

			String value = (String) paramMap.get(key);
			nvps.add(new BasicNameValuePair(key, value));
		}
		return nvps;
	}

}
