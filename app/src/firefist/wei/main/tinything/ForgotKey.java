package firefist.wei.main.tinything;

import firefist.wei.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class ForgotKey extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.forgotkey);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int i = (int) (Math.random()*10);
		if(i<4);
			ForgotKey.this.setContentView(R.layout.forgotkey);

		
		return false;
	}
}
