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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import firefist.wei.main.R;
import firefist.wei.main.activity.Primary_MyInfo;
import firefist.wei.main.right.Right_Comments;
import firefist.wei.main.right.Right_Friends;
import firefist.wei.main.right.Right_Group;
import firefist.wei.main.right.Right_MyInfo;
import firefist.wei.main.right.Right_MyLevel;
import firefist.wei.main.right.Right_MyMsg;
import firefist.wei.main.right.Right_Upload;

public class RightFragment extends Fragment implements OnItemClickListener {

	private static final String TAG = "RightFragment";

	private ListView mListView;

	private MyAdapter myAdapter;

	private List<String> data = new ArrayList<String>();
	private FragmentManager mFragmentManager;

	public RightFragment(FragmentManager fragmentManager) {
		mFragmentManager = fragmentManager;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.right, null);

		mListView = (ListView) view.findViewById(R.id.right_listview);

		data.add("我的消息");
		data.add("我上传的");
//		data.add("我评论的");
		data.add("我的好友");
//		data.add("我的群组");
//		data.add("我的勋章");
		data.add("个人资料");
		data.add("退出登录");

		myAdapter = new MyAdapter(data);
		mListView.setAdapter(myAdapter);
		mListView.setOnItemClickListener(this);
		myAdapter.setSelectPosition(0);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private class MyAdapter extends BaseAdapter {

		private List<String> data;

		private int selectPosition;

		MyAdapter(List<String> list) {
			this.data = list;

		}

		public void setSelectPosition(int position) {
			selectPosition = position;
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
			// TODO Auto-generated method stub
			return position;
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
				img.setBackgroundResource(R.drawable.ic_right_msg);
				break;
			case 1:
				img.setBackgroundResource(R.drawable.ic_right_upload);
				break;
/*			case 2:
				img.setBackgroundResource(R.drawable.ic_right_comments);
				break;*/
			case 2:
				img.setBackgroundResource(R.drawable.ic_right_friends);
				break;
/*			case 4:
				img.setBackgroundResource(R.drawable.ic_right_group);
				break;*/
/*			case 5:
				img.setBackgroundResource(R.drawable.ic_right_mylevel);
				break;*/
			case 3:
				img.setBackgroundResource(R.drawable.ic_right_userinfo);
				break;
			case 4:
				img.setBackgroundResource(R.drawable.ic_right_out);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0: // 我的消息
			Intent intent0 = new Intent(getActivity(), Right_MyMsg.class);
			startActivity(intent0);
			break;
		case 1: // 我上传的
			Intent intent1 = new Intent(getActivity(), Right_Upload.class);
			startActivity(intent1);
			// Toast.makeText(getApplicationContext(), "你点了 个人活动 ",
			// 1000).show();
			break;
/*		case 2: // 我评论的
			Intent intent2 = new Intent(getActivity(), Right_Comments.class);
			startActivity(intent2);
			// Toast.makeText(getApplicationContext(), "你点了 公共活动 ",
			// 1000).show();
			break;*/
		case 2: // 我的好友
			Intent intent3 = new Intent(getActivity(), Right_Friends.class);
			startActivity(intent3);
			// Toast.makeText(getApplicationContext(), "Nothing ", 1000).show();
			break;
/*		case 4: // 我的群组
			Intent intent4 = new Intent(getActivity(), Right_Group.class);
			startActivity(intent4);
			break;*/
/*		case 5: // 我的勋章
			Intent intent5 = new Intent(getActivity(), Right_MyLevel.class);
			startActivity(intent5);
			break;*/
		case 3: // 个人资料
			Intent intent6 = new Intent(getActivity(), Primary_MyInfo.class);
			startActivity(intent6);
			break;
		case 4: // 退出登录
			
			SharedPreferences sharedPreferences = getActivity().getSharedPreferences("u2bsp",
					Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("keycheckbox", "0");
			editor.commit();
			
			Toast.makeText(getActivity(), "用户账号已退出，下次需要重新登录", 1500)
					.show();
			getActivity().finish();
			break;
		}

		/*
		 * View childView = null; View text = null; int length =
		 * mListView.getChildCount(); for(int i = 0,pos = 0; i <length;
		 * i++,pos++){ childView = mListView.getChildAt(i); text =
		 * childView.findViewById(R.id.left_list_text); if(pos == position){
		 * text.setSelected(true); } } myAdapter.setSelectPosition(position);
		 * myAdapter.notifyDataSetChanged();
		 */

	}

}
