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

	// 上传照片的路径
	public static String UploadPhotoPath = null;
	
	

	public static List<FriendsPhoto> mFriendsPhoto = new ArrayList<FriendsPhoto>();

	static {
		mFriendsPhoto
				.add(new FriendsPhoto(
						2,
						"黑胡椒",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6月20日",
						"基情无处不在",
						"你们不要再黑这对幸福的基友了",
						5,7,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						1,
						"门骑",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6月19日",
						"自拍可耻，卖2无罪",
						"",
						3,5,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						1,
						"门骑",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"6月18日",
						"东哥发威了",
						"",
						1,4,
						"https://lh5.googleusercontent.com/-Pxa7eqF4cyc/T3R4oasvPEI/AAAAAAAAAF0/-uYDH92h8LA/s1024/sample_image_09.jpg"));
		mFriendsPhoto
				.add(new FriendsPhoto(
						2,
						"黑胡椒",
						"https://lh6.googleusercontent.com/-jZgveEqb6pg/T3R4kXScycI/AAAAAAAAAE0/xQ7CvpfXDzc/s1024/sample_image_01.jpg",
						"2013年5月20日",
						"落实 基 本国策",
						"又是幸福的一对儿",
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
	public static String activeJSON = "[{\"uid\":301,\"aid\":17,\"atype\":1,\"aname\":\"3+3随即好友下午茶\",\"atime\":\"今天  4:20PM\",\"aposition\":\"后花园奶茶店\",\"amember\":\"301+302+303\",\"apeople_no\":\"1/10\",\"adescrip\":\"别宅了，出来聊聊，认识新的朋友.\\r\\n3+3是一个随机陌生人交流的活动，要开始这个活动必须要3个男生 和3个女生，主要可以就某个话题进行讨论，从而让宅宅们脱离使他们变宅的环境，到线下与别人面对面交流。\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":302,\"aid\":11,\"atype\":1,\"aname\":\"魔方高手挑战\",\"atime\":\"今天  20:20\",\"aposition\":\"1310教室\",\"amember\":\"302\",\"apeople_no\":\"1/2\",\"adescrip\":\"没错，我是魔方社长，求挑战！\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":303,\"aid\":5,\"atype\":1,\"aname\":\"乒乓球高手挑战\",\"atime\":\"今天  20:20\",\"aposition\":\"操场乒乓球处\",\"amember\":\"303\",\"apeople_no\":\"1/2\",\"adescrip\":\"谁要打乒乓球，呵呵，求高手MM,呵呵，球拍自带，呵呵\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":304,\"aid\":3,\"atype\":1,\"aname\":\"android大神交流会\",\"atime\":\"今天  8:20PM\",\"aposition\":\"1号教学楼前\",\"amember\":\"304\",\"apeople_no\":\"1/10\",\"adescrip\":\"安卓达人交流会，不要错过哦，也许改变世界的想法就在这里诞生哦！\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0},"
			+ "{\"uid\":301,\"aid\":9,\"atype\":1,\"aname\":\"操场跑步求一起\",\"atime\":\"今天  9:30PM\",\"aposition\":\"操场田径场\",\"amember\":\"301\",\"apeople_no\":\"1/5\",\"adescrip\":\"身体是革命的本钱，加入我，和我们一起跑步。\",\"alongi\":\"108.876082087\",\"alatitude\":\"34.1502375676\",\"isfull\":0,\"isfinished\":0}]";

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
		mPhoto.add(new Photo(1,"url",1,"5月30日","龟派气功波","aaa",27,15));
		mPhoto.add(new Photo(1,"url",1,"5月25日","你们尽管来吧","aaa",21,6));
		mPhoto.add(new Photo(1,"url",1,"5月26日","武媚娘在此","aaa",16,4));
		mPhoto.add(new Photo(1,"url",1,"6月3日","酒后乱性的志哥","aaa",11,3));
		mPhoto.add(new Photo(1,"url",1,"6月9日","哈利破特 快乐的小2B","aaa",9,2));
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
