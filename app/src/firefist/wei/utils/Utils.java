package firefist.wei.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import firefist.wei.main.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.format.DateUtils;

/**
 * ������
 * 
 * @author wei
 * 
 */
public class Utils {
	/**
	 * �����Ա����ֻ�ȡ���Ա�ͼƬ
	 * 
	 * @param res
	 *            Resources����
	 * @param gender
	 *            0����Ů��,1��������
	 * @return �Ա�ͼƬ(Bitmap ����)
	 */
	public static Bitmap getGender(Resources res, int gender) {
		switch (gender) {
		case 0:
			return BitmapFactory.decodeResource(res,
					R.drawable.profile_icon_girl);
		case 1:
			return BitmapFactory.decodeResource(res,
					R.drawable.profile_icon_boy);
		default:
			return BitmapFactory.decodeResource(res,
					R.drawable.profile_icon_boy);
		}
	}

	/**
	 * �����Ա����ֻ�ȡ���Ա�����
	 * 
	 * @param gender
	 *            0����Ů��,1��������
	 * @return �Ա�����(String ����)
	 */
	public static String getGender(int gender) {
		switch (gender) {
		case 0:
			return "Ů";
		case 1:
			return "��";
		default:
			return "δ֪";
		}
	}

	/**
	 * ת��long�����ڸ�ʽ
	 * 
	 * @param context
	 * @param date
	 * @return
	 */
	public static String formatDate(Context context, long date) {
		int format_flags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
				| DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_CAP_AMPM
				| DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_DATE
				| DateUtils.FORMAT_SHOW_TIME;
		return DateUtils.formatDateTime(context, date, format_flags);
	}

	/**
	 * ת��long�����ڸ�ʽ
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(long date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date(date));
	}

	/**
	 * ��ȡ��ǰ��ʱ��
	 * 
	 * @param context
	 * @return
	 */
	public static String getTime(Context context) {
		return formatDate(context, System.currentTimeMillis());
	}

	/**
	 * ��ȡ��ǰ��ʱ��
	 * 
	 * @return
	 */
	public static String getTime() {
		return formatDate(System.currentTimeMillis());
	}

	/**
	 * ��inputStream ת�� String
	 * 
	 * @return
	 */
	public static String readInputStream(InputStream inputStream) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	public static String createJsonString(String key, Object value)
			throws JSONException
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, value);
		return jsonObject.toString();

	}
}
