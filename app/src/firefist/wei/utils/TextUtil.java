package firefist.wei.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.graphics.Bitmap;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import firefist.wei.main.KXApplication;

/**
 * ���ֹ�����
 * 
 * @author rendongwei
 * 
 */
public class TextUtil {
	private KXApplication mKxApplication;
	
	public TextUtil(){
		
	}

	public TextUtil(KXApplication application) {
		mKxApplication = application;
	}

	/**
	 * ����������ת�����ַ���
	 * 
	 * @param inputStream
	 *            ����������
	 * @return �����ַ���(String ����)
	 */
	public String readTextFile(InputStream inputStream) {
		String readedStr = "";
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				readedStr += tmp;
			}
			br.close();
			inputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return readedStr;
	}

	/**
	 * �����������ʽ
	 * 
	 * @return
	 */
	private Pattern buildPattern() {
		StringBuilder patternString = new StringBuilder(
				mKxApplication.mFacesText.size() * 3);
		patternString.append('(');
		for (int i = 0; i < mKxApplication.mFacesText.size(); i++) {
			String s = mKxApplication.mFacesText.get(i);
			patternString.append(Pattern.quote(s));
			patternString.append('|');
		}
		patternString.replace(patternString.length() - 1,
				patternString.length(), ")");
		return Pattern.compile(patternString.toString());
	}

	/**
	 * ���ı��к��б����ַ������ݻ��ɴ��б���ͼƬ���ı�
	 * 
	 * @param text
	 *            ���б����ַ����ı�
	 * @return ���б���ͼƬ���ı�
	 */
	public CharSequence replace(CharSequence text) {
		try {
			SpannableStringBuilder builder = new SpannableStringBuilder(text);
			Pattern pattern = buildPattern();
			Matcher matcher = pattern.matcher(text);
			while (matcher.find()) {
				Bitmap bitmap = mKxApplication
						.getFaceBitmap(mKxApplication.mFacesText
								.indexOf(matcher.group()));
				if (bitmap != null) {
					ImageSpan span = new ImageSpan(bitmap);
					builder.setSpan(span, matcher.start(), matcher.end(),
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
			return builder;
		} catch (Exception e) {
			return text;
		}
	}

	public String getCharacterPinYin(char c) {
		HanyuPinyinOutputFormat format = null;
		format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String[] pinyin = null;
		try {
			pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		// ���c���Ǻ��֣�toHanyuPinyinStringArray�᷵��null
		if (pinyin == null)
			return null;
		// ֻȡһ������������Ƕ����֣���ȡ��һ������
		return pinyin[0];
	}

	/**
	 * ��ȡ�ַ���ƴ��
	 * 
	 * @param str
	 * @return
	 */
	public String getStringPinYin(String str) {
		StringBuilder sb = new StringBuilder();
		String tempPinyin = null;
		for (int i = 0; i < str.length(); ++i) {
			tempPinyin = getCharacterPinYin(str.charAt(i));
			if (tempPinyin == null) {
				// ���str.charAt(i)�Ǻ��֣��򱣳�ԭ��
				sb.append(str.charAt(i));
			} else {
				sb.append(tempPinyin);
			}
		}
		return sb.toString();
	}
}
