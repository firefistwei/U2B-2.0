package firefist.wei.main.right;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.utils.ActivityForResultUtil;
import firefist.wei.utils.PhotoUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Right_MyInfo extends Activity {

	private TextView mTitle;
	private ImageButton mAvatar;
	private Button mAvatarChange;
	private TextView mName;
	private TextView mSig;
	private ImageView mSex;

	private TextView mSingle;
	private TextView mBirthday;
	private TextView mConstellation; // ����
	private TextView mHometown;
	private TextView mLivingplace;
	private TextView mPhonenumber;
	private TextView mGoodat;
	private TextView mFromschool;
	private TextView mFromcompany;

	private ImageView mSingleIcon;
	private ImageView mBirthdayIcon;
	private ImageView mConstellationIcon;
	private ImageView mHometownIcon;
	private ImageView mGoodatIcon;
	private ImageView mFromschoolIcon;
	private ImageView mFromcompanyIcon;
	private ImageView mLivingplaceIcon;
	private ImageView mPhonenumberIcon;

	private int mUid;// ��ǰ�鿴���û�Id
	/*
	 * private UserMoreInfo mUserMoreInfo;// ��ǰ�鿴���û����������� private UserInfo
	 * mUserInfo;
	 */

	Context mContext = null;

	int mScreenWidth;
	int mScreenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.right_myinfo);

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

	public void right_myinfo_back(View v) {
		this.finish();
	}

	private void findViewById() {

		mAvatar = (ImageButton) findViewById(R.id.about_avatar);
		mAvatarChange = (Button) findViewById(R.id.about_avatar_change);
		mName = (TextView) findViewById(R.id.about_name);
		mSig = (TextView) findViewById(R.id.about_signature);
		mSex = (ImageView) findViewById(R.id.about_sex);

		mBirthday = (TextView) findViewById(R.id.about_birthday);
		mConstellation = (TextView) findViewById(R.id.about_constellation);

		mLivingplace = (TextView) findViewById(R.id.about_livingplace);
		mPhonenumber = (TextView) findViewById(R.id.about_phonenumber);
		mGoodat = (TextView) findViewById(R.id.about_goodat);
		mFromschool = (TextView) findViewById(R.id.about_fromschool);
		mFromcompany = (TextView) findViewById(R.id.about_fromcompany);

		mBirthdayIcon = (ImageView) findViewById(R.id.about_birthday_icon);
		mConstellationIcon = (ImageView) findViewById(R.id.about_constellation_icon);
		mFromschoolIcon = (ImageView) findViewById(R.id.about_fromschool_icon);
		mFromcompanyIcon = (ImageView) findViewById(R.id.about_fromcompany_icon);
		mLivingplaceIcon = (ImageView) findViewById(R.id.about_livingplace_icon);
		mPhonenumberIcon = (ImageView) findViewById(R.id.about_phonenumber_icon);
	}

	private void setListener() {

		mAvatarChange.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new AlertDialog.Builder(mContext)
						.setTitle("�޸�ͷ��")
						.setItems(new String[] { "�����ϴ�", "�ϴ�����е���Ƭ" },
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
													"/sdcard/LightTower/Camera");
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

	private void init() {

		mUid = getIntent().getIntExtra("uid", 0);// ���մ��ݹ������û�ID

		/*
		 * // ��Id������ʱΪ��ǰ��¼�û�,�������������û�,�����û��Ĳ�ͬ,��ʾ��ͬ����Ч�� if (mUid == 0) {
		 * 
		 * // ͨ��uid���� ���������ݿ��ж�Ӧ�� UserMoreInfo // ����ֻ�ǲ������� mUserMoreInfo = new
		 * UserMoreInfo(301, 1, "1992-3-29", "������", "����", "����", "133091818**",
		 * "��׿", "������ѧ", "��"); mUserInfo = Constants.me_UserInfo;
		 * 
		 * mTitle.setText("�ҵ�����"); mAvatarChange.setVisibility(View.VISIBLE);
		 * 
		 * mSingleIcon.setVisibility(View.VISIBLE);
		 * mBirthdayIcon.setVisibility(View.VISIBLE);
		 * mConstellation.setVisibility(View.VISIBLE);
		 * mHometown.setVisibility(View.VISIBLE);
		 * mLivingplaceIcon.setVisibility(View.VISIBLE);
		 * mPhonenumberIcon.setVisibility(View.VISIBLE);
		 * mGoodatIcon.setVisibility(View.VISIBLE);
		 * mFromschoolIcon.setVisibility(View.VISIBLE);
		 * mFromcompanyIcon.setVisibility(View.VISIBLE);
		 * 
		 * } else {
		 * 
		 * // ͨ��uid���� ���������ݿ��ж�Ӧ�� UserMoreInfo // ����ֻ�ǲ������� mUserMoreInfo = new
		 * UserMoreInfo(301, 1, "1992-3-29", "������", "����", "����", "133091818**",
		 * "��׿", "������ѧ", "��"); mUserInfo = Constants.me_UserInfo;
		 * 
		 * mTitle.setText(mUserInfo.getName() + "������");
		 * mAvatarChange.setVisibility(View.INVISIBLE);
		 * 
		 * mSingleIcon.setVisibility(View.GONE);
		 * mBirthdayIcon.setVisibility(View.GONE);
		 * mConstellation.setVisibility(View.GONE);
		 * mHometown.setVisibility(View.GONE);
		 * mLivingplaceIcon.setVisibility(View.GONE);
		 * mPhonenumberIcon.setVisibility(View.GONE);
		 * mGoodatIcon.setVisibility(View.GONE);
		 * mFromschoolIcon.setVisibility(View.GONE);
		 * mFromcompanyIcon.setVisibility(View.GONE);
		 * 
		 * }
		 * 
		 * // ����������
		 * 
		 * mAvatar.setImageResource(R.drawable.random_head0);
		 * 
		 * mName.setText(mUserInfo.getName()); mSig.setText(new
		 * TextUtil().replace(mUserInfo.getSig())); if (mUserInfo.getSex() == 1)
		 * { mSex.setImageResource(R.drawable.sex_1); } else {
		 * mSex.setImageResource(R.drawable.sex_2); } //
		 * mSex.setText(Utils.getGender(mUserInfo.getSex())); if
		 * (mUserMoreInfo.getSingle() == 1) { mSingle.setText("��"); } else if
		 * (mUserMoreInfo.getSingle() == 2) { mSingle.setText("��"); } else {
		 * mSingle.setText("����"); }
		 * mBirthday.setText(mUserMoreInfo.getBirthday());
		 * mConstellation.setText(mUserMoreInfo.getConstellation());
		 * mHometown.setText(mUserMoreInfo.getHometown());
		 * mLivingplace.setText(mUserMoreInfo.getLivingplace());
		 * mPhonenumber.setText(mUserMoreInfo.getPhonenumber());
		 * mGoodat.setText(mUserMoreInfo.getGoodat());
		 * mFromschool.setText(mUserMoreInfo.getFromschool());
		 * mFromcompany.setText(mUserMoreInfo.getFromcompany());
		 */
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		/**
		 * ͨ�������޸�ͷ��
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CAMERA:
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD������", Toast.LENGTH_SHORT).show();
					return;
				}
				File file = new File(MyConstants.UploadPhotoPath);
				startPhotoZoom(Uri.fromFile(file));
			} else {
				Toast.makeText(this, "ȡ���ϴ�", Toast.LENGTH_SHORT).show();
			}
			break;
		/**
		 * ͨ�������޸�ͷ��
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_LOCATION:
			Uri uri = null;
			if (data == null) {
				Toast.makeText(this, "ȡ���ϴ�", Toast.LENGTH_SHORT).show();
				return;
			}
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD������", Toast.LENGTH_SHORT).show();
					return;
				}
				uri = data.getData();
				startPhotoZoom(uri);
			} else {
				Toast.makeText(this, "��Ƭ��ȡʧ��", Toast.LENGTH_SHORT).show();
			}
			break;
		/**
		 * �ü��޸ĵ�ͷ��
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADAVATAR_CROP:
			if (data == null) {
				Toast.makeText(this, "ȡ���ϴ�", Toast.LENGTH_SHORT).show();
				return;
			} else {
				saveCropPhoto(data);
			}
			break;
		}

	}

	/**
	 * ϵͳ�ü���Ƭ
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
	 * ����ü�����Ƭ
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
			Toast.makeText(this, "��ȡ�ü���Ƭ����", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * ����ͷ��
	 */
	private void uploadPhoto(Bitmap bitmap) {
		mAvatar.setImageBitmap(bitmap);

	}

}
