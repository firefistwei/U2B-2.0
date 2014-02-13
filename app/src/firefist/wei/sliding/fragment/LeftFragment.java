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

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.users.UsersGetInfoRequestParam;
import com.renren.api.connect.android.users.UsersGetInfoResponseBean;

import firefist.wei.left.Fun_Game;
import firefist.wei.left.PK_Photo;
import firefist.wei.left.Set_Activity;
import firefist.wei.left.nearby.Nearby_ViewPager_Activity;
import firefist.wei.left.ranklist.RankList_Activity;
import firefist.wei.left.voice.Voice_Video_Activity;
import firefist.wei.main.MainActivity;
import firefist.wei.main.R;
import firefist.wei.main.activity.Primary_MyInfo;
import firefist.wei.main.activity.Rank_My;
import firefist.wei.main.activity.Set_About;
import firefist.wei.sliding.utils.IChangeFragment;
import firefist.wei.sliding.view.SlidingMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LeftFragment extends Fragment implements OnItemClickListener {

	private static final String TAG = "LeftFragment";

	private ListView mListView;

	private MyAdapter myAdapter;
	private List<String> data = new ArrayList<String>();
	private FragmentManager mFragmentManager;

	private ImageView left_avatar;
	private TextView left_name;
	
	private RelativeLayout left_top_layout;

	public LeftFragment(FragmentManager fragmentManager) {
		mFragmentManager = fragmentManager;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		
		View view = inflater.inflate(R.layout.left, null);
		
		left_top_layout = (RelativeLayout)view.findViewById(R.id.left_top_layout);
		mListView = (ListView) view.findViewById(R.id.left_listview);

		data.add("主页");
//		data.add("语音&视频");
		data.add("U2B活动");
		data.add("校园2B榜");
		data.add("照片PK");
		data.add("Have Fun");
		data.add("设置");
		data.add("关于");

		// data.add(getResources().getString(R.string.tab_home));

		myAdapter = new MyAdapter(data);
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
		myAdapter.setSelectPosition(0);
		
		left_avatar = (ImageView)view.findViewById(R.id.left_avatar);
		left_name = (TextView)view.findViewById(R.id.left_name);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		left_top_layout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),Primary_MyInfo.class);
				startActivity(intent);
			}
		});
		
		Renren renren;

		Intent intent = MainActivity.instance.getIntent();
		renren = intent.getParcelableExtra(Renren.RENREN_LABEL);
		if (renren != null) {
			renren.init(MainActivity.instance);
		}

		if (renren != null) {
			AsyncRenren asyncRenren = new AsyncRenren(renren);
			String uids[] = parseCommaIds("338916467");
			if (uids == null) {
				return;
			}
			UsersGetInfoRequestParam param = new UsersGetInfoRequestParam(uids);

			AbstractRequestListener<UsersGetInfoResponseBean> listener = new AbstractRequestListener<UsersGetInfoResponseBean>() {

				@Override
				public void onComplete(final UsersGetInfoResponseBean bean) {
					MainActivity.instance.runOnUiThread(new Runnable() {
						String json = null;
						String renren_name = null;
						String renren_headurl = null;

						@Override
						public void run() {

							String json = bean.toString();
							if (json != null) {
								parseJson();

								if(renren_name!=null){
									left_name.setText(renren_name);
								}
							}
							Log.v("RenRen", bean.toString());

						}

						private void parseJson() {
							try {
								JSONArray array = new JSONArray(json);
								for (int i = 0; i < array.length(); i++) {
									renren_name = array.getJSONObject(i)
											.getString("name");
									renren_headurl = array.getJSONObject(i)
											.getString("headurl");

								}
							} catch (JSONException e) {

								e.printStackTrace();
							}
						}
					});
				}

				@Override
				public void onRenrenError(final RenrenError renrenError) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", renrenError.getMessage());

						}
					};
				}

				@Override
				public void onFault(final Throwable fault) {
					new Runnable() {

						@Override
						public void run() {
							Log.v("RenRen", fault.getMessage());

						}
					};
				}
			};
			asyncRenren.getUsersInfo(param, listener);

		}
	}

	private String[] parseCommaIds(String s) {
		if (s == null) {
			return null;
		}
		String[] ids = s.split(",");
		return ids;
	}

	private class MyAdapter extends BaseAdapter {

		private List<String> data;

		private int selectPosition;

		MyAdapter(List<String> list) {
			this.data = list;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		public void setSelectPosition(int position) {
			selectPosition = position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = LayoutInflater.from(getActivity()).inflate(
					R.layout.left_list, null);
			TextView textView = (TextView) row
					.findViewById(R.id.left_list_text);
			textView.setText(data.get(position));
			ImageView img = (ImageView) row.findViewById(R.id.left_list_image);
			switch (position) {
			case 0:
				img.setBackgroundResource(R.drawable.ic_left_home);
				break;
			/*case 1:
				img.setBackgroundResource(R.drawable.ic_left_voice);
				break;*/
			case 1:
				img.setBackgroundResource(R.drawable.ic_left_active);
				break;
			case 2:
				img.setBackgroundResource(R.drawable.ic_left_ranklist);
				break;
			case 3:
				img.setBackgroundResource(R.drawable.ic_left_pk);
				break;
			case 4:
				img.setBackgroundResource(R.drawable.ic_left_play);
				break;
			case 5:
				img.setBackgroundResource(R.drawable.ic_left_set);
				break;
			case 6:
				img.setBackgroundResource(R.drawable.ic_left_about);
				break;
			default:
				break;
			}

			/*
			 * if(position == selectPosition){
			 * row.setBackgroundResource(R.drawable.left_tab_bg_pressed);
			 * textView.setSelected(true); }
			 */
			return row;
		}

	}

	private IChangeFragment iChangeFragment;

	public void setChangeFragmentListener(IChangeFragment listener) {
		iChangeFragment = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0: // 主页
			((MainActivity) getActivity()).showLeft();
			break;
		/*case 1: // 语音&视频
			Intent intent1 = new Intent(getActivity(),
					Voice_Video_Activity.class);
			startActivity(intent1);
			// Toast.makeText(getApplicationContext(), "你点了 个人活动 ",
			// 1000).show();
			break;*/
		case 1: // U2B活动
			Intent intent2 = new Intent(getActivity(),
					Nearby_ViewPager_Activity.class);
			startActivity(intent2);
			// Toast.makeText(getApplicationContext(), "你点了 公共活动 ",
			// 1000).show();
			break;
		case 2: // U2B排行榜
			Intent intent3 = new Intent(getActivity(), RankList_Activity.class);
			startActivity(intent3);
			// Toast.makeText(getApplicationContext(), "Nothing ", 1000).show();
			break;
		case 3: // 照片PK
			Intent intent4 = new Intent(getActivity(), PK_Photo.class);
			startActivity(intent4);
			break;
		case 4: // Have Fun
			Intent intent5 = new Intent(getActivity(), Fun_Game.class);
			startActivity(intent5);
			break;
		case 5: // 设置
			Intent intent6 = new Intent(getActivity(), Set_Activity.class);
			startActivity(intent6);
			break;
		case 6: // 关于
			Intent intent7 = new Intent(getActivity(), Set_About.class);
			startActivity(intent7);
			break;

		}
		// View childView = null;
		// View text = null;
		// int length = mListView.getChildCount();
		// for(int i = 0,pos = 0; i <length; i++,pos++){
		// childView = mListView.getChildAt(i);
		// text = childView.findViewById(R.id.left_list_text);
		// if(pos == position){
		// text.setSelected(true);
		// }
		// }

		// if(iChangeFragment != null){
		// iChangeFragment.changeFragment(position);
		// }

		myAdapter.setSelectPosition(position);
		myAdapter.notifyDataSetChanged();

		// FragmentTransaction t = mFragmentManager
		// .beginTransaction();
		// LocalFragment leftFragment = new LocalFragment();
		// t.replace(R.id.center_frame, leftFragment);
		// t.commit();
	}

}
