package firefist.wei.left.nearby;


import firefist.wei.main.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SetUpParty extends Activity{
	
	ImageView setup_party_img;

	EditText ed_where;
	EditText ed_when;
	EditText ed_reason;

	Button btn_ok;
	
	TextView setup_party_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setup_party);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		findViewById();
		init();
	}
	
	public void setup_party_back(View v){
		this.finish();
	}
	
	
	
	private void findViewById(){	
		ed_where = (EditText)this.findViewById(R.id.setup_party_where_edittext);
		ed_when = (EditText)this.findViewById(R.id.setup_party_when_edittext);
		ed_reason = (EditText)this.findViewById(R.id.setup_party_reason);
		btn_ok = (Button)this.findViewById(R.id.setup_party_btn);
		
		setup_party_img = (ImageView)this.findViewById(R.id.setup_party_img);
		setup_party_title = (TextView)this.findViewById(R.id.setup_party_title);
	}

	private void init() {

		
		int party_type = this.getIntent().getIntExtra("type", 1);
		
		if(party_type == 1){
			setup_party_img.setImageResource(R.drawable.job_1);
			setup_party_title.setText("我讲笑话你请客");
		}else if(party_type == 2){
			setup_party_img.setImageResource(R.drawable.job_2);
			setup_party_title.setText("恶搞路人甲");
		}
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//发布
				
				
			}
		});
		
	}

	
	
	
}
