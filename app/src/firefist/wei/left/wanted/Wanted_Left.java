package firefist.wei.left.wanted;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import firefist.wei.left.nearby.OneParty;
import firefist.wei.main.R;

public class Wanted_Left extends Fragment{

	private ImageView imageView = null;
	private TextView textView = null;

	Context context =null;

	Button btn1,btn2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.wanted_left, container, false);
		
		imageView = (ImageView)view.findViewById(R.id.wanted_left_img);
		textView = (TextView)view.findViewById(R.id.wanted_left_text);
		btn1 = (Button)view.findViewById(R.id.wanted_left_btn1);
		btn2 = (Button)view.findViewById(R.id.wanted_left_btn2);
		
		return view;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		context = getActivity();
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,OneParty.class);
				Bundle bundle = new Bundle();
				bundle.putInt("type", 1);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,OneParty.class);
				Bundle bundle = new Bundle();
				bundle.putInt("type", 2);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});


	}
}
