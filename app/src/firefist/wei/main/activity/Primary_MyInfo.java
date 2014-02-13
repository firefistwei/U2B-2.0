package firefist.wei.main.activity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.view.RenrenAuthListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import firefist.wei.main.ApiDemoInvoker;
import firefist.wei.main.Login;
import firefist.wei.main.MainActivity;
import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.renren.ApiListActivity;
import firefist.wei.main.renren.RenRenBaseActivity;
import firefist.wei.main.service.MyService;
import firefist.wei.utils.ActivityForResultUtil;
import firefist.wei.utils.PhotoUtil;
import firefist.wei.utils.Utils;

public class Primary_MyInfo extends RenRenBaseActivity {

	private ImageButton mAvatar;
	private Button mAvatarChange;
	private TextView mName;
	private TextView mSig;
	private TextView mSex;

	private TextView mBirthday;

	private TextView mGoodat;
	private TextView mFromschool;

	private LinearLayout layout_name, layout_gender, layout_sig;
	private LinearLayout layout_birthday, layout_school, layout_goodat;

	private Button renren_connect_btn;

	private long mUid;// 当前查看的用户Id
	/*
	 * private UserMoreInfo mUserMoreInfo;// 当前查看的用户的资料数据 private UserInfo
	 * mUserInfo;
	 */

	Context mContext = null;

	int mScreenWidth;
	int mScreenHeight;

	ProgressDialog pd = null;

	private static final String API_KEY = "6b1016db20c540e78bd1b20be4c707a3";

	private static final String SECRET_KEY = "4723a695c09e4ddebbe8d87393d95fb4";

	private static final String APP_ID = "105381";

	private Handler handler;

	private Renren renren;

	private RenrenAuthListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.primary_myinfo);

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		mContext = this;

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;

		findViewById();
		setListener();
		init();
	}

	public void primary_myinfo_done(View v) {

		pd = ProgressDialog.show(Primary_MyInfo.this, null, "正在更新信息...", true, true);
		setUserInfo(MyConstants.user_uid);
	}

	private void setUserInfo(final long uid) {
		new Thread() {
			public void run() {
				Looper.prepare();
				InputStream inputStream;

				try {

					Map<String, String> params = new HashMap<String, String>();
					params.put("action", "complete");
					params.put("uid", String.valueOf(MyConstants.user_uid));
					params.put("nick_name",
							URLEncoder.encode(MyConstants.user_name, "UTF-8"));
					params.put("gender",
							String.valueOf(MyConstants.user_gender));
					params.put("birthday", URLEncoder.encode(
							MyConstants.user_birthday, "UTF-8"));
					params.put("school",
							URLEncoder.encode(MyConstants.user_school, "UTF-8"));
					params.put("signature",
							URLEncoder.encode(MyConstants.user_sig, "UTF-8"));

					inputStream = MyService.setUserInfo(params);

					String result = Utils.readInputStream(inputStream);

					if (result.equals("1")) {
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
		Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();

	}

	private void failure() {
		Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();

	}

	@Override
	protected void onDestroy() {
		if (pd != null) {
			pd.dismiss(); // 调用MyConnector的sayBye方法
		}
		super.onDestroy();
	}

	public void primary_myinfo_back(View v) {
		this.finish();
	}

	private void findViewById() {

		mAvatar = (ImageButton) findViewById(R.id.primary_myinfo_avatar);
		mAvatarChange = (Button) findViewById(R.id.primary_myinfo_avatar_change);
		mName = (TextView) findViewById(R.id.primary_myinfo_name);
		mSig = (TextView) findViewById(R.id.primary_myinfo_sig);
		mSex = (TextView) findViewById(R.id.primary_myinfo_gender);

		mBirthday = (TextView) findViewById(R.id.primary_myinfo_birthday);
		mFromschool = (TextView) findViewById(R.id.primary_myinfo_school);
		mGoodat = (TextView) findViewById(R.id.primary_myinfo_goodat);

		layout_name = (LinearLayout) findViewById(R.id.primary_myinfo_layout1);
		layout_gender = (LinearLayout) findViewById(R.id.primary_myinfo_layout2);
		layout_sig = (LinearLayout) findViewById(R.id.primary_myinfo_layout3);
		layout_birthday = (LinearLayout) findViewById(R.id.primary_myinfo_layout4);
		layout_school = (LinearLayout) findViewById(R.id.primary_myinfo_layout5);
		layout_goodat = (LinearLayout) findViewById(R.id.primary_myinfo_layout6);

		renren_connect_btn = (Button) findViewById(R.id.renren_connect_btn);

	}

	private void init() {

		mUid = MyConstants.user_uid;// 接收传递过来的用户ID

		mName.setText(MyConstants.user_name);
		mSig.setText(MyConstants.user_sig);
		if (MyConstants.user_gender == 0) {
			mSex.setText("Man");
		} else {
			mSex.setText("Woman");
		}
		mBirthday.setText(MyConstants.user_birthday);
		mFromschool.setText(MyConstants.user_school);
		mGoodat.setText(MyConstants.user_goodat);

	}

	@Override
	protected void onResume() {
		super.onResume();
		mUid = MyConstants.user_uid;// 接收传递过来的用户ID

		mName.setText(MyConstants.user_name);
		mSig.setText(MyConstants.user_sig);
		if (MyConstants.user_gender == 0) {
			mSex.setText("Man");
		} else {
			mSex.setText("Woman");
		}
		mBirthday.setText(MyConstants.user_birthday);
		mFromschool.setText(MyConstants.user_school);
		mGoodat.setText(MyConstants.user_goodat);

		// renren.init(this);
	}

	private void setListener() {
		renren_connect_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				init_renren_btn();

			}
		});

		layout_name.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Primary_MyInfo.this,
						Primary_MyInfo_Item.class);
				intent.putExtra("text_action", "name");
				startActivity(intent);

			}
		});
		layout_gender.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mSex.getText().toString().trim().equals("Man")) {
					mSex.setText("Woman");
					MyConstants.user_gender = 1;
				} else {
					mSex.setText("Man");
					MyConstants.user_gender = 0;

				}

			}
		});
		layout_sig.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Primary_MyInfo.this,
						Primary_MyInfo_Item.class);
				intent.putExtra("text_action", "sig");
				startActivity(intent);

			}
		});
		layout_birthday.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Primary_MyInfo.this,
						Primary_MyInfo_Item.class);
				intent.putExtra("text_action", "birthday");
				startActivity(intent);

			}
		});
		layout_school.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Primary_MyInfo.this,
						Primary_MyInfo_Item.class);
				intent.putExtra("text_action", "school");
				startActivity(intent);

			}
		});
		layout_goodat.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Primary_MyInfo.this,
						Primary_MyInfo_Item.class);
				intent.putExtra("text_action", "goodat");
				startActivity(intent);

			}
		});

		mAvatarChange.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new AlertDialog.Builder(mContext)
						.setTitle("修改头像")
						.setItems(new String[] { "拍照上传", "上传相册中的照片" },
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Intent intent = null;
										switch (which) {
										case 0:
											intent = new Intent(
													MediaStore.ACTION_IMAGE_CAPTURE);
											File dir = new File(
													"/sdcard/U2B/Camera");
											if (!dir.exists()) {
												dir.mkdirs();
											}
											MyConstants.UploadPhotoPath = "/sdcard/U2B/Camera"
													+ UUID.randomUUID()
															.toString();
											File file = new File(
													MyConstants.UploadPhotoPath);

											if (!file.exists()) {
												try {
													file.createNewFile();
												} catch (IOException e) {

												}
											}
											intent.putExtra(
													MediaStore.EXTRA_OUTPUT,
													Uri.fromFile(file));
											((Activity) mContext)
													.startActivityForResult(
															intent,
															ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CAMERA);
											break;
										case 1:
											intent = new Intent(
													Intent.ACTION_PICK, null);
											intent.setDataAndType(
													MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
													"image/*");
											((Activity) mContext)
													.startActivityForResult(
															intent,
															ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_LOCATION);
											break;
										}
									}
								}).create().show();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		/**
		 * 通过照相修改头像
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CAMERA:
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				File file = new File(MyConstants.UploadPhotoPath);
				startPhotoZoom(Uri.fromFile(file));
			} else {
				Toast.makeText(this, "取消上传", Toast.LENGTH_SHORT).show();
			}
			break;
		/**
		 * 通过本地修改头像
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_LOCATION:
			Uri uri = null;
			if (data == null) {
				Toast.makeText(this, "取消上传", Toast.LENGTH_SHORT).show();
				return;
			}
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				uri = data.getData();
				startPhotoZoom(uri);
			} else {
				Toast.makeText(this, "照片获取失败", Toast.LENGTH_SHORT).show();
			}
			break;
		/**
		 * 裁剪修改的头像
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CROP:
			if (data == null) {
				Toast.makeText(this, "取消上传", Toast.LENGTH_SHORT).show();
				return;
			} else {
				saveCropPhoto(data);
			}
			break;
		}

		if (renren != null) {
			renren.authorizeCallback(requestCode, resultCode, data);
		}

	}

	/**
	 * 系统裁剪照片
	 * 
	 * @param uri
	 */
	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 200);
		intent.putExtra("scale", true);
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent,
				ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CROP);
	}

	/**
	 * 保存裁剪的照片
	 * 
	 * @param data
	 */
	private void saveCropPhoto(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap bitmap = extras.getParcelable("data");
			bitmap = PhotoUtil.toRoundCorner(bitmap, 15);
			if (bitmap != null) {
				uploadPhoto(bitmap);
			}
		} else {
			Toast.makeText(this, "获取裁剪照片错误", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 更新头像
	 */
	private void uploadPhoto(Bitmap bitmap) {
		mAvatar.setImageBitmap(bitmap);

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

				finish();
				// leftLogin();
			}

			@Override
			public void onRenrenAuthError(RenrenAuthError renrenAuthError) {
				handler.post(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(
								Primary_MyInfo.this,
								Primary_MyInfo.this
										.getString(R.string.auth_failed),
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
	 * start the api list activity
	 */
	private void startApiList() {
		Intent intent = new Intent(this, ApiListActivity.class);
		intent.putExtra(Renren.RENREN_LABEL, renren);
		startActivity(intent);
	}

	public void login_renren(View v) {
		renren.authorize(Primary_MyInfo.this, listener);
	}

}
