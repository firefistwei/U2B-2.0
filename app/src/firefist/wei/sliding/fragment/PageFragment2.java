package firefist.wei.sliding.fragment;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.activity.FindFriend;
import firefist.wei.main.activity.ImagePagerActivity;
import firefist.wei.main.domain.FriendsPhoto;
import firefist.wei.main.widget.RefreshListView;

/**
 * @奇葩好友
 * 
 */
public class PageFragment2 extends Fragment implements
		RefreshListView.IOnRefreshListener, RefreshListView.IOnLoadMoreListener {

	private Context mContext;
	private Activity mActivity;

	private LinearLayout friend2b_layout2;
	private TextView layout2_tv;
	private Button layout2_btn;

	private RefreshListView mListView;
	private RefreshDataAsynTask mRefreshAsynTask;
	private LoadMoreDataAsynTask mLoadMoreAsynTask;

	private LayoutInflater mInflater;

	public static File cache; // 在 Exit中清掉

	private List<FriendsPhoto> mPhoto = null;
	private FriendAdapter mAdapter;
	private int pos = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.friend2b_photo, null);

		mListView = (RefreshListView) view.findViewById(R.id.friend2b_list);

		/*friend2b_layout2 = (LinearLayout) view.findViewById(R.id.friend2b_layout2);
		layout2_tv = (TextView)view.findViewById(R.id.friend2b_tv);
		layout2_btn = (Button)view.findViewById(R.id.friend2b_btn);*/


		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mContext = this.getActivity();
		mActivity = this.getActivity();

		init();
		initList();
	}

	private void init() {
		/**
		 * 检测好友的个数
		 */

		/*// 如果不为零
		friend2b_layout2.setVisibility(View.INVISIBLE);
		layout2_tv.setVisibility(View.INVISIBLE);
		layout2_btn.setVisibility(View.INVISIBLE);

		
		 * //为零 friend2b_layout2.setVisibility(View.VISIBLE);
		 * layout2_tv.setVisibility(View.VISIBLE);
		 * layout2_btn.setVisibility(View.VISIBLE);
		 

		layout2_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, FindFriend.class);
				getActivity().startActivity(intent);

			}
		});*/

	}

	private void initList() {

		mPhoto = new ArrayList<FriendsPhoto>();
		// 获取数据
		getData();
		// 添加适配器
		mListView.setAdapter(new FriendAdapter(mContext, mPhoto));
		mListView.setOnRefreshListener(this);
		mListView.setOnLoadMoreListener(this);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(mContext,ImagePagerActivity.class);
				startActivity(intent);

			}

		});

		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if (!cache.exists())
			cache.mkdirs();

	}

	/**
	 * 获取数据
	 */
	private void getData() {

		mPhoto = MyConstants.mFriendsPhoto;

	}

	private class FriendAdapter extends BaseAdapter {

		private Context mContext;
		List<FriendsPhoto> photos = new ArrayList<FriendsPhoto>();

		public FriendAdapter(Context context, List<FriendsPhoto> mFriendsPhoto) {
			mContext = context;
			this.photos = mFriendsPhoto;
		}

		public void refreshData(List<FriendsPhoto> mFriendsPhoto) {
			this.photos = mFriendsPhoto;
			notifyDataSetChanged();

		}

		@Override
		public int getCount() {

			return photos.size();
		}

		@Override
		public Object getItem(int position) {

			return photos.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.friend2b_photo_item, null);
				holder = new ViewHolder();

				holder.head = (ImageView) convertView
						.findViewById(R.id.friend2b_item_head);
				holder.friend_name = (TextView) convertView
						.findViewById(R.id.friend2b_item_name);
				holder.time = (TextView) convertView
						.findViewById(R.id.friend2b_item_time);
				holder.title = (TextView) convertView
						.findViewById(R.id.friend2b_item_title);
				holder.content = (TextView) convertView
						.findViewById(R.id.friend2b_item_description);
				holder.photo = (ImageView) convertView
						.findViewById(R.id.friend2b_item_photo);

				holder.comment_count = (TextView) convertView
						.findViewById(R.id.friend2b_item_comment_count);
				holder.like_count = (TextView) convertView
						.findViewById(R.id.friend2b_item_like_count);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			FriendsPhoto friendPhoto = mPhoto.get(position);

			/*
			 * asyncImageLoad(holder.head, MyConstants.WebURL +
			 * friendPhoto.getF_headurl()); // holder.head.setImageBitmap(bm);
			 * asyncImageLoad(holder.photo, MyConstants.WebURL +
			 * friendPhoto.getPhoto_url()); // holder.photo.setImageBitmap(bm);
			 */
			holder.friend_name.setText(friendPhoto.getF_name());
			holder.time.setText(friendPhoto.getP_time());
			holder.title.setText(friendPhoto.getP_title());
			holder.content.setText(friendPhoto.getP_content());

			holder.comment_count.setText(friendPhoto.getComment_count() + "");
			holder.like_count.setText(friendPhoto.getLike_count() + "");
			
			if(position==0){
				holder.photo.setImageResource(R.drawable.page2_1);
			}else if(position==1){
				holder.photo.setImageResource(R.drawable.page2_2);
			}else if(position==2){
				holder.photo.setImageResource(R.drawable.page2_3);
			}else if(position==3){
				holder.photo.setImageResource(R.drawable.page2_4);
			}
			
			if(friendPhoto.getF_id()==1){
				holder.head.setImageResource(R.drawable.random_head3);
			}else{
				holder.head.setImageResource(R.drawable.random_head4);
			}

			return convertView;
		}

		class ViewHolder {

			TextView friend_name;
			ImageView head;
			TextView time;
			TextView title;
			TextView content;
			ImageView photo;

			TextView comment_count;
			TextView like_count;

		}

		/*
		 * private void asyncImageLoad(ImageView imageView, String path) {
		 * AsyncImageTask asyncImageTask = new AsyncImageTask(imageView);
		 * asyncImageTask.execute(path);
		 * 
		 * }
		 * 
		 * private final class AsyncImageTask extends AsyncTask<String, Integer,
		 * Uri> { private ImageView imageView;
		 * 
		 * public AsyncImageTask(ImageView imageView) { this.imageView =
		 * imageView; }
		 * 
		 * protected Uri doInBackground(String... params) {// 子线程中执行的 try {
		 * return firefist.wei.main.service.PhotosCategoryService
		 * .getImage(params[0], cache); } catch (Exception e) {
		 * e.printStackTrace(); } return null; }
		 * 
		 * protected void onPostExecute(Uri result) {// 运行在主线程
		 * 
		 * File _file = null; if (result != null && imageView != null) { try {
		 * _file = new File(new URI(result.toString())); } catch
		 * (URISyntaxException e) { e.printStackTrace(); }
		 * 
		 * BitmapFactory.Options opts = new BitmapFactory.Options();
		 * opts.inJustDecodeBounds = true;
		 * BitmapFactory.decodeFile(result.toString(), opts);
		 * 
		 * opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
		 * opts.inJustDecodeBounds = false; try { Bitmap bmp =
		 * BitmapFactory.decodeFile( result.toString(), opts);
		 * imageView.setImageBitmap(bmp); } catch (OutOfMemoryError err) { } } }
		 * }
		 */

		/*
		 * public int computeSampleSize(BitmapFactory.Options options, int
		 * minSideLength, int maxNumOfPixels) { int initialSize =
		 * computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
		 * 
		 * int roundedSize; if (initialSize <= 8) { roundedSize = 1; while
		 * (roundedSize < initialSize) { roundedSize <<= 1; } } else {
		 * roundedSize = (initialSize + 7) / 8 * 8; }
		 * 
		 * return roundedSize; }
		 */

		/*
		 * private int computeInitialSampleSize(BitmapFactory.Options options,
		 * int minSideLength, int maxNumOfPixels) { double w = options.outWidth;
		 * double h = options.outHeight;
		 * 
		 * int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
		 * .sqrt(w * h / maxNumOfPixels)); int upperBound = (minSideLength ==
		 * -1) ? 128 : (int) Math.min( Math.floor(w / minSideLength),
		 * Math.floor(h / minSideLength));
		 * 
		 * if (upperBound < lowerBound) { // return the larger one when there is
		 * no overlapping zone. return lowerBound; }
		 * 
		 * if ((maxNumOfPixels == -1) && (minSideLength == -1)) { return 1; }
		 * else if (minSideLength == -1) { return lowerBound; } else { return
		 * upperBound; } }
		 */
	}

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


//			mAdapter.refreshData(mPhoto);

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

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			//mAdapter.refreshData(mPhoto);

			

			if (pos > 5) {
				mListView.onLoadMoreComplete(true);
			} else {
				mListView.onLoadMoreComplete(false);
			}

		}

	}
}
