package firefist.wei.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import firefist.wei.main.MyConstants;
import firefist.wei.main.R;

public class Primary_MyInfo_Item extends Activity {

	Button btn_ok;
	TextView title;
	EditText ed;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myinfo_item);

		findViewById();
		init();

	}
	public void myinfo_item_back(View v) {
		this.finish();
	}
	
	private void findViewById() {
		title = (TextView)findViewById(R.id.myinfo_item_title);
		ed = (EditText)findViewById(R.id.myinfo_item_ed);
		btn_ok = (Button)findViewById(R.id.myinfo_item_ok);
	}

	private void init() {
		
		final String action = getIntent().getStringExtra("text_action");
		
		if(action.equals("name")){
			title.setText("�޸��ǳ�");
		}else if(action.equals("sig")){
			title.setText("�޸�ǩ��");
		}else if(action.equals("birthday")){
			title.setText("�޸�����");
		}else if(action.equals("school")){
			title.setText("�޸�ѧУ");
		}else if(action.equals("goodat")){
			title.setText("�޸�ר��");
		}

		btn_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(action.equals("name")){
					MyConstants.user_name = ed.getText().toString().trim();
				}else if(action.equals("sig")){
					MyConstants.user_sig = ed.getText().toString().trim();
				}else if(action.equals("birthday")){
					MyConstants.user_birthday = ed.getText().toString().trim();
				}else if(action.equals("school")){
					MyConstants.user_school = ed.getText().toString().trim();
				}else if(action.equals("goodat")){
					MyConstants.user_goodat = ed.getText().toString().trim();
				}

				finish();
			}
		});
		
		
	}

}
