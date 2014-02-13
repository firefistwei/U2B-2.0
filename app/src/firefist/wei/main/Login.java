package firefist.wei.main;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import firefist.wei.main.domain.User;
import firefist.wei.main.renren.ApiListActivity;
import firefist.wei.main.renren.RenRenBaseActivity;
import firefist.wei.main.service.CheckUserService;
import firefist.wei.main.service.MyService;
import firefist.wei.main.tinything.ForgotKey;
import firefist.wei.main.tinything.Register;
import firefist.wei.utils.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.renren.api.connect.android.PasswordFlowRequestParam;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.renren.api.connect.android.Renren;

public class Login extends RenRenBaseActivity {
	private EditText mUser; // 帐号编辑框
	private EditText mPassword; // 密码编辑框
	private CheckBox mCheckBox;

	boolean gointo_flag = false;

	private static final String API_KEY = "6b1016db20c540e78bd1b20be4c707a3";

	private static final String SECRET_KEY = "4723a695c09e4ddebbe8d87393d95fb4";

	private static final String APP_ID = "105381";

	private Handler handler;

	private Renren renren;

	private RenrenAuthListener listener;

	ProgressDialog pd = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		init();

	}

	public void register_btn(View v) { // 注册按钮
		Intent intent = new Intent(Login.this, Register.class);
		startActivity(intent);
		this.finish();
	}

	private void init() {
		mUser = (EditText) findViewById(R.id.login_user_edit);
		mPassword = (EditText) findViewById(R.id.login_passwd_edit);
		mCheckBox = (CheckBox) findViewById(R.id.login_check_box);

		SharedPreferences sharedPreferences = getSharedPreferences("u2bsp",
				MODE_PRIVATE);
		String keyCheck = sharedPreferences.getString("keycheckbox", "1");

		gointo_flag = false;

		String pre_email = sharedPreferences.getString("login_user", "");
		String pre_pwd = sharedPreferences.getString("login_passwd", "");
		
		if (Register.uname != null) {
			mUser.setText(Register.uname);
			mPassword.setText(Register.upasswd);
		}else if(pre_email.equals("")){
			
			mUser.setText(pre_email);
			mPassword.setText(pre_pwd);
		}else{
			mUser.setText("firefist.wei@gmail.com");
			mPassword.setText("fire");
		}
		
		if (keyCheck.equals("1")) {

			mCheckBox.setChecked(true);

			String email = sharedPreferences.getString("login_user", "");
			String pwd = sharedPreferences.getString("login_passwd", "");
			Long uid = sharedPreferences.getLong("login_uid", 1l);
			
			MyConstants.user_uid = uid;

			if (!email.equals("")) {
				Intent intent = new Intent();
				intent.setClass(Login.this, MainActivity.class);
				startActivity(intent);

				MyConstants.login_user = sharedPreferences.getString(
						"login_user", "");
				MyConstants.user_uid = sharedPreferences.getInt("user_id", 0);
				this.finish();
			}
		}else{
			Toast.makeText(
					getApplication(),
					"默认登陆：\n" + "用户名 ：  firefist.wei@gmail.com" + "\n" + "密码： fire",
					1).show();
		}

		if (keyCheck.equals("0")) {
			mCheckBox.setChecked(false);
		} else {
			mCheckBox.setChecked(true);
		}

		mCheckBox
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton compoundButton,
							boolean b) {
					}
				});

		

		init_renren_btn();
	}

	public void login_btn(View v) {

		String user = mUser.getText().toString().trim();
		String passwd = mPassword.getText().toString().trim();

		pd = ProgressDialog.show(Login.this, null, "正在登录...", true, true);
		getLogin(user, passwd);

	}

	private void getLogin(final String user, final String passwd) {
		new Thread() {
			public void run() {
				Looper.prepare();
				InputStream inputStream;

				try {
					inputStream = MyService.getLogin(user, passwd);

					String result = Utils.readInputStream(inputStream);
					Log.i("Login_User", result + "");
					if (result.length() > 0) {
						JSONObject jsonObject = new JSONObject(result);
						JSONObject userObject = jsonObject
								.getJSONObject("user");

						User user = new User();
						user.setUid(userObject.getLong("uid"));
						user.setNick_name(userObject.getString("nick_name"));
						user.setHead_url(userObject.getString("head_url"));
						user.setEmail(userObject.getString("email"));
						user.setPassword(userObject.getString("password"));

						user.setGender(userObject.getInt("gender"));
						user.setDegree(userObject.getInt("degree"));
						user.setSignature(userObject.getString("signature"));
						user.setBirthday(userObject.getString("birthday"));
						user.setSchool(userObject.getString("school"));

						user.setScore(userObject.getInt("score"));
						user.setRegister_day(userObject
								.getString("register_day"));
						user.setUpload_max(userObject.getInt("upload_max"));

						MyConstants.meUser = user;
					}

					if (MyConstants.meUser.getUid() > 0) {
						pd.dismiss();

//						if (mCheckBox.isChecked()) {}
							SharedPreferences sp = getSharedPreferences(
									"u2bsp", MODE_PRIVATE);
							Editor editor = sp.edit();
							editor.putString("keycheckbox", "1");
							editor.putString("login_user",
									MyConstants.meUser.getEmail());
							editor.putString("login_passwd",
									MyConstants.meUser.getPassword());
							editor.putLong("login_id",
									MyConstants.meUser.getUid());
							editor.commit();
						
						Intent intent = new Intent();
						intent.setClass(Login.this, MainActivity.class);
						startActivity(intent);
						finish();

						Looper.loop();
					} else {
						pd.dismiss();
						new AlertDialog.Builder(Login.this)
								.setIcon(
										getResources().getDrawable(
												R.drawable.login_error_passwd))
								.setTitle("登录失败")
								.setMessage("U2b帐号或者密码不正确，\n请检查后重新输入！")
								.create().show();
						
						Looper.loop();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	@Override
	protected void onResume() {
		super.onResume();
		renren.init(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (renren != null) {
			renren.authorizeCallback(requestCode, resultCode, data);
		}
	}

	private void init_renren_btn() {

		renren = new Renren(API_KEY, SECRET_KEY, APP_ID, this);
		// Initialize the demo invoker
		ApiDemoInvoker.init(renren);
		handler = new Handler();

		listener = new RenrenAuthListener() {

			@Override
			public void onComplete(Bundle values) {
				Log.d("test", values.toString());

				// startApiList();
				Intent intent = new Intent();
				intent.setClass(Login.this, MainActivity.class);
				startActivity(intent);
				finish();
				//leftLogin();
			}

			@Override
			public void onRenrenAuthError(RenrenAuthError renrenAuthError) {
				handler.post(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(Login.this,
								Login.this.getString(R.string.auth_failed),
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onCancelLogin() {
			}

			@Override
			public void onCancelAuth(Bundle values) {
			}
		};
	}

	/**
	 * 人人登录完了要结束Login
	 */
	private void leftLogin() {
		this.finish();
	}

	/**
	 * start the api list activity
	 */
	private void startApiList() {
		Intent intent = new Intent(this, ApiListActivity.class);
		intent.putExtra(Renren.RENREN_LABEL, renren);
		startActivity(intent);
	}

	public void login_renren(View v) {
		renren.authorize(Login.this, listener);
	}

	@Override
	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss(); // 调用MyConnector的sayBye方法
		}
		super.onDestroy();
	}
}
