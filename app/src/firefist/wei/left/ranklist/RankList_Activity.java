package firefist.wei.left.ranklist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import firefist.wei.main.R;

public class RankList_Activity extends Activity{

//	private View headView;
	
	private ListView mList;
	private MyAdapter mAdapter;

	Context mContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_ranklist_main);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mContext = this;
		/*headView = LayoutInflater.from(this).inflate(R.layout.ranklist_head,
				null);*/

		findViewById();
		init();
	}
	
	public void left_ranklist_back(View v){
		this.finish();
	}

	private void findViewById() {
		mList = (ListView)this.findViewById(R.id.ranklist_listview);
		
	}

	private void init() {
		
		
	}
	
	private class MyAdapter extends BaseAdapter{
				
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}





