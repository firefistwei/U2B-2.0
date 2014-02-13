package firefist.wei.left.nearby;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import firefist.wei.main.R;

public class NearBy_Left extends Fragment{
	
	private ImageView imageView = null;
	private AnimationDrawable anim = null;

	Context context =null;

	Button btn1,btn2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.nearby_left, container, false);
		btn1 = (Button)view.findViewById(R.id.nearby_left_btn1);
		btn2 = (Button)view.findViewById(R.id.nearby_left_btn2);
		
		return view;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		context = getActivity();
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,SetUpParty.class);
				Bundle bundle = new Bundle();
				bundle.putInt("type", 1);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context,SetUpParty.class);
				Bundle bundle = new Bundle();
				bundle.putInt("type", 2);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});


	}
}
