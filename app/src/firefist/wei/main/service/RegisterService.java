package firefist.wei.main.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import firefist.wei.main.MyConstants;
import firefist.wei.main.tinything.Register;

public class RegisterService {

	public static boolean register(String _name, int _sex, String _gclass,
			String _gschool) {
		String path = MyConstants.WebURL + "RegisterServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("params1", Register.uname);
		params.put("params2", Register.upasswd);

		params.put("name", _name);
		params.put("sex", String.valueOf(_sex));
		params.put("gclass", _gclass);
		params.put("gschool", _gschool);

		try {
			return setGetRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	private static boolean setGetRequest(String path,
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
//			InputStream inStream = conn.getInputStream();

			return true;
		}
		return false;

	}

}
