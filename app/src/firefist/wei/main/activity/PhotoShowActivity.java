package firefist.wei.main.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.domain.Comments;
import firefist.wei.main.domain.Photo;
import firefist.wei.utils.PhotoUtil;

public class PhotoShowActivity extends Activity {
	DisplayImageOptions options;

	private View headView;
	private View footView;
	private ProgressBar head_progressBar;
	private ImageView head_image;
	private TextView head_title;
	private ImageView head_avatar;
	private TextView head_time;
	private TextView head_name;
	private TextView head_description;
	private TextView head_like;
	private TextView head_zan;// 是否已赞

	private TextView foot_comment;
	
	private LinearLayout photoshow_text_layout = null;
	private LinearLayout photoshow_voice_layout = null;

	Context mContext = null;
	// 显示内容的ListView以及适配器
	private ListView mListView;
	private MyAdapter mAdapter;
	
	private List<Comments> comments = null;
	
	Photo show_photo = null;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.photoshow);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		Bundle bundle = getIntent().getExtras();
		// String[] imageUrls = bundle.getStringArray(Extra.IMAGES);
		// int pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);

		mContext = this;
		headView = LayoutInflater.from(this).inflate(R.layout.photoshow_header,
				null);
		footView = LayoutInflater.from(this).inflate(R.layout.photoshow_footer,
				null);

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();

		initImageLoader(getApplicationContext());

		findViewById();
		// setListener();
		init();
	}

	private void init() {
		
		mListView.addHeaderView(headView);
		mListView.addFooterView(footView);
		
		initInfo();
		
		
		comments = new ArrayList<Comments>();

		getComments();
		
		mAdapter = new MyAdapter(this,comments);
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Intent intent = new Intent(mContext, ImagePagerActivity.class);
//				startActivity(intent);

			}

		});

	}

	private void initInfo() {
		head_progressBar.setVisibility(View.GONE);
		
		head_image.setImageBitmap(MyConstants.Current_bmp);
		
		show_photo = getIntent().getParcelableExtra("page1_photo");
		
		head_title.setText(show_photo.getTitle());
		head_time.setText(show_photo.getDate());
		head_description.setText(show_photo.getInformation());
		head_like.setText(show_photo.getLike_max()+"");
		head_zan.setText(show_photo.getComment_max()+"");

		
	}
	
	public void photoshow_text_turn(View v){
		photoshow_text_layout.setVisibility(View.VISIBLE);
		photoshow_voice_layout.setVisibility(View.GONE);
	}
	
	public void photoshow_voice_turn(View v){
		photoshow_text_layout.setVisibility(View.GONE);
		photoshow_voice_layout.setVisibility(View.VISIBLE);
	}

	private void getComments() {
		// TODO Auto-generated method stub
		
	}


	private void findViewById() {
		mListView = (ListView) this.findViewById(R.id.photoshow_listview);

		head_progressBar = (ProgressBar) headView
				.findViewById(R.id.photoshow_loading);
		head_image = (ImageView) headView.findViewById(R.id.photoshow_image);
		head_title = (TextView) headView.findViewById(R.id.photoshow_title);
		head_avatar = (ImageView) headView.findViewById(R.id.photoshow_avatar);
		head_time = (TextView) headView.findViewById(R.id.photoshow_time);
		head_name = (TextView) headView.findViewById(R.id.photoshow_name);
		head_description = (TextView) headView
				.findViewById(R.id.photoshow_description);
		head_like = (TextView) headView.findViewById(R.id.photoshow_like);
		head_zan = (TextView) headView.findViewById(R.id.photoshow_zan);

		foot_comment = (TextView) footView
				.findViewById(R.id.photoshow_comment_count);
		
		photoshow_text_layout = (LinearLayout)findViewById(R.id.photoshow_text_layout);
		photoshow_voice_layout = (LinearLayout)findViewById(R.id.photoshow_voice_layout );

	}

	public void photoshow_back(View v) {
		this.finish();
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
	
	private class MyAdapter extends BaseAdapter {

		private List<Comments> mComments = null;
		private Context mContext;

		public MyAdapter(Context context, List<Comments> comments) {
			mContext = context;
			this.mComments = comments;
		}

		public void refreshData(ArrayList<Comments> data) {
			this.mComments = data;
			notifyDataSetChanged();

		}

		@Override
		public int getCount() {
			return mComments.size();
		}

		@Override
		public Object getItem(int position) {
			return mComments.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			
			Comments data = mComments.get(position);
			
			if (convertView == null) {
				
				if(data.getUrl().equals("0")){ //评论为文字
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.photoshow_comment_text, null);
					holder = new ViewHolder();
					holder.avatar = (ImageView) convertView
							.findViewById(R.id.comment_text_head);
					holder.name = (TextView) convertView
							.findViewById(R.id.comment_text_name);
					holder.time = (TextView) convertView
							.findViewById(R.id.comment_text_time);
					holder.content = (TextView) convertView
							.findViewById(R.id.comment_text_content);
					
					convertView.setTag(holder);
					
				}else{  //评论为语音
					convertView = LayoutInflater.from(mContext).inflate(
							R.layout.photoshow_comment_voice, null);
					holder = new ViewHolder();
					holder.avatar = (ImageView) convertView
							.findViewById(R.id.comment_voice_head);
					holder.name = (TextView) convertView
							.findViewById(R.id.comment_voice_name);
					holder.time = (TextView) convertView
							.findViewById(R.id.comment_voice_time);
					holder.play = (ImageView) convertView
							.findViewById(R.id.comment_voice_play);
					holder.progress = (ProgressBar) convertView
							.findViewById(R.id.comment_voice_progressbar);
					holder.length = (TextView) convertView
							.findViewById(R.id.comment_voice_length);
					convertView.setTag(holder);
				}
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}


			/*Bitmap avatar = PhotoUtil.toRoundCorner(BitmapFactory
					.decodeResource(mContext.getResources(),
							R.drawable.random_head0), 20);
			holder.avatar.setImageBitmap(avatar);

			holder.time.setText(active.getAtime());
			holder.content.setText(active.getAname());*/

			return convertView;
		}

		class ViewHolder {
			ImageView avatar;
			TextView name;
			TextView time;
			TextView content;
			
			ImageView play;
			ProgressBar progress;
			TextView length;
		}

		

	}
}
