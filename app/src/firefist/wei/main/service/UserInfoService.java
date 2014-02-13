package firefist.wei.main.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import firefist.wei.main.MyConstants;
import firefist.wei.main.domain.UserInfo;


import android.util.Xml;

public class UserInfoService {
       
	public static UserInfo getUserInformation() throws Exception{
		String path = MyConstants.WebURL + "SomeOtherServlets";
		Map<String,String> params = new HashMap<String,String>();
		params.put("action","getUserInfo");
		params.put("uid",String.valueOf(MyConstants.user_uid));
		
		try{
			return sendGetRequest(path,params,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	private static UserInfo sendGetRequest(String path,
			Map<String, String> params, String encoding)throws Exception {
		
		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(), encoding));
			url.append("&");
		}
		url.deleteCharAt(url.length() - 1);
		HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			InputStream inStream = conn.getInputStream();
			return parseXML(inStream);
		}
		return null;

	}

	private static UserInfo parseXML(InputStream inStream) throws Exception{
		UserInfo userInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream,"UTF-8");
		int event = parser.getEventType();
		while(event!=XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_TAG:
				if("userinfo".equals(parser.getName())){
					int uid = Integer.valueOf(parser.getAttributeValue(0));
					userInfo = new UserInfo();
					userInfo.setUid(uid);
				}else if("name".equals(parser.getName())){
					userInfo.setName(parser.nextText());
				}else if("head".equals(parser.getName())){
					userInfo.setHead(parser.nextText());
				}else if("sex".equals(parser.getName())){
					userInfo.setSex(Integer.parseInt(parser.nextText()));
				}else if("ulevel".equals(parser.getName())){
					userInfo.setUlevel(Integer.parseInt(parser.nextText()));
				}else if("gid".equals(parser.getName())){
					userInfo.setGid(Integer.parseInt(parser.nextText()));
				}else if("gclass".equals(parser.getName())){
					userInfo.setGclass(parser.nextText());
				}else if("gschool".equals(parser.getName())){
					userInfo.setGschool(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if("userinfo".equals(parser.getName())){
					//恩，对象只有一个，什么都不做
				}
				break;
			}
			event = parser.next();
		}
		return userInfo;
	}
	
}
