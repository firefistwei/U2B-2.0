package firefist.wei.main.activity;


import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Rank_My extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_rank);

		Button btn_right = (Button) this.findViewById(R.id.my_rank_right);
		btn_right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int score = 100 ;
				Toast.makeText(getApplicationContext(),
						"距下一级还差" + String.valueOf(score)+"尔币", Toast.LENGTH_SHORT)
						.show();

			}
		});
	}

	public void my_rank_back(View v) {
		this.finish();
	}

}
