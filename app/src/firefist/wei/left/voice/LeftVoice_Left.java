package firefist.wei.left.voice;

import firefist.wei.main.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class LeftVoice_Left extends Fragment{

	private ListView mListView;

	//private MyAdapter mAdapter;
	
	Context context = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		View view = inflater
				.inflate(R.layout.leftvoice_left, container, false);

		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		context = this.getActivity();
		

	}
}
