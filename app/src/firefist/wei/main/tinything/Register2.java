package firefist.wei.main.tinything;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import firefist.wei.main.Login;
import firefist.wei.main.R;
import firefist.wei.main.service.RegisterService;

public class Register2 extends Activity {

	private EditText name;
	private Spinner spinner1, spinner2, spinner3, spinner4;
	private ArrayAdapter<String> adapter1, adapter2, adapter3, adapter4;

	String _sex, _gschool, _gclass, _number;
	String sex[];
	String gschool[];
	String gclass[];
	String number[];

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register2);

		context = this;
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		name = (EditText) findViewById(R.id.register2_name);
		initSpinner();

	}

	public void register2_back(View v) {
		this.finish();
	}

	public void register2_finish(View v) throws Exception {

		String name_ = name.getText().toString().trim();
		int sex_;
		if (_sex.equals("男")) {
			sex_ = 1;
		} else if (_sex.equals("女")) {
			sex_ = 2;
		} else {
			sex_ = 3;}

		if (_gclass.equals("计科")) {
			_gclass = "CS" + _number;
		} else {
			_gclass = "XX";}
		
		if (_gschool.equals("西北大学")) {
			_gschool = "NWU";
		} else {
			_gschool = "XXX";}
		
		if (RegisterService.register(name_, sex_, _gclass, _gschool)) {
			Intent intent = new Intent(Register2.this, Login.class);
			startActivity(intent);
			this.finish();
		}else{
			Toast.makeText(getApplicationContext(), "其实我也不知道发生了什么错误，" +
					"可能是未连接服务器，您得重试了", Toast.LENGTH_SHORT).show();
		}

	}

	public void initSpinner() {
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner4 = (Spinner) findViewById(R.id.spinner4);

		sex = context.getResources().getStringArray(R.array.sex);
		gschool = context.getResources().getStringArray(R.array.gschool);
		gclass = context.getResources().getStringArray(R.array.gclass);
		number = context.getResources().getStringArray(R.array.number);

		_sex = sex[0];
		_gschool = gschool[0];
		_gclass = gclass[0];
		_number = number[0];

		adapter1 = new ArrayAdapter<String>(this, R.layout.my_spinner, sex);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter1);

		adapter2 = new ArrayAdapter<String>(this, R.layout.my_spinner, gschool);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);

		adapter3 = new ArrayAdapter<String>(this, R.layout.my_spinner, gclass);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(adapter3);

		adapter4 = new ArrayAdapter<String>(this, R.layout.my_spinner, number);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(adapter4);

		spinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				_sex = sex[position];
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				parent.setVisibility(View.VISIBLE);

			}

		});

		spinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				_gschool = gschool[position];
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				parent.setVisibility(View.VISIBLE);
			}

		});

		spinner3.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				_gclass = gclass[position];
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				parent.setVisibility(View.VISIBLE);
			}

		});

		spinner4.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				_number = number[position];
				parent.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				parent.setVisibility(View.VISIBLE);
			}

		});

	}

}
