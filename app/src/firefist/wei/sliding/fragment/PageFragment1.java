/*
 * Copyright (C) 2012 yueyueniao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package firefist.wei.sliding.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.activity.ImagePagerActivity;
import firefist.wei.main.activity.PhotoShowActivity;
import firefist.wei.main.domain.Photo;
import firefist.wei.main.service.MyService;
import firefist.wei.main.widget.FlowIndicator;
import firefist.wei.main.widget.RefreshListView;
import firefist.wei.sliding.adapter.GalleryAdapter;
import firefist.wei.utils.TextUtil;
import firefist.wei.utils.Utils;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * 热门推荐
 * 
 */
public class PageFragment1 extends Fragment implements
		RefreshListView.IOnRefreshListener, RefreshListView.IOnLoadMoreListener {

	public static List<Bitmap> Page1_Bmp = null;
	
	DisplayImageOptions options;
	List<String> DailyHotUrls = null;
	public ImageLoader imageLoader = ImageLoader.getInstance();

	String[] RecommendWeekUrls = null;

	static final int SCROLL_ACTION = 0;

	private RefreshListView mListView;
	private MyListViewAdapter mAdapter;
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	private List<Photo> Page1_Photos = null;
	private int pos = 0;

	private Context mContext;
	private View headView;

	Gallery mGallery;
	GalleryAdapter mGalleryAdapter;
	FlowIndicator mFlowIndicator;
	Timer mTimer;

	static int select_point = 0;

	boolean first = true;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page1_fragment, null);
		mListView = (RefreshListView) view.findViewById(R.id.page1_listview);

		mContext = this.getActivity();
		headView = LayoutInflater.from(mContext).inflate(
				R.layout.page1_headview, null);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		/*
		 * LinearLayout mLayout = (LinearLayout) (getActivity())
		 * .findViewById(R.id.page1_layout); LayoutInflater mInflater =
		 * LayoutInflater .from(mLayout.getContext()); View headView =
		 * mInflater.inflate(R.layout.page1_headview, null);
		 * mLayout.addView(headView);
		 */

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		Page1_Bmp = new ArrayList<Bitmap>();
		
		initGallery();

		initImageLoader(mContext);
		initList();

	}

	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging() // Not
																				// necessary
																				// in
																				// common
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}

	private void initList() {

		mListView.addHeaderView(headView);

		Page1_Photos = new ArrayList<Photo>();

		if (MyConstants.PhotoDailyHot.size() < 5) {
			getDailyHot();
		}
		// if (photos.size() == 0) { photos = MyConstants.mPhoto;
		/**
		 * http 获取 photos
		 */
		getDailyLast(0);

		mAdapter = new MyListViewAdapter(this.getActivity(), Page1_Photos);
		mListView.setAdapter(mAdapter);
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);

		mListView.setOnItemClickListener(new OnItemClickListener() {


			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				MyConstants.Current_bmp = Page1_Bmp.get(arg2);
				
				Intent intent = new Intent(mContext, PhotoShowActivity.class);
				intent.putExtra("page1_photo",MyConstants.PhotoDailyHot.get(arg2));
				
				
				startActivity(intent);
			}

		});
	}

	private void getDailyHot() {

		new Thread() {
			public void run() {
				Looper.prepare();
				InputStream inputStream;

				try {
					inputStream = MyService.getDailyHot();
					System.out.println(inputStream);
					String jsonString = Utils.readInputStream(inputStream);

					Log.i("What", jsonString);

					JSONObject jsonObject = new JSONObject(jsonString);
					JSONArray jsonArray = jsonObject.getJSONArray("hot");

					Photo result = null;
					Page1_Photos = new ArrayList<Photo>();

					DailyHotUrls = new ArrayList<String>();

					for (int i = 0; i < jsonArray.length(); i++) {
						result = new Photo();
						result.setPid(jsonArray.getJSONObject(i).getInt("pid"));
						result.setUrl(jsonArray.getJSONObject(i).getString(
								"url"));
						result.setUid(jsonArray.getJSONObject(i).getInt("uid"));
						result.setDate(jsonArray.getJSONObject(i).getString(
								"upload_date"));
						result.setTitle(jsonArray.getJSONObject(i).getString(
								"title"));
						result.setInformation(jsonArray.getJSONObject(i)
								.getString("description"));
						result.setLike_max(jsonArray.getJSONObject(i).getInt(
								"like_max"));
						result.setComment_max(jsonArray.getJSONObject(i)
								.getInt("comment_max"));

						MyConstants.PhotoDailyHot.add(result);
						MyConstants.Page1_Url.add(jsonArray.getJSONObject(i)
								.getString("url"));
					}
				} catch (Exception e) {
				}
				Page1_Photos = MyConstants.PhotoDailyHot;
				DailyHotUrls = MyConstants.Page1_Url;
				handler.sendEmptyMessage(0);
				Looper.loop();
			}
		}.start();

	}

	private void getDailyLast(final int offset) {

		new Thread() {
			public void run() {
				Looper.prepare();
				InputStream inputStream;

				try {
					inputStream = MyService.getDailyLast(offset);
					System.out.println(inputStream);
					String jsonString = Utils.readInputStream(inputStream);

					Log.i("What", jsonString);

					JSONObject jsonObject = new JSONObject(jsonString);
					JSONArray jsonArray = jsonObject.getJSONArray("last");

					Photo result = null;
					Page1_Photos = new ArrayList<Photo>();

					DailyHotUrls = new ArrayList<String>();

					for (int i = 0; i < jsonArray.length(); i++) {
						result = new Photo();
						result.setPid(jsonArray.getJSONObject(i).getInt("pid"));
						result.setUrl(jsonArray.getJSONObject(i).getString(
								"url"));
						result.setUid(jsonArray.getJSONObject(i).getInt("uid"));
						result.setDate(jsonArray.getJSONObject(i).getString(
								"upload_date"));
						result.setTitle(jsonArray.getJSONObject(i).getString(
								"title"));
						result.setInformation(jsonArray.getJSONObject(i)
								.getString("description"));
						result.setLike_max(jsonArray.getJSONObject(i).getInt(
								"like_max"));
						result.setComment_max(jsonArray.getJSONObject(i)
								.getInt("comment_max"));

						MyConstants.PhotoDailyHot.add(result);
						MyConstants.Page1_Url.add(jsonArray.getJSONObject(i)
								.getString("url"));
					}
				} catch (Exception e) {
					Page1_Photos = new ArrayList<Photo>();
				}
				Page1_Photos = MyConstants.PhotoDailyHot;
				DailyHotUrls = MyConstants.Page1_Url;
				handler.sendEmptyMessage(0);
				Looper.loop();
			}
		}.start();

	}

	private void initGallery() {
		mGallery = (Gallery) headView.findViewById(R.id.home_gallery);
		mFlowIndicator = (FlowIndicator) headView
				.findViewById(R.id.myFlowIndicator);

		// getRecommendWeek();

		RecommendWeekUrls = MyConstants.RecommendWeek;
		if (MyConstants.RecommendWeek[0].equals("")) {
			mGalleryAdapter = new GalleryAdapter(mContext);
		} else {

			mGalleryAdapter = new GalleryAdapter(mContext, RecommendWeekUrls);
		}

		// 初始化GelleryAdapter(context,urls)，这里传入数据
		/**
		 * 
		 */

		mFlowIndicator.setCount(mGalleryAdapter.getCount());

		mGallery.setAdapter(mGalleryAdapter);

		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mFlowIndicator.setSeletion(position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}

		});

		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(new MyTask(), 0, 5000);

	}

	private void getRecommendWeek() {
		InputStream inputStream;
		try {
			inputStream = MyService.getDailyHot();
			String json = new TextUtil().readTextFile(inputStream);

			JSONArray array = new JSONArray(json);
			System.out.println(json);

			for (int i = 0; i < array.length(); i++) {
				RecommendWeekUrls[i] = array.getJSONObject(i).getString("url");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class MyTask extends TimerTask {
		@Override
		public void run() {
			mHandler.sendEmptyMessage(SCROLL_ACTION);

			select_point = mFlowIndicator.getSelection();

		}
	}

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if (mAdapter != null) {
					mAdapter.refreshData(Page1_Photos);
				}
				break;

			}
		};
	};

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SCROLL_ACTION:

				MotionEvent e1 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN,
						89.333336f, 265.33334f, 0);
				MotionEvent e2 = MotionEvent.obtain(SystemClock.uptimeMillis(),
						SystemClock.uptimeMillis(), MotionEvent.ACTION_UP,
						300.0f, 238.00003f, 0);

				mGallery.onFling(e1, e2, -950, 0);
				// mGallery.onFling(e1, e2, -1000, 0);

				if (select_point == (mGalleryAdapter.getCount() - 1)) {
					mGallery.setSelection(0);
					select_point = 0;
				}
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void OnLoadMore() {

		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute();
	}

	@Override
	public void OnRefresh() {
		mRefreshAsynTask = new RefreshDataAsynTask();
		mRefreshAsynTask.execute();

	}

	class MyListViewAdapter extends BaseAdapter {

		private List<Photo> data = new ArrayList<Photo>();

		private Context mContext;
		ProgressBar progressBar = null;

		public MyListViewAdapter(Context context, List<Photo> data) {
			mContext = context;
			this.data = data;
		}

		public void refreshData(List<Photo> data) {
			this.data = data;
			notifyDataSetChanged();

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int pos) {
			// TODO Auto-generated method stub
			return data.get(pos);
		}

		@Override
		public long getItemId(int pos) {
			// TODO Auto-generated method stub
			return pos;
		}

		@Override
		public View getView(int pos, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.page1_refresh_item, null);

				holder = new ViewHolder();

				holder.top = (TextView) convertView
						.findViewById(R.id.page1_item_top_tv);
				holder.title = (TextView) convertView
						.findViewById(R.id.page1_item_title);
				holder.time = (TextView) convertView
						.findViewById(R.id.page1_item_time);
				holder.photo = (ImageView) convertView
						.findViewById(R.id.page1_item_photo);
				holder.zan = (TextView) convertView
						.findViewById(R.id.page1_item_zan);
				holder.comment = (TextView) convertView
						.findViewById(R.id.page1_item_comment);

				progressBar = (ProgressBar) convertView
						.findViewById(R.id.page1_progress);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Photo mPhoto = null;

			// if (photos.size() > pos + 1) {
			mPhoto = Page1_Photos.get(pos);

			if (pos < 5) {
				holder.top.setText("今日最热 Top" + (5 - pos));
			} else {
				holder.top.setText("最新上传");
			}

			holder.title.setText(mPhoto.getTitle());
			holder.time.setText(mPhoto.getDate());
			holder.zan.setText(mPhoto.getLike_max() + "");
			holder.comment.setText(mPhoto.getComment_max() + "");

			/*
			 * holder.friend_name.setText(mPhotos.get());
			 * holder.time.setText(friendPhoto.getP_time());
			 * holder.title.setText(friendPhoto.getP_title());
			 * holder.content.setText(friendPhoto.getP_content());
			 * 
			 * holder.comment_count.setText(friendPhoto.getComment_count() +
			 * ""); holder.like_count.setText(friendPhoto.getLike_count() + "");
			 */
			if (pos == 0) {
				holder.photo.setImageResource(R.drawable.page1_1);
			} else if (pos == 1) {
				holder.photo.setImageResource(R.drawable.page1_2);
			} else if (pos == 2) {
				holder.photo.setImageResource(R.drawable.page1_3);
			} else if (pos == 3) {
				holder.photo.setImageResource(R.drawable.page1_4);
			} else if (pos == 4) {
				holder.photo.setImageResource(R.drawable.page1_5);
			}

			if (DailyHotUrls != null) {
				imageLoader.displayImage(DailyHotUrls.get(pos), holder.photo,
						options, new SimpleImageLoadingListener() {

							@Override
							public void onLoadingStarted(String imageUri,
									View view) {
								progressBar.setVisibility(View.VISIBLE);
							}

							@Override
							public void onLoadingFailed(String imageUri,
									View view, FailReason failReason) {
								String message = null;
								switch (failReason.getType()) {
								case IO_ERROR:
									message = "Input/Output error";
									break;
								case DECODING_ERROR:
									message = "Image can't be decoded";
									break;
								case NETWORK_DENIED:
									message = "Downloads are denied";
									break;
								case OUT_OF_MEMORY:
									message = "Out Of Memory error";
									break;
								case UNKNOWN:
									message = "Unknown error";
									break;
								}
								Toast.makeText(mContext, message,
										Toast.LENGTH_SHORT).show();

								progressBar.setVisibility(View.GONE);
							}

							@Override
							public void onLoadingComplete(String imageUri,
									View view, Bitmap loadedImage) {
								progressBar.setVisibility(View.GONE);
								Page1_Bmp.add(loadedImage);
								
							}

						});
			}

			return convertView;

		}

		class ViewHolder {
			TextView top;
			TextView title;
			TextView time;
			ImageView photo;
			TextView zan;
			TextView comment;
		}

	}

	class RefreshDataAsynTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// List 的数据

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			mAdapter.refreshData(Page1_Photos);
			mListView.onRefreshComplete();
		}

	}

	class LoadMoreDataAsynTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// List加载数据
			getDailyLast(0);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			mAdapter.refreshData(Page1_Photos);

			if (pos > 0) {
				mListView.onLoadMoreComplete(true);
			} else {
				mListView.onLoadMoreComplete(false);
			}

		}

	}
}
