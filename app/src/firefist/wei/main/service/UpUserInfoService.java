package firefist.wei.main.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import firefist.wei.main.MyConstants;



public class UpUserInfoService {

	public static boolean updateUserInfo(int uid, String name, String head,
			int sex, int ulevel,int gid, String gclass, String gschool){
		String path = MyConstants.WebURL + "SomeOtherServlets";
		Map<String,String> params = new HashMap<String,String>();
		params.put("action","updateuserinfo");
		params.put("uid", String.valueOf(uid));
		params.put("name", name);
		params.put("head", head);
		params.put("sex", String.valueOf(sex));
		params.put("ulevel",String.valueOf(ulevel));
		params.put("gid", String.valueOf(gid));
		params.put("gclass", gclass);
		params.put("gschool", gschool);
		
		try{
			return setGetRequest(path,params,"UTF-8");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

	private static boolean setGetRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		StringBuilder url = new StringBuilder(path);
		url.append("?");
		for(Map.Entry<String, String> entry: params.entrySet()){
			url.append(entry.getKey()).append("=");
			url.append(URLEncoder.encode(entry.getValue(),encoding));
			url.append("&");
		}
		url.deleteCharAt(url.length()-1);
		HttpURLConnection conn =(HttpURLConnection)new URL(url.toString())
		      .openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			
			return true;
		}		
		
		return false;
	}
	
}
