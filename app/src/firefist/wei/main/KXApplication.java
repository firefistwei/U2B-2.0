package firefist.wei.main;

import java.io.IOException; 
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import firefist.wei.main.result.HomeResult;
import firefist.wei.utils.PhotoUtil;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * ��Ź��е�����
 * 
 * @author firefist_wei
 * 
 */


public class KXApplication extends Application {

	/**
	 * Ĭ�ϱ�ֽ
	 */
	public Bitmap mDefault_Wallpager;
	/**
	 * Ĭ�ϱ����ֽ
	 */
	public Bitmap mDefault_TitleWallpager;
	/**
	 * Ĭ��ͷ��
	 */
	public Bitmap mDefault_Avatar;
	/**
	 * Ĭ����Ƭ
	 */
	public Bitmap mDefault_Photo;
	/**
	 * ��ֽ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mWallpagersCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ��ֽ����
	 */
	public String[] mWallpagersName;
	/**
	 * �����ֽ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mTitleWallpagersCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * �����ֽ����
	 */
	public String[] mTitleWallpagersName;
	/**
	 * ��ǰ��ֽ���
	 */
	public int mWallpagerPosition = 0;
	/**
	 * Բ��ͷ�񻺴�
	 */
	public HashMap<String, SoftReference<Bitmap>> mAvatarCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * Ĭ��ͷ�񻺴�
	 */
	public HashMap<String, SoftReference<Bitmap>> mDefaultAvatarCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ͷ������
	 */
	public String[] mAvatars = {"f1","f2","f3","f4","f5","f6","f7","f8","f9","f10","f11","f12","f13","f14","f15","f16"
			,"f17","f18","f19"};
	/**
	 * ������ҳͷ�񻺴�
	 */
	public HashMap<String, SoftReference<Bitmap>> mPublicPageAvatarCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ������ҳͷ������
	 */
	public String[] mPublicPageAvatars;
	/**
	 * ����
	 */
	public int[] mFaces = { };
	/**
	 * ��������
	 */
	public List<String> mFacesText = new ArrayList<String>();
	/**
	 * ���黺��
	 */
	public HashMap<String, SoftReference<Bitmap>> mFaceCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ��Ƭ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mPhotoCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ��Ƭ����
	 */
	public String[] mPhotosName;
	/**
	 * ת��ͼƬ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mViewedCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ת��ͼƬ����
	 */
	public String[] mViewedName;
	/**
	 * ����ת��ͼƬ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mViewedHotCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ����ת��ͼƬ����
	 */
	public String[] mViewedHotName;
	/**
	 * ��ϷͼƬ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mRecommendCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ������Ƭ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mNearbyPhoto = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * ��ҳͼƬ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mHomeCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * �ֻ�SD��ͼƬ����
	 */
	public HashMap<String, SoftReference<Bitmap>> mPhoneAlbumCache = new HashMap<String, SoftReference<Bitmap>>();
	/**
	 * �ֻ�SD��ͼƬ��·��
	 */
	public Map<String, List<Map<String, String>>> mPhoneAlbum = new HashMap<String, List<Map<String, String>>>();

	/**
	 * �����û���ҳ����
	 */
	public static List<HomeResult> mMyHomeResults = new ArrayList<HomeResult>();
	/**
	 * ��ǰ�û�����������
	 *//*
	public FriendInfoResult mMyInfoResult;

	*//**
	 * ��ǰ�û�����������
	 *//*
	public List<VisitorsResult> mMyVisitorsResults = new ArrayList<VisitorsResult>();
	*//**
	 * ��ǰ�û���״̬����
	 *//*
	public List<StatusResult> mMyStatusResults = new ArrayList<StatusResult>();
	*//**
	 * ��ǰ�û����������
	 *//*
	public List<PhotoResult> mMyPhotoResults = new ArrayList<PhotoResult>();
	*//**
	 * ��ǰ�û����ռ�����
	 *//*
	public List<DiaryResult> mMyDiaryResults = new ArrayList<DiaryResult>();
	*//**
	 * ��ǰ�û��ĺ�������
	 *//*
	public List<FriendsResult> mMyFriendsResults = new ArrayList<FriendsResult>();
	*//**
	 * ��ǰ�û��ĺ��Ѹ�����������ĸ����
	 *//*
	public Map<String, List<FriendsResult>> mMyFriendsGroupByFirstName = new HashMap<String, List<FriendsResult>>();*/
	/**
	 * ��ǰ�û��ĺ��ѵ���������ĸ���б��е�λ��
	 */
	public Map<String, Integer> mMyFriendsFirstNamePosition = new HashMap<String, Integer>();
	/**
	 * ��ǰ�û��ĺ��ѵ�����������ĸ����
	 */
	public List<String> mMyFriendsFirstName = new ArrayList<String>();
	/**
	 * ��ǰ�û��ĺ��ѵ�����������ĸ�����б��е�λ��
	 */
	public List<Integer> mMyFriendsPosition = new ArrayList<Integer>();

	/**
	 * ��ǰ�û��Ĺ�����ҳ����
	 */
	/*public List<PublicPageResult> mMyPublicPageResults = new ArrayList<PublicPageResult>();
	*//**
	 * ��ǰ�û��Ĺ�����ҳ������������ĸ����
	 *//*
	public Map<String, List<PublicPageResult>> mMyPublicPageGroupByFirstName = new HashMap<String, List<PublicPageResult>>();

	*//**
	 * ��ǰ�û��ĺ���ת������
	 *//*
	public List<ViewedResult> mMyViewedResults = new ArrayList<ViewedResult>();

	*//**
	 * ��ǰ�û�������ת������
	 *//*
	public List<ViewedResult> mViewedHotResults = new ArrayList<ViewedResult>();

	*//**
	 * ��ǰ�û�����������պ�������
	 *//*
	public List<FriendsBirthdayResult> mMyFriendsBirthdayResults = new ArrayList<FriendsBirthdayResult>();

	*//**
	 * ��ǰ�û����Ƽ��ٷ�ģ������
	 *//*
	public List<RecommendResult> mMyRecommendOfficialResults = new ArrayList<RecommendResult>();
	*//**
	 * ��ǰ�û����Ƽ�Ӧ����������
	 *//*
	public List<RecommendResult> mMyRecommendAppDownLoadResults = new ArrayList<RecommendResult>();

	*//**
	 * ��ǰ�û��ĸ�����������
	 *//*
	public List<NearbyPeopleResult> mMyNearByPeopleResults = new ArrayList<NearbyPeopleResult>();

	*//**
	 * ��ǰ�û��ĸ�������Ƭ����
	 *//*
	public List<NearbyPhotoResult> mMyNearbyPhotoResults = new ArrayList<NearbyPhotoResult>();

	*//**
	 * ��ǰ�û��ĵ���λ������
	 *//*
	public List<LocationResult> mMyLocationResults = new ArrayList<LocationResult>();

	*//**
	 * ���к��ѵ��������� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, FriendInfoResult> mFriendInfoResults = new HashMap<String, FriendInfoResult>();

	*//**
	 * ���к��ѵ��������� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, List<VisitorsResult>> mFriendVisitorsResults = new HashMap<String, List<VisitorsResult>>();

	*//**
	 * ���к��ѵ�״̬���� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, List<StatusResult>> mFriendStatusResults = new HashMap<String, List<StatusResult>>();

	*//**
	 * ���к��ѵ�������� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, List<PhotoResult>> mFriendPhotoResults = new HashMap<String, List<PhotoResult>>();

	*//**
	 * ���к��ѵ��ռ����� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, List<DiaryResult>> mFriendDiaryResults = new HashMap<String, List<DiaryResult>>();

	*//**
	 * ���к��ѵĺ������� (Key��Ӧ�ú��ѵ�uid)
	 *//*
	public Map<String, List<FriendsResult>> mFriendFriendsResults = new HashMap<String, List<FriendsResult>>();

	*//**
	 * ��������¼
	 *//*
	public List<ChatResult> mChatResults = new ArrayList<ChatResult>();*/

	/**
	 * �����������ĺ���
	 */
	public Map<String, Map<String, String>> mGiftFriendsList = new HashMap<String, Map<String, String>>();

	/**
	 * �������ͼƬ
	 */
	public HashMap<String, SoftReference<Bitmap>> mGiftsCache = new HashMap<String, SoftReference<Bitmap>>();

	public String[] mGiftsName;
	/**
	 * �������ľ�����Ϣ
	 */
//	public List<GiftResult> mGiftResults = new ArrayList<GiftResult>();

	/**
	 * ��Ŵ�Ϊ�ݸ���ռǱ���
	 */
	public String mDraft_DiaryTitle;
	/**
	 * ��Ŵ�Ϊ�ݸ���ռ�����
	 */
	public String mDraft_DiaryContent;

	/**
	 * ��������ϴ�����Ƭ·��
	 */
	public String mUploadPhotoPath;
	/**
	 * ��ű���ѡȡ����Ƭ����
	 */
	public List<Map<String, String>> mAlbumList = new ArrayList<Map<String, String>>();

	public void onCreate() {
		super.onCreate();
		/**
		 * ��ʼ��Ĭ������
		 */
		mDefault_Wallpager = BitmapFactory.decodeResource(getResources(),
				R.drawable.cover);
		mDefault_TitleWallpager = BitmapFactory.decodeResource(getResources(),
				R.drawable.cover_title);
		mDefault_Photo = BitmapFactory.decodeResource(getResources(),
				R.drawable.photo);
		mDefault_Avatar = PhotoUtil.toRoundCorner(
				BitmapFactory.decodeResource(getResources(), R.drawable.head),
				15);
		mWallpagerPosition = (int) (Math.random() * 12);
		/**
		 * ��ʼ�����е�������Ϣ
		 */
/*		try {
			mWallpagersName = getAssets().list("wallpaper");
			mTitleWallpagersName = getAssets().list("title_wallpager");
			mAvatars = getAssets().list("avatar");
			mPublicPageAvatars = getAssets().list("publicpage_avatar");
			mPhotosName = getAssets().list("photo");
			mViewedName = getAssets().list("viewed");
			mViewedHotName = getAssets().list("viewed_hot");
			mGiftsName = getAssets().list("gifts");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/**
		 * ��ʼ����������
		 */
		for (int i = 0; i < mFaces.length; i++) {
			mFacesText.add("[face_" + i + "]");
		}
	}

	/**
	 * ���ݱ�ֽ��Ż�ȡ��ֽ
	 */
	public Bitmap getWallpager(int position) {
		try {
			String wallpagerName = mWallpagersName[position];
			Bitmap bitmap = null;
			if (mWallpagersCache.containsKey(wallpagerName)) {
				SoftReference<Bitmap> reference = mWallpagersCache
						.get(wallpagerName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"wallpaper/" + wallpagerName));
			mWallpagersCache.put(wallpagerName, new SoftReference<Bitmap>(
					bitmap));
			return bitmap;

		} catch (Exception e) {
			return mDefault_Wallpager;
		}
	}

	/**
	 * ���ݱ�ֽ��Ż�ȡ�����ֽ
	 */
	public Bitmap getTitleWallpager(int position) {
		try {
			String titleWallpagerName = mTitleWallpagersName[position];
			Bitmap bitmap = null;
			if (mTitleWallpagersCache.containsKey(titleWallpagerName)) {
				SoftReference<Bitmap> reference = mTitleWallpagersCache
						.get(titleWallpagerName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"title_wallpager/" + titleWallpagerName));
			mTitleWallpagersCache.put(titleWallpagerName,
					new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return mDefault_TitleWallpager;
		}
	}

	/**
	 * ����ͼƬ���ƻ�ȡ��ҳͼƬ
	 */
	public Bitmap getHome(String photo) {
		try {
			String homeName = photo + ".jpg";
			Bitmap bitmap = null;
			if (mHomeCache.containsKey(homeName)) {
				SoftReference<Bitmap> reference = mHomeCache.get(homeName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"home/" + homeName));
			mHomeCache.put(homeName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory.decodeResource(getResources(),
					R.drawable.picture_default_fg);
		}
	}

	/**
	 * ���ݱ�Ż�ȡ�û�Բ��ͷ��
	 */
	public Bitmap getAvatar(int position) {
		try {
			String avatarName = mAvatars[position];
			Bitmap bitmap = null;
			if (mAvatarCache.containsKey(avatarName)) {
				SoftReference<Bitmap> reference = mAvatarCache.get(avatarName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = PhotoUtil.toRoundCorner(
					BitmapFactory.decodeStream(getAssets().open(
							"avatar/" + avatarName)), 15);
			mAvatarCache.put(avatarName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return mDefault_Avatar;
		}
	}

	/**
	 * ���ݱ�Ż�ȡ�û�Ĭ��ͷ��
	 */
	public Bitmap getDefaultAvatar(int position) {
		try {
			String avatarName = mAvatars[position];
			Bitmap bitmap = null;
			if (mDefaultAvatarCache.containsKey(avatarName)) {
				SoftReference<Bitmap> reference = mDefaultAvatarCache
						.get(avatarName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"avatar/" + avatarName));
			mDefaultAvatarCache.put(avatarName, new SoftReference<Bitmap>(
					bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory
					.decodeResource(getResources(), R.drawable.head);
		}
	}

	/**
	 * ���ݱ�Ż�ȡ������ҳͷ��
	 */
	public Bitmap getPublicPageAvatar(int position) {
		try {
			String avatarName = mPublicPageAvatars[position];
			Bitmap bitmap = null;
			if (mPublicPageAvatarCache.containsKey(avatarName)) {
				SoftReference<Bitmap> reference = mPublicPageAvatarCache
						.get(avatarName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = PhotoUtil.toRoundCorner(
					BitmapFactory.decodeStream(getAssets().open(
							"publicpage_avatar/" + avatarName)), 15);
			mPublicPageAvatarCache.put(avatarName, new SoftReference<Bitmap>(
					bitmap));
			return bitmap;
		} catch (Exception e) {
			return mDefault_Avatar;
		}
	}

	/**
	 * ���ݱ�Ż�ȡ��Ƭ
	 */
	public Bitmap getPhoto(int position) {
		try {
			String photosName = mPhotosName[position];
			Bitmap bitmap = null;
			if (mPhotoCache.containsKey(photosName)) {
				SoftReference<Bitmap> reference = mPhotoCache.get(photosName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"photo/" + photosName));
			mPhotoCache.put(photosName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return mDefault_Photo;
		}
	}

	/**
	 * ���ݱ�Ż�ȡת��ͼƬ
	 */
	public Bitmap getViewed(int position) {
		try {
			String viewedName = mViewedName[position];
			Bitmap bitmap = null;
			if (mViewedCache.containsKey(viewedName)) {
				SoftReference<Bitmap> reference = mViewedCache.get(viewedName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"viewed/" + viewedName));
			mViewedCache.put(viewedName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory.decodeResource(getResources(),
					R.drawable.picture_default_fg);
		}
	}

	/**
	 * ���ݱ�Ż�ȡ����ת��ͼƬ
	 */
	public Bitmap getViewedHot(int position) {
		try {
			String viewedHotName = mViewedHotName[position];
			Bitmap bitmap = null;
			if (mViewedHotCache.containsKey(viewedHotName)) {
				SoftReference<Bitmap> reference = mViewedHotCache
						.get(viewedHotName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"viewed_hot/" + viewedHotName));
			mViewedHotCache.put(viewedHotName,
					new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory.decodeResource(getResources(),
					R.drawable.picture_default_fg);
		}
	}

	/**
	 * ���ݱ�����ƻ�ȡ��ϷͼƬ
	 */
	public Bitmap getRecommend(String position) {
		try {
			String recommendName = "icon_" + position + ".jpg";
			Bitmap bitmap = null;
			if (mRecommendCache.containsKey(recommendName)) {
				SoftReference<Bitmap> reference = mRecommendCache
						.get(recommendName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"recommend/" + recommendName));
			mRecommendCache.put(recommendName,
					new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ����ͼƬ���ƻ�ȡ������Ƭ��ͼƬ
	 */
/*	public Bitmap getNearbyPhoto(String position) {
		try {
			String nearbyPhotoName = position + ".jpg";
			Bitmap bitmap = null;
			if (mNearbyPhoto.containsKey(nearbyPhotoName)) {
				SoftReference<Bitmap> reference = mNearbyPhoto
						.get(nearbyPhotoName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets().open(
					"nearby_photo/" + nearbyPhotoName));
			mNearbyPhoto
					.put(nearbyPhotoName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory.decodeResource(getResources(),
					R.drawable.lbs_checkin_photo_icon);
		}
	}*/

	/**
	 * ���ݱ�Ż�ȡ����ͼƬ
	 */
	public Bitmap getFaceBitmap(int position) {
		try {
			String faceName = mFacesText.get(position);
			Bitmap bitmap = null;
			if (mFaceCache.containsKey(faceName)) {
				SoftReference<Bitmap> reference = mFaceCache.get(faceName);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeResource(getResources(),
					mFaces[position]);
			mFaceCache.put(faceName, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ���������Ż�ȡ����ͼƬ
	 */
/*	public Bitmap getGift(String gid) {
		try {
			Bitmap bitmap = null;
			if (mGiftsCache.containsKey(gid)) {
				SoftReference<Bitmap> reference = mGiftsCache.get(gid);
				bitmap = reference.get();
				if (bitmap != null) {
					return bitmap;
				}
			}
			bitmap = BitmapFactory.decodeStream(getAssets()
					.open("gifts/" + gid));
			mGiftsCache.put(gid, new SoftReference<Bitmap>(bitmap));
			return bitmap;
		} catch (Exception e) {
			return BitmapFactory.decodeResource(getResources(),
					R.drawable.gifts_default_01);
		}
	}*/

	/**
	 * ���ݵ�ַ��ȡ�ֻ�SD��ͼƬ
	 */
	public Bitmap getPhoneAlbum(String path) {
		Bitmap bitmap = null;
		if (mPhoneAlbumCache.containsKey(path)) {
			SoftReference<Bitmap> reference = mPhoneAlbumCache.get(path);
			bitmap = reference.get();
			if (bitmap != null) {
				return bitmap;
			}
		}
		bitmap = BitmapFactory.decodeFile(path);
		mPhoneAlbumCache.put(path, new SoftReference<Bitmap>(bitmap));
		return bitmap;
	}
}

