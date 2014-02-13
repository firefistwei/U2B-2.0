package firefist.wei.main.right;

import firefist.wei.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

public class Right_Upload extends Activity{
	private TextView mTitle;
	private GridView mDisplay;
	private TextView mNoDisplay;
	
	private int mUid;// 照片所属的用户ID
	private String mName;// 照片所属的用户姓名
	private int mAvatar;// 照片所属的用户头像
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.right_upload_photo);
		
	    getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	    
		findViewById();
		/*setListener();
		init();*/
	}

	public void right_upload_back(View v){
		this.finish();
	}
	
	private void findViewById() {
		
		mDisplay = (GridView) findViewById(R.id.photo_display);
		mNoDisplay = (TextView) findViewById(R.id.photo_nodisplay);
	}

	/*private void setListener() {
		
		mDisplay.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 传递照片所属用户的ID、姓名、头像以及图片数据到图片列表类
				Intent intent = new Intent();
				intent.setClass(PhotoActivity.this, PhotoListActivity.class);
				intent.putExtra("uid", mUid);
				intent.putExtra("name", mName);
				intent.putExtra("avatar", mAvatar);
				// ID为空时为当前用户
				if (mUid ==0) {
					intent.putExtra("result",
							MyConstants.mMyPhotoResults.get(arg2));
				} else {
					intent.putExtra(
							"result",
							MyConstants.mFriendPhotoResults.get(mUid).get(
									arg2));
				}
				startActivity(intent);
			}
		});
	}

	private void init() {
		// 获取照片所属用户的ID、姓名、头像
		mUid = getIntent().getIntExtra("uid", 0);
		mName = getIntent().getStringExtra("name");
		mAvatar = getIntent().getIntExtra("avatar", -1);
		// ID为空时代表为当前用户,根据用户的不同 显示不同的内容
		if (mUid == 0) {
			mTitle.setText("个人相册");
			getPhotos();
			mDisplay.setAdapter(new PhotoAdapter(MyConstants.mMyPhotoResults));
		} else {
			
			mTitle.setText(mName + "的相册");
			getPhotos();
			mDisplay.setAdapter(new PhotoAdapter(
					MyConstants.mFriendPhotoResults.get(mUid)));
		}

	}

	*//**
	 * 获取照片数据
	 *//*
	private void getPhotos() {
		// ID为空时为当前用户数据,否则根据ID获取数据
		if (mUid == 0) {
			if (MyConstants.mMyPhotoResults.isEmpty()) {
				InputStream inputStream;
				try {
					inputStream = getAssets().open("data/my_photo.KX");
					String json = new TextUtil()
							.readTextFile(inputStream);
					getPhotos(json);
				} catch (IOException e) {
					mDisplay.setVisibility(View.GONE);
					mNoDisplay.setVisibility(View.VISIBLE);
				}
			}
		} else {
			if (!mKXApplication.mFriendPhotoResults.containsKey(mUid)) {
				InputStream inputStream;
				try {
					inputStream = getAssets()
							.open("data/" + mUid + "_photo.KX");
					String json = new TextUtil(mKXApplication)
							.readTextFile(inputStream);
					getPhotos(json);
				} catch (IOException e) {
					e.printStackTrace();
					mDisplay.setVisibility(View.GONE);
					mNoDisplay.setVisibility(View.VISIBLE);
				}
			}
		}
	}

	*//**
	 * 解析图片数据
	 * 
	 * @param json
	 *//*
	private void getPhotos(String json) {
		try {
			JSONArray array = new JSONArray(json);
			PhotoResult result = null;
			List<PhotoResult> list = new ArrayList<PhotoResult>();
			for (int i = 0; i < array.length(); i++) {
				result = new PhotoResult();
				result.setPid(array.getJSONObject(i).getString("pid"));
				result.setImage(array.getJSONObject(i).getInt("image"));
				result.setTitle(array.getJSONObject(i).getString("title"));
				result.setCount(array.getJSONObject(i).getInt("count"));
				result.setTime(array.getJSONObject(i).getString("time"));
				result.setType(array.getJSONObject(i).getInt("type"));
				JSONArray imagesArray = array.getJSONObject(i).getJSONArray(
						"images");
				List<PhotoDetailResult> images = new ArrayList<PhotoDetailResult>();
				for (int j = 0; j < imagesArray.length(); j++) {
					PhotoDetailResult photoDetailResult = new PhotoDetailResult();
					photoDetailResult.setImage(imagesArray.getJSONObject(j)
							.getInt("image"));
					photoDetailResult.setTime(imagesArray.getJSONObject(j)
							.getString("time"));
					photoDetailResult.setDescription(imagesArray.getJSONObject(
							j).getString("description"));
					if (imagesArray.getJSONObject(j).has("comment_count")) {
						photoDetailResult.setComment_count(imagesArray
								.getJSONObject(j).getInt("comment_count"));
					}
					if (imagesArray.getJSONObject(j).has("like_count")) {
						photoDetailResult.setLike_count(imagesArray
								.getJSONObject(j).getInt("like_count"));
					}
					List<Map<String, Object>> comments = new ArrayList<Map<String, Object>>();
					if (imagesArray.getJSONObject(j).has("comments")) {
						JSONArray commentsArray = imagesArray.getJSONObject(j)
								.getJSONArray("comments");
						for (int k = 0; k < commentsArray.length(); k++) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("uid", commentsArray.getJSONObject(k)
									.getString("uid"));
							map.put("avatar", commentsArray.getJSONObject(k)
									.getString("avatar"));
							map.put("name", commentsArray.getJSONObject(k)
									.getString("name"));
							map.put("time", commentsArray.getJSONObject(k)
									.getString("time"));
							map.put("content", commentsArray.getJSONObject(k)
									.getString("content"));
							if (commentsArray.getJSONObject(k).has("replys")) {
								JSONArray replysArray = commentsArray
										.getJSONObject(k)
										.getJSONArray("replys");
								List<Map<String, String>> replysResults = new ArrayList<Map<String, String>>();
								for (int l = 0; l < replysArray.length(); l++) {
									Map<String, String> replyMap = new HashMap<String, String>();
									replyMap.put("uid", replysArray
											.getJSONObject(l).getString("uid"));
									replyMap.put("avatar",
											replysArray.getJSONObject(l)
													.getString("avatar"));
									replyMap.put("name", replysArray
											.getJSONObject(l).getString("name"));
									replyMap.put("time", replysArray
											.getJSONObject(l).getString("time"));
									replyMap.put("content",
											replysArray.getJSONObject(l)
													.getString("content"));
									replysResults.add(replyMap);
								}
								map.put("replys", replysResults);
							}
							comments.add(map);
						}
						photoDetailResult.setComments(comments);
						images.add(photoDetailResult);
					} else {
						photoDetailResult.setComments(comments);
						images.add(photoDetailResult);
					}
				}

				result.setImages(images);
				list.add(result);
			}
			if (mUid == null) {
				mKXApplication.mMyPhotoResults = list;
			} else {
				mKXApplication.mFriendPhotoResults.put(mUid, list);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			mDisplay.setVisibility(View.GONE);
			mNoDisplay.setVisibility(View.VISIBLE);
		}
	}

	private class PhotoAdapter extends BaseAdapter {

		private List<PhotoResult> mResults = new ArrayList<PhotoResult>();

		public PhotoAdapter(List<PhotoResult> results) {
			if (results != null) {
				mResults = results;
			}
		}

		public int getCount() {
			return mResults.size();
		}

		public Object getItem(int position) {
			return mResults.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(PhotoActivity.this).inflate(
						R.layout.photo_activity_item, null);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.photo_item_img);
				int padding = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 40, PhotoActivity.this
								.getResources().getDisplayMetrics());
				LayoutParams params = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.width = (mScreenWidth - padding) / 3;
				params.height = (mScreenWidth - padding) / 3;
				holder.image.setLayoutParams(params);
				holder.title = (TextView) convertView
						.findViewById(R.id.photo_item_title);
				holder.time = (TextView) convertView
						.findViewById(R.id.photo_item_time);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			PhotoResult result = mResults.get(position);
			if (result.getType() == 0) {
				holder.image.setImageBitmap(mKXApplication.getAvatar(result
						.getImage()));
			} else {
				holder.image.setImageBitmap(mKXApplication.getPhoto(result
						.getImage()));
			}

			holder.title.setText(result.getTitle() + "(" + result.getCount()
					+ ")");
			holder.time.setText(result.getTime() + " 更新");
			return convertView;
		}

		class ViewHolder {
			ImageView image;
			TextView title;
			TextView time;
		}
	}*/


}
