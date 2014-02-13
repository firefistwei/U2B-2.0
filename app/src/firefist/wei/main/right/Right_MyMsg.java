package firefist.wei.main.right;

import firefist.wei.main.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Right_MyMsg extends Activity{

	private Context mContext;

	private ListView mDisplay;
	private String[] mTitles = { "照片评论", "好友留言", "活动消息"};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.right_mymsg);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mContext = this;

		findViewById();
		setListener();
		init();
	}
	
	private void findViewById() {
		
		mDisplay = (ListView) this.findViewById(R.id.right_mymsg_listview);
	}

	private void setListener() {
		
	}

	private void init() {
		//添加适配器
		mDisplay.setAdapter(new MessageAdapter());
	}


	private class MessageAdapter extends BaseAdapter {

		public int getCount() {
			return mTitles.length;
		}

		public Object getItem(int position) {
			return mTitles[position];
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.right_mymsg_item, null);
				holder = new ViewHolder();
				holder.title = (TextView) convertView
						.findViewById(R.id.right_mymsg_item_title);
				holder.messageCount = (TextView) convertView
						.findViewById(R.id.right_mymsg_item_messagecount);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.title.setText(mTitles[position]);
			holder.messageCount.setText("0条新消息");
			return convertView;
		}

		class ViewHolder {
			TextView title;
			TextView messageCount;
		}
	}

	
	public void right_mymsg_back(View v){
		this.finish();
	}
}
