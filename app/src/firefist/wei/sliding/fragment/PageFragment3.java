package firefist.wei.sliding.fragment;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import firefist.wei.main.activity.ImagePagerActivity;
import firefist.wei.main.domain.Photo;
import firefist.wei.main.widget.RefreshListView;

/**
 * 本周通缉
 * 
 */
public class PageFragment3 extends Fragment implements
		RefreshListView.IOnLoadMoreListener {

	public ImageLoader imageLoader = ImageLoader.getInstance();

	private View headView;
	private TextView header_tv;
	private Button header_btn;

	private Context mContext;
	private RefreshListView mListView;
	private MyAdapter mAdapter;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	private List<Photo> photos = null;
	private int pos = 0;

	DisplayImageOptions options;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.page3_fragment, null);

		mContext = this.getActivity();
		headView = LayoutInflater.from(mContext).inflate(R.layout.page3_header,
				null);

		mListView = (RefreshListView) view.findViewById(R.id.page3_listview);
		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		initHead();
		init();

		initImageLoader(mContext);
	}

	private void initHead() {

		header_tv = (TextView) headView.findViewById(R.id.page3_header_tv);
		header_btn = (Button) headView.findViewById(R.id.page3_header_btn);

		/**
		 * http 改变tv的显示
		 */

		header_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

	private void init() {

		mListView.addHeaderView(headView);

		photos = new ArrayList<Photo>();
		photos = MyConstants.mPhoto;
		/**
		 * http 获取 photos
		 */
		mAdapter = new MyAdapter(this.getActivity(), photos);
		mListView.setAdapter(mAdapter);
		mListView.setOnLoadMoreListener(this);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(mContext, ImagePagerActivity.class);
				startActivity(intent);

			}

		});

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

	@Override
	public void OnLoadMore() {
		mLoadMoreAsynTask = new LoadMoreDataAsynTask();
		mLoadMoreAsynTask.execute();

	}

	class MyAdapter extends BaseAdapter {

		private List<Photo> data = new ArrayList<Photo>();

		private Context mContext;

		public MyAdapter(Context context, List<Photo> data) {
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
						R.layout.page3_refresh_item, null);

				holder = new ViewHolder();

				holder.top = (TextView) convertView
						.findViewById(R.id.page3_item_top);
				holder.title = (TextView) convertView
						.findViewById(R.id.page3_item_title);
				holder.score = (TextView) convertView
						.findViewById(R.id.page3_item_score);
				holder.photo = (ImageView) convertView
						.findViewById(R.id.page3_item_img);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Photo mPhotos = photos.get(pos);

			holder.top.setText("Top " + (pos + 1));

			/*
			 * holder.friend_name.setText(mPhotos.get());
			 * holder.time.setText(friendPhoto.getP_time());
			 * holder.title.setText(friendPhoto.getP_title());
			 * holder.content.setText(friendPhoto.getP_content());
			 * 
			 * holder.comment_count.setText(friendPhoto.getComment_count() +
			 * ""); holder.like_count.setText(friendPhoto.getLike_count() + "");
			 */

			imageLoader.displayImage(MyConstants.IMAGES[pos], holder.photo,
					options, new SimpleImageLoadingListener() {

						@Override
						public void onLoadingStarted(String imageUri, View view) {

						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
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

						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {

						}
					});
			return convertView;
		}

		class ViewHolder {
			TextView top;
			TextView title;
			TextView score;
			ImageView photo;
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

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub

			mAdapter.refreshData(photos);

			if (pos > 10) {
				mListView.onLoadMoreComplete(true);
			} else {
				mListView.onLoadMoreComplete(false);
			}

		}

	}

}