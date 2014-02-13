package firefist.wei.sliding.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import firefist.wei.left.nearby.Nearby_ViewPager_Activity;
import firefist.wei.left.voice.Voice_Video_Activity;
import firefist.wei.main.KXActivity;
import firefist.wei.main.MainActivity;
import firefist.wei.main.R;
import firefist.wei.main.activity.FindFriend;
import firefist.wei.main.activity.Primary_MyInfo;
import firefist.wei.main.activity.PublishVoice;
import firefist.wei.main.activity.Rank_My;
import firefist.wei.main.activity.Set_About;
import firefist.wei.satellite.SatelliteMenu;
import firefist.wei.satellite.SatelliteMenu.SateliteClickedListener;
import firefist.wei.satellite.SatelliteMenuItem;
import firefist.wei.sliding.adapter.ListViewPagerAdapter;
import firefist.wei.sliding.adapter.ScrollingTabsAdapter;

import firefist.wei.sliding.view.ScrollableTabView;
import firefist.wei.sliding.view.SlidingMenu;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

public class MainFragment extends Fragment implements
		OnRefreshListener<ListView>, ViewPager.OnPageChangeListener {
	private static final String TAG = "NewsFragment";

	private View showLeft;
	private View showRight;
	private TextView mTopTitleView;
	private ImageView mTopBackView;
	private MyAdapter mAdapter;
	private ViewPager mPager;
	private ArrayList<Fragment> pagerItemList = null;

	private ListViewPagerAdapter listViewPagerAdapter;
	private Activity mActivity;

	private ScrollableTabView mScrollableTabView;
	private ScrollingTabsAdapter mScrollingTabsAdapter;

	public SatelliteMenu menu;
	public List<SatelliteMenuItem> items;

	public MainFragment() {
	}

	public MainFragment(Activity activity) {
		this.mActivity = activity;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e(TAG, "onDestroy");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(TAG, "onCreateView");
		View mView = inflater.inflate(R.layout.view_pager, null);

		showLeft = (View) mView.findViewById(R.id.head_layout_showLeft);
		showRight = (View) mView.findViewById(R.id.head_layout_showRight);

		mTopTitleView = (TextView) showLeft.findViewById(R.id.head_layout_text);
		mTopTitleView.setText(getString(R.string.app_name));

		mTopBackView = (ImageView) showLeft.findViewById(R.id.head_layout_back);
		mTopBackView
				.setBackgroundResource(R.drawable.biz_news_main_back_normal);

		mPager = (ViewPager) mView.findViewById(R.id.vp_list);

		// listViewPagerAdapter = new ListViewPagerAdapter(this);
		// mPager.setAdapter(listViewPagerAdapter);

		// pagerItemList = new ArrayList<Fragment>() ;
		// pagerItemList.add(new Fragment());
		// pagerItemList.add(new Fragment());
		// pagerItemList.add(new Fragment());

		pagerItemList = new ArrayList<Fragment>();

		pagerItemList.add(new PageFragment1());

		// PageFragment2 page2 = new
		// PageFragment2(getActivity(),mActivity,MainActivity.mKXApplication);
		pagerItemList.add(new PageFragment2());

		pagerItemList.add(new PageFragment3());

		// PageFragment1 page1 = new PageFragment1();
		// PageFragment2 page2 = new PageFragment2();
		// pagerItemList.add(page1);
		// pagerItemList.add(page2);

		mAdapter = new MyAdapter(getFragmentManager());
		mPager.setAdapter(mAdapter);

		mPager.setOnPageChangeListener(this);
		initScrollableTabs(mView, mPager);

		// menu = (SatelliteMenu) mView.findViewById(R.id.sate_menu);

		return mView;
	}

	private void initScrollableTabs(View view, ViewPager mViewPager) {
		mScrollableTabView = (ScrollableTabView) view
				.findViewById(R.id.scrollabletabview);
		mScrollingTabsAdapter = new ScrollingTabsAdapter(mActivity);
		mScrollableTabView.setAdapter(mScrollingTabsAdapter);
		mScrollableTabView.setViewPage(mViewPager);
	}

	public ViewPager getViewPage() {
		return mPager;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		showLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).showLeft();
			}
		});

		showRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).showRight();
			}
		});

		LinearLayout layout = (LinearLayout) getActivity().findViewById(
				R.id.view_pager_layout);
		layout.removeAllViews();
		LayoutInflater mInflater = LayoutInflater.from(layout.getContext());

		View littleView = mInflater.inflate(R.layout.satellite_layout, null);

		// menu = new SatelliteMenu(this.getActivity());
		menu = (SatelliteMenu) littleView.findViewById(R.id.sate_menu);

		items = new ArrayList<SatelliteMenuItem>();

		items.add(new SatelliteMenuItem(0, R.drawable.ic4_c));// 1.À—À˜
		items.add(new SatelliteMenuItem(1, R.drawable.ic2_c));// 2. ”∆µ
		items.add(new SatelliteMenuItem(2, R.drawable.ic1_c));// 3.”Ô“Ù
		items.add(new SatelliteMenuItem(3, R.drawable.ic3_c));// 4.Œª÷√

		// items.add(new SatelliteMenuItem(5, R.drawable.sat_item));

		menu.addItems(items);

		layout.addView(littleView);

		menu.setOnItemClickedListener(new SateliteClickedListener() {

			public void eventOccured(int id) {
				Log.i("Satellite",id+"");
				
				switch (id){
				
				case 0:  //findFreind
					Intent intent0 = new Intent(getActivity(), FindFriend.class);
					startActivity(intent0);
					
					
					break;
				case 1:  //video
					Intent intent1 = new Intent(getActivity(), Primary_MyInfo.class);
					startActivity(intent1);
					
					break;
				case 2:  //voice
					Intent intent2 = new Intent(getActivity(), PublishVoice.class);
					startActivity(intent2);
/*					Intent intent2 = new Intent(getActivity(), Nearby_ViewPager_Activity.class);
					startActivity(intent2);*/
					
					break;
				case 3: //active
					Intent intent3 = new Intent(getActivity(), Nearby_ViewPager_Activity.class);
					startActivity(intent3);
					
					//Toast.makeText(getApplicationContext(), "Nothing ", 1000).show();
					break;
				default:
					break;
				}
			}
		});

		/*
		 * menu.setOnItemClickedListener(new SateliteClickedListener() {
		 * 
		 * public void eventOccured(int id) { Log.i("sat", "Clicked on " + id);
		 * Toast.makeText(getActivity(), "fuck", Toast.LENGTH_SHORT) .show(); }
		 * });
		 */
	}

	public boolean isFirst() {

		if (mPager.getCurrentItem() == 0)
			return true;
		else
			return false;
	}

	public boolean isEnd() {

		if (mPager.getCurrentItem() == pagerItemList.size() - 1)
			return true;
		else
			return false;
	}

	public class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public Fragment getItem(int position) {

			Fragment fragment = null;
			/*
			 * if (position < pagerItemList.size()) fragment =
			 * pagerItemList.get(position); else fragment =
			 * pagerItemList.get(0);
			 */
			fragment = pagerItemList.get(position);

			return fragment;

		}
	}

	private MyPageChangeListener myPageChangeListener;

	public void setMyPageChangeListener(MyPageChangeListener l) {

		myPageChangeListener = l;

	}

	public interface MyPageChangeListener {
		public void onPageSelected(int position);
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		new GetDataTask(refreshView).execute();
	}

	private static class GetDataTask extends AsyncTask<Void, Void, Void> {

		PullToRefreshBase<?> mRefreshedView;

		public GetDataTask(PullToRefreshBase<?> refreshedView) {
			mRefreshedView = refreshedView;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mRefreshedView.onRefreshComplete();
			super.onPostExecute(result);
		}
	}

	@Override
	public void onPageScrollStateChanged(int position) {

	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int position) {
		if (myPageChangeListener != null) {
			myPageChangeListener.onPageSelected(position);

			mPager.setCurrentItem(position);
		}
		if (mScrollableTabView != null) {
			mScrollableTabView.selectTab(position);

			mPager.setCurrentItem(position);
		}

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.e(TAG, "onDestroyView");
		listViewPagerAdapter = null;
		pagerItemList.clear();
		pagerItemList = null;
		mScrollableTabView = null;
		mScrollingTabsAdapter = null;
		mActivity = null;
	}

}
