package firefist.wei.main.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import firefist.wei.main.MyConstants;
import firefist.wei.main.tinything.Register;

public class MyService {

	public static InputStream getRegister() {
		String path = MyConstants.WebURL + "UserService";

		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "register");
		params.put("email", Register.uname);
		params.put("password", Register.upasswd);
		// params.put("gender", 1+"");

		InputStream inStream = null;
		try {
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inStream;
	}

	public static InputStream getLogin(String email, String passwd) {
		String path = MyConstants.WebURL + "UserService";

		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "login");
		params.put("email", email);
		params.put("password", passwd);

		InputStream inStream = null;
		try {
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inStream;
	}

	public static InputStream setUserInfo(Map<String, String> params) throws Exception {
				String path = MyConstants.WebURL + "UserService";

				StringBuilder data = new StringBuilder();
				if (params != null && !params.isEmpty()) {
					for (Map.Entry<String, String> entry : params.entrySet()) {
						data.append(entry.getKey()).append("=");
						data.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
						data.append("&");
					}
					data.deleteCharAt(data.length() - 1);
				}
				byte[] entity = data.toString().getBytes();// 生成实体数据
				HttpURLConnection conn = (HttpURLConnection) new URL(path)
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);// 允许对外输出数据
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
				OutputStream outStream = conn.getOutputStream();
				outStream.write(entity);
				if (conn.getResponseCode() == 200) {
					return conn.getInputStream();
				}
				return null;
	}

	public static InputStream setComment(String path,
			Map<String, String> params, String encoding) throws Exception {
		// title=liming&timelength=90
		StringBuilder data = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(), encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length() - 1);
		}
		byte[] entity = data.toString().getBytes();// 生成实体数据
		HttpURLConnection conn = (HttpURLConnection) new URL(path)
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 允许对外输出数据
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		if (conn.getResponseCode() == 200) {
			return conn.getInputStream();
		}
		return null;
	}

	public static InputStream getComment() {
		return null;

	}

	public static InputStream getDailyHot() throws Exception {
		String path = MyConstants.WebURL + "RecommendService";

		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "hot");

		InputStream inStream = null;
		try {
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);

			HttpURLConnection conn = (HttpURLConnection) new URL(
					url.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return inStream;
	}

	public static InputStream getDailyLast(int offset) throws Exception {
		String path = MyConstants.WebURL + "RecommendService";

		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "last");
		params.put("offset", String.valueOf(offset));

		InputStream inStream = null;
		try {
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);

			HttpURLConnection conn = (HttpURLConnection) new URL(
					url.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
				return inStream;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return inStream;
	}
	public static InputStream getRecommendWeek() {
		String path = MyConstants.WebURL + "Servlet";
		InputStream inStream = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(
					path.toString()).openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
			inStream = null;
		}
		return inStream;
	}

	public static InputStream getShowComments(int _pid, int _offset) {
		String path = MyConstants.WebURL + "CommentsShowServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("pid", String.valueOf(_pid));
		params.put("offset", String.valueOf(_offset));
		InputStream inStream = null;
		try {
			StringBuilder url = new StringBuilder(path);
			url.append("?");
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append(entry.getKey()).append("=");
				url.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				url.append("&");
			}
			url.deleteCharAt(url.length() - 1);
			HttpURLConnection conn = (HttpURLConnection) new URL(url.toString())
					.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				inStream = conn.getInputStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inStream;
	}

	/**
	 * 通过HttpClient发送Post请求
	 * 
	 * @param path
	 *            请求路径
	 * @param params
	 *            请求参数
	 * @param encoding
	 *            编码
	 * @return 请求是否成功
	 */
	public static boolean sendHttpClientPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();// 存放请求参数
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, encoding);
		HttpPost httpPost = new HttpPost(path);
		httpPost.setEntity(entity);
		DefaultHttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 发送Post请求
	 * 
	 * @param path
	 *            请求路径
	 * @param params
	 *            请求参数
	 * @param encoding
	 *            编码
	 * @return 请求是否成功
	 */
	public static boolean sendPOSTRequest(String path,
			Map<String, String> params, String encoding) throws Exception {
		// title=liming&timelength=90
		StringBuilder data = new StringBuilder();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				data.append(entry.getKey()).append("=");
				data.append(URLEncoder.encode(entry.getValue(), encoding));
				data.append("&");
			}
			data.deleteCharAt(data.length() - 1);
		}
		byte[] entity = data.toString().getBytes();// 生成实体数据
		HttpURLConnection conn = (HttpURLConnection) new URL(path)
				.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 允许对外输出数据
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(entity.length));
		OutputStream outStream = conn.getOutputStream();
		outStream.write(entity);
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}
}
