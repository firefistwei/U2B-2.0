package firefist.wei.main.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
import firefist.wei.main.MyConstants;
import firefist.wei.main.domain.Comments;



public class CommentsShowService {

	public static List<Comments> getShowComments(int _pid, int _offset) {
		String path = MyConstants.WebURL + "CommentsShowServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("pid", String.valueOf(_pid));
		params.put("offset", String.valueOf(_offset));
		try {
			return sendGETRequest(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static List<Comments> sendGETRequest(String path,
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
			System.out.println("恩恩，获取评论成功了呀，下面解析就好了。");
			return parseXML(inStream);
		}
		return null;
	}

	private static List<Comments> parseXML(InputStream inStream)
			throws Exception {
		List<Comments> commentses = new ArrayList<Comments>();
		Comments mComment = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_TAG:
				if ("comments".equals(parser.getName())) {
					int pid = Integer.valueOf(parser.getAttributeValue(0));
					mComment = new Comments();
					mComment.setPid(pid);
				/*} else if ("num_comment".equals(parser.getName())) {
					mComment.setNum_comment(Integer.valueOf(parser.nextText()));
				} else if ("con_comment".equals(parser.getName())) {
					mComment.setCon_comment(parser.nextText());
				} else if ("speaker".equals(parser.getName())) {
					mComment.setSpeaker(parser.nextText());
				} else if ("sdate".equals(parser.getName())) {
					mComment.setSdate(parser.nextText());
				} else if ("head".equals(parser.getName())) {
					mComment.setHead(parser.nextText());*/
				}
				break;

			case XmlPullParser.END_TAG:
				if ("comments".equals(parser.getName())) {
					commentses.add(mComment);
					mComment = null;
				}
				break;
			}
			event = parser.next();
		}

		return commentses;
	}

}
