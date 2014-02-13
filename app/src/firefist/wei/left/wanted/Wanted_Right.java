package firefist.wei.left.wanted;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import firefist.wei.left.nearby.OneParty;
import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.domain.MyActive;
import firefist.wei.utils.PhotoUtil;

public class Wanted_Right extends Fragment{

	private ListView mListView;

	private MyAdapter mAdapter;
	
	Context context = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater
				.inflate(R.layout.wanted_right, container, false);
		
		mListView = (ListView) view.findViewById(R.id.wanted_right_listview);
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		context = this.getActivity();
		init();
		
		mAdapter = new MyAdapter();
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent(context,OneParty.class);
				intent.putExtra("party_position", position);
				intent.putExtra("uid",MyConstants.mActiveList.get(position).getUid());
				
				startActivity(intent);
				
			}
			
		});

	}


	private void init() {
		getData();
		
	}
	
	private void getData() {
		if (MyConstants.mActiveList.isEmpty()) {
			String json = MyConstants.activeJSON;
			try {
				JSONArray array = new JSONArray(json);
				MyActive myActive = null;
				for (int i = 0; i < array.length(); i++) {
					myActive = new MyActive();
					myActive.setUid(array.getJSONObject(i).getInt("uid"));
					myActive.setAid(array.getJSONObject(i).getInt("aid"));
					myActive.setAtype(array.getJSONObject(i).getInt("atype"));
					myActive.setAname(array.getJSONObject(i).getString("aname"));
					myActive.setAtime(array.getJSONObject(i).getString("atime"));
					myActive.setAposition(array.getJSONObject(i).getString(
							"aposition"));
					myActive.setAmember(array.getJSONObject(i).getString(
							"amember"));
					myActive.setApeople_no(array.getJSONObject(i).getString(
							"apeople_no"));
					myActive.setAdescrip(array.getJSONObject(i).getString(
							"adescrip"));
					myActive.setAlongi(array.getJSONObject(i).getDouble(
							"alongi"));
					myActive.setAlatitude(array.getJSONObject(i).getDouble(
							"alatitude"));
					myActive.setIsfull(array.getJSONObject(i).getInt("isfull"));
					myActive.setIsfinished(array.getJSONObject(i).getInt("isfinished"));

					MyConstants.mActiveList.add(myActive);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}

	private class MyAdapter extends BaseAdapter {

		private List<MyActive> mList= null;

		public MyAdapter() {
			
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.nearby_right_item, null);
				holder = new ViewHolder();
				holder.avatar = (ImageView) convertView
						.findViewById(R.id.home_activ_img1);
				holder.title = (TextView) convertView
						.findViewById(R.id.home_activ_tv1);
				holder.time = (TextView) convertView
						.findViewById(R.id.home_activ_tv2);
				holder.position = (TextView) convertView
						.findViewById(R.id.home_activ_tv3);
				holder.content = (TextView) convertView
						.findViewById(R.id.home_activ_tv4);
				holder.people_no = (TextView) convertView
						.findViewById(R.id.home_activ_tv5);

/*				layout = (LinearLayout) convertView
						.findViewById(R.id.linearlayout_home_activ);*/
				
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			
			MyActive myActive = mList.get(position);

			// 需要处理
			int r = (int)(Math.random()*15+0.5);
			Bitmap bm_head = BitmapFactory.decodeResource(context.getResources(), 
					MyConstants.Head_all[r]);
			holder.avatar.setImageBitmap(PhotoUtil.toRoundCorner(bm_head, 20));

			holder.title.setText(myActive.getAname());

			// 需要处理
			holder.time.setText(myActive.getAtime());

			// 需要处理
			holder.position.setText(myActive.getAposition());
			holder.content.setText(myActive.getAdescrip());
			holder.people_no.setText(myActive.getApeople_no());
			
			//layout.setOnClickListener(new layoutOnClickListener)

			return convertView;
		}

		class ViewHolder {
			ImageView avatar;
			TextView title;
			TextView time;
			TextView position;
			TextView content;
			TextView people_no;

		}
	}
}
