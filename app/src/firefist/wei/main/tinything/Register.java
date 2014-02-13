package firefist.wei.main.tinything;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import firefist.wei.main.Login;
import firefist.wei.main.MainActivity;
import firefist.wei.main.R;
import firefist.wei.main.domain.User;
import firefist.wei.main.service.CheckUserService;
import firefist.wei.main.service.MyService;
import firefist.wei.utils.Utils;

public class Register extends Activity {

	private EditText mUser;
	private EditText mPassword;
	private EditText mAgain;

	private boolean okay;

	public static String uname = null;
	public static String upasswd = null;

	public static Long register_uid = 0l;

	ProgressDialog pd = null;

	public Context context = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register1);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mUser = (EditText) findViewById(R.id.register_user_edit);
		mPassword = (EditText) findViewById(R.id.register_passwd_edit);
		mAgain = (EditText) findViewById(R.id.register_passwd2_edit);

		context = this;
		okay = true;
	}

	public void register1_back(View v) { // 标题栏 返回按钮
		this.finish();
	}

	public void register1_next(View v) {

		okay = true;

		uname = mUser.getText().toString().trim();
		upasswd = mPassword.getText().toString().trim();

		if (uname.equals("")) {
			okay = false;
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();

		} else if (upasswd.equals("")) {
			okay = false;
			Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();

		} else if (!((mAgain.getText().toString().trim()).equals(upasswd))) {
			okay = false;
			Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();

		}
		pd = ProgressDialog.show(Register.this, "请稍候...", "正在注册...", false);

		getRegister(uname, upasswd);

		/*
		 * if (okay == true) { Toast.makeText(this, "注册成功" + "Uid:" +
		 * register_uid, Toast.LENGTH_SHORT).show();
		 * 
		 * Intent intent = new Intent(Register.this, MainActivity.class);
		 * startActivity(intent); this.finish(); }
		 */
	}

	public void getRegister(final String email, final String passwd) {

		new Thread() {
			public void run() {
				Looper.prepare();
				InputStream inputStream;

				try {
					inputStream = MyService.getRegister();

					String result = Utils.readInputStream(inputStream);
					Long uid = Long.valueOf(result);
					Log.i("Register_Uid", uid + "");

					register_uid = uid;

					if (uid > 0) {
						pd.dismiss();
						success();
						Looper.loop();
					} else {
						pd.dismiss();
						failure();
						Looper.loop();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();
	}

	private void success() {
		Toast.makeText(this, "注册成功" + "账号: " + uname, Toast.LENGTH_SHORT)
				.show();

		Intent intent = new Intent(Register.this, Login.class);
		startActivity(intent);
		this.finish();
	}

	private void failure() {
		Toast.makeText(this, "注册失败" , Toast.LENGTH_SHORT)
				.show();

	}


	@Override
	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss(); // 调用MyConnector的sayBye方法
		}
		super.onDestroy();
	}
}
