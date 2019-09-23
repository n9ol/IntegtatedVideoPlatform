package com.zzrenfeng.zhsx.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/*import org.junit.After;
import org.junit.Before;*/

/**
 * json 转换成list
 * 
 * @author rfyfb01
 *
 */
public class JsonUtils {
	private static ObjectMapper objectMapper = null;

	// @Before
	public static void init() {
		objectMapper = new ObjectMapper();
	}

	// @After
	public static void destory() {
		objectMapper = null;
		System.gc();
	}

	@SuppressWarnings("unchecked")
	public static List<LinkedHashMap<String, Object>> json2List(String json) {
		List<LinkedHashMap<String, Object>> list = null;
		try {
			init();
			list = objectMapper.readValue(json, List.class);
			destory();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * <h2>将map转换成json字符串</h2>
	 * 
	 * @Title: writeMapJSON
	 * @Description:将map转换成json字符串
	 */
	public static String map2JSON(Map<String, Object> map) {
		String json = "";
		try {
			init();
			json = objectMapper.writeValueAsString(map);
			destory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void main(String[] args) {
		Map m = str2Map("{\"userId\":\"\",\"photo\":\"\",\"msg\":\"ddd\"}");
		System.out.println(m.toString());
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> str2Map(String str) {
		Map<String, Object> map = null;
		try {
			init();
			map = objectMapper.readValue(str, Map.class);
			destory();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
