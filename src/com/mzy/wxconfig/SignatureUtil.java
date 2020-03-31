package com.mzy.wxconfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;

public class SignatureUtil {
	public static class Config {

		private String appid;
		// ����ǩ����ʱ���
		private String timestamp;
		// ����ǩ���������
		private String nonceStr;
		// ǩ��
		private String signature;
		private String url;

		public String getAppid() {
			return appid;
		}

		public void setAppid(String appid) {
			this.appid = appid;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getNonceStr() {
			return nonceStr;
		}

		public void setNonceStr(String nonceStr) {
			this.nonceStr = nonceStr;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String toJSON() {
			Map<String, String> data = new HashMap();
			data.put("appid", appid);
			data.put("timestamp", timestamp);
			data.put("nonceStr", nonceStr);
			data.put("signature", signature);
			JSONObject obj = new JSONObject(data);
			return obj.toString();
		}

	}

	// ��ȡconfig��ʼ������
	public static Config getConfig(String url) throws Exception {
		Config config = new Config();
		//TODO ����һ��Ҫ����, �����޷�ʹ��΢��SDK
		config.setAppid("wxfd0b880d162f9d8a");
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		config.setTimestamp(timestamp);
		String nonceStr = String.valueOf(ThreadLocalRandom.current().nextInt(89999999) + 10000000);
		config.setNonceStr(nonceStr);
		String signature = getSignature(TicketUtil.getTicket(), nonceStr, timestamp, url);
		config.setSignature(signature);
		config.setUrl(url);
		return config;
	}

	public static String getSignature(String jsapi_ticket, String noncestr, String timestamp, String url) {
		Map<String, String> params = new HashMap();
		params.put("jsapi_ticket", jsapi_ticket);
		params.put("noncestr", noncestr);
		params.put("timestamp", timestamp);
		params.put("url", url);
		Map<String, String> sortParams = sortAsc(params);
		String string1 = mapJoin(sortParams, false);
		System.out.println(params);
System.out.println("STRING1:"+string1);
		try {
			return DigestUtils.sha1Hex(string1.getBytes("UTF-8"));
		} catch (IOException var8) {
			return "";
		}
	}

	// �Բ����������򣬵õ�һ������Ĳ����б�
	public static Map<String, String> sortAsc(Map<String, String> map) {
		HashMap<String, String> tempMap = new LinkedHashMap();
		List<Entry<String, String>> infoIds = new ArrayList(map.entrySet());
		Collections.sort(infoIds, new Comparator<Entry<String, String>>() {
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return ((String) o1.getKey()).compareTo((String) o2.getKey());
			}
		});
		for (int i = 0; i < infoIds.size(); ++i) {
			Entry<String, String> item = (Entry) infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}

		return tempMap;
	}

	// ��װ���������string1

	public static String mapJoin(Map<String, String> map, boolean valueUrlEncode) {
		StringBuilder sb = new StringBuilder();
		Iterator i$ = map.keySet().iterator();

		while (true) {
			String key;
			do {
				do {
					if (!i$.hasNext()) {
						if (sb.length() > 0) {
							sb.deleteCharAt(sb.length() - 1);
						}

						return sb.toString();
					}

					key = (String) i$.next();
				} while (map.get(key) == null);
			} while ("".equals(map.get(key)));

			try {
				String temp = key.endsWith("_") && key.length() > 1 ? key.substring(0, key.length() - 1) : key;
				sb.append(temp);
				sb.append("=");
				String value = (String) map.get(key);
				if (valueUrlEncode) {
					value = URLEncoder.encode((String) map.get(key), "utf-8").replace("+", "%20");
				}

				sb.append(value);
				sb.append("&");
			} catch (UnsupportedEncodingException var7) {
				var7.printStackTrace();
			}
		}
	}

}
