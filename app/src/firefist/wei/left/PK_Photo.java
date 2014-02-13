package firefist.wei.left;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import firefist.wei.main.R;

public class PK_Photo extends Activity{

//	private View headView;
	
	private ListView mList;

	Context mContext = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.left_pk_photo_main);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mContext = this;
		/*headView = LayoutInflater.from(this).inflate(R.layout.ranklist_head,
				null);*/

	}
	
	public void left_pk_photo_back(View v){
		this.finish();
	}
}
