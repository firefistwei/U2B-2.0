package firefist.wei.main;

import firefist.wei.main.domain.Comments;
import firefist.wei.main.domain.FriendsPhoto;
import firefist.wei.main.domain.MyActive;
import firefist.wei.main.domain.Photo;
import firefist.wei.main.domain.User;
import firefist.wei.main.result.HomeResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;


public final class MyConstants {

	public final static String WebURL = "http://1.u2b3.sinaapp.com/";

	public static String login_user = "";

	

	public static int user_head_int = R.drawable.default_head;

	public static String user_head_string = "";

	
	/**
	 * MOST Important
	 *
	 */
	public static User meUser= null;
	static {
		meUser = new User();
	}
	public static long user_uid = 0;
	public static String user_name = "";
	public static String user_head = "";
	public static String user_email = "";
	
	public static int user_gender = 0;
	public static String user_sig = "";
	public static String user_birthday = "";
	public static String user_school = "";
	
	public static int user_degree = 0;
	public static int user_score = 0;
	public static String user_register_day = "";
	
	public static String user_goodat = "";

	// �ϴ���Ƭ��·��
	public static String UploadPhotoPath = null;
	
	

	public static List<FriendsPhoto> mFriendsPhoto = new ArrayList<FriendsPhoto>();

	static {
		mFriendsPhoto
				.add(new FriendsPhoto(
						2,
						"�ں���",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6��20��",
						"�����޴�����",
						"���ǲ�Ҫ�ٺ�����Ҹ��Ļ�����",
						5,7,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						1,
						"����",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6��19��",
						"���Ŀɳܣ���2����",
						"",
						3,5,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						1,
						"����",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6��18��",
						"���緢����",
						"",
						1,4,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						2,
						"�ں���",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"2013��5��20��",
						"��ʵ �� ������",
						"�����Ҹ���һ�Զ�",
						11,12,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));

	}

	public static final String[] IMAGES = new String[] {
			// Heavy images
			/*"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
			"https://lh4.googleusercontent.com/-K2FMuOozxU0/T3R4lRAiBTI/AAAAAAAAAE8/a3Eh9JvnnzI/s1024/sample_image_02.jpg",
			"https://lh5.googleusercontent.com/-SCS5C646rxM/T3R4l7QB6xI/AAAAAAAAAFE/xLcuVv3CUyA/s1024/sample_image_03.jpg",
			"https://lh6.googleusercontent.com/-f0NJR6-_Thg/T3R4mNex2wI/AAAAAAAAAFI/45oug4VE8MI/s1024/sample_image_04.jpg",
			"https://lh3.googleusercontent.com/-n-xcJmiI0pg/T3R4mkSchHI/AAAAAAAAAFU/EoiNNb7kk3A/s1024/sample_image_05.jpg",
			"https://lh3.googleusercontent.com/-X43vAudm7f4/T3R4nGSChJI/AAAAAAAAAFk/3bna6D-2EE8/s1024/sample_image_06.jpg",
			"https://lh5.googleusercontent.com/-MpZneqIyjXU/T3R4nuGO1aI/AAAAAAAAAFg/r09OPjLx1ZY/s1024/sample_image_07.jpg",
			"https://lh6.googleusercontent.com/-ql3YNfdClJo/T3XvW9apmFI/AAAAAAAAAL4/_6HFDzbahc4/s1024/sample_image_08.jpg",
			"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg",
			"https://lh4.googleusercontent.com/-Li-rjhFEuaI/T3R4o-VUl4I/AAAAAAAAAF8/5E5XdMnP1oE/s1024/sample_image_10.jpg", */
		"http://img3.chinaface.com/middle/11216lol4UryK6VMlgVOaLNiCRsKQ.jpg",
			"http://www.hinews.cn/pic/0/11/11/28/11112856_974903.jpg",
			"http://img.h9t.net/allimg/121116/1_121116232458_1.jpg",
			"http://photocdn.sohu.com/20061228/Img247320188.jpg",
			"http://x.limgs.cn/f2/g/130606/b201315616203951b046571f84a.jpg",
			"http://imgsrc.baidu.com/forum/pic/item/ed0ed979cd796f6e1e3089f4.jpg"
	
	};

	public static class Extra {
		public static final String IMAGES = "com.nostra13.example.universalimageloader.IMAGES";
		public static final String IMAGE_POSITION = "com.nostra13.example.universalimageloader.IMAGE_POSITION";
	}

	public static List<MyActive> mActiveList = new ArrayList<MyActive>();
	public static String activeJSON = "[{\"uid\":301,\"aid\":17,\"atype\":1,\"aname\":\"3+3�漴���������\",\"atime\":\"����  4:20PM\",\"aposition\":\"��԰�̲��\",\"amember\":\"301+302+303\",\"apeople_no\":\"1/10\",\"adescrip\":\"��լ�ˣ��������ģ���ʶ�µ�����.\\r\\n3+3��һ�����İ���˽����Ļ��Ҫ��ʼ��������Ҫ3������ ��3��Ů������Ҫ���Ծ�ĳ������������ۣ��Ӷ���լլ������ʹ���Ǳ�լ�Ļ��������������������潻����\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":302,\"aid\":11,\"atype\":1,\"aname\":\"ħ��������ս\",\"atime\":\"����  20:20\",\"aposition\":\"1310����\",\"amember\":\"302\",\"apeople_no\":\"1/2\",\"adescrip\":\"û������ħ���糤������ս��\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":303,\"aid\":5,\"atype\":1,\"aname\":\"ƹ���������ս\",\"atime\":\"����  20:20\",\"aposition\":\"�ٳ�ƹ����\",\"amember\":\"303\",\"apeople_no\":\"1/2\",\"adescrip\":\"˭Ҫ��ƹ���򣬺Ǻǣ������MM,�Ǻǣ������Դ����Ǻ�\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":304,\"aid\":3,\"atype\":1,\"aname\":\"android��������\",\"atime\":\"����  8:20PM\",\"aposition\":\"1�Ž�ѧ¥ǰ\",\"amember\":\"304\",\"apeople_no\":\"1/10\",\"adescrip\":\"��׿���˽����ᣬ��Ҫ���Ŷ��Ҳ��ı�������뷨�������ﵮ��Ŷ��\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":301,\"aid\":9,\"atype\":1,\"aname\":\"�ٳ��ܲ���һ��\",\"atime\":\"����  9:30PM\",\"aposition\":\"�ٳ��ﾶ��\",\"amember\":\"301\",\"apeople_no\":\"1/5\",\"adescrip\":\"�����Ǹ����ı�Ǯ�������ң�������һ���ܲ���\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0}]";

	public static int Head_all[] = { R.drawable.random_head0,
			R.drawable.random_head1, R.drawable.random_head2,
			R.drawable.random_head3, R.drawable.random_head4,
			R.drawable.random_head5, R.drawable.random_head6,
			R.drawable.random_head7, R.drawable.random_head8,
			R.drawable.random_head9, R.drawable.random_head10,
			R.drawable.random_head11, R.drawable.random_head12,
			R.drawable.random_head13, R.drawable.random_head14,
			R.drawable.random_head15 };

	public static List<Photo> mPhoto = new ArrayList<Photo>();

	

	static {
		/*Photo(int pid, String url, int uid, String date, String title,
				String information, int like_max, int comment_max)*/
		mPhoto.add(new Photo(1,"url",1,"5��30��","����������","aaa",27,15));
		mPhoto.add(new Photo(1,"url",1,"5��25��","���Ǿ�������","aaa",21,6));
		mPhoto.add(new Photo(1,"url",1,"5��26��","�������ڴ�","aaa",16,4));
		mPhoto.add(new Photo(1,"url",1,"6��3��","�ƺ����Ե�־��","aaa",11,3));
		mPhoto.add(new Photo(1,"url",1,"6��9��","�������� ���ֵ�С2B","aaa",9,2));
		/*mPhoto.add(new Photo(111, "urls", new User(), new Date(), "nothing",
				new ArrayList<Comments>()));
		mPhoto.add(new Photo(111, "urls", new User(), new Date(), "nothing",
				new ArrayList<Comments>()));
		mPhoto.add(new Photo(111, "urls", new User(), new Date(), "nothing",
				new ArrayList<Comments>()));
		mPhoto.add(new Photo(111, "urls", new User(), new Date(), "nothing",
				new ArrayList<Comments>()));
		mPhoto.add(new Photo(111, "urls", new User(), new Date(), "nothing",
				new ArrayList<Comments>()));*/

	}
	


	public static  List<Photo> PhotoDailyHot = new ArrayList<Photo>();

	
	public static  List<String> Page1_Url = new ArrayList<String>();
	
	public static  List<Photo> PhotoFriend = new ArrayList<Photo>();
	
	public static  List<Photo> PhotoWanted = new ArrayList<Photo>();
	
	public static  String RecommendWeek[] = new String[]{"","","","",""};
	
	public static  Bitmap Current_bmp = null;
}
