package firefist.wei.main.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyUploadService {

	public static String uploadPhoto(String url, Map<String, String> params,
			File file) {
		String uid = params.get("uid");
		String title = params.get("title");
		String description = params.get("description");

		String response = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		MultipartEntity multipartEntity = new MultipartEntity();
		try {
			multipartEntity.addPart("file", new FileBody(file));
			multipartEntity.addPart("uid", new StringBody(uid));
			multipartEntity.addPart("title",
					new StringBody(URLEncoder.encode(title, "UTF-8")));
			multipartEntity.addPart("description",
					new StringBody(URLEncoder.encode(description, "UTF-8")));
			httppost.setEntity(multipartEntity);
			HttpResponse httpResponse = httpClient.execute(httppost);
			HttpEntity responseEntity = httpResponse.getEntity();
			InputStream inputStream = responseEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			return sb.toString();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return response;
	}

	public static String uploadVoice(String url,
			List<Map<String, String>> params, File file) {
		String response = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		MultipartEntity multipartEntity = new MultipartEntity();
		try {
			multipartEntity.addPart("file", new FileBody(file));
			multipartEntity.addPart("uid", new StringBody("888"));
			multipartEntity.addPart("title", new StringBody("照片的标题"));
			multipartEntity.addPart("description", new StringBody("照片的描述"));
			httppost.setEntity(multipartEntity);
			HttpResponse httpResponse = httpClient.execute(httppost);
			HttpEntity responseEntity = httpResponse.getEntity();
			InputStream inputStream = responseEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {

				sb.append(line);
			}
			response = sb.toString();
			System.out.println(sb.toString());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return response;
	}
}
