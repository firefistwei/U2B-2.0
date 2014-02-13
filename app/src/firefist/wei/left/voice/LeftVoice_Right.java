package firefist.wei.left.voice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import firefist.wei.main.R;

public class LeftVoice_Right extends Fragment{

	private ListView mListView;

	//private MyAdapter mAdapter;
	
	Context context = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater
				.inflate(R.layout.leftvoice_right, container, false);

		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		context = this.getActivity();
		

	}
}
