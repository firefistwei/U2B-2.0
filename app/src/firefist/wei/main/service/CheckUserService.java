package firefist.wei.main.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import firefist.wei.main.MyConstants;
import firefist.wei.main.domain.User;

import android.util.Xml;


public class CheckUserService {

	public static User checkUser(String uname, String upasswd)
			throws Exception {

		String path = MyConstants.WebURL + "SomeOtherServlets";
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "checkUser");
		params.put("params1", uname);
		params.put("params2", upasswd);

		try {
			return sendGETRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static User sendGETRequest(String path,
			Map<String, String> params, String encoding) throws Exception {

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

	private static User parseXML(InputStream inStream) throws Exception{
		
		User myUser = null;
		/*
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		int event = parser.getEventType();
		while(event != XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_TAG:
				if("user".equals(parser.getName())){
					int uid = Integer.valueOf(parser.getAttributeValue(0));
					myUser = new User();
					myUser.setUid(uid);
				}else if("uname".equals(parser.getName())){
					myUser.setUname(parser.nextText());
				}else if("upasswd".equals(parser.getName())){
					myUser.setUpasswd(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if("user".equals(parser.getName())){
					System.out.println("Ok£¬I got user xml");
					System.out.println(myUser.getUid());
					System.out.println(myUser.getUname());
					System.out.println(myUser.getUpasswd());
				}
				break;
			}
			event = parser.next();
		}

		
	}*/
		return myUser;
	}
}
