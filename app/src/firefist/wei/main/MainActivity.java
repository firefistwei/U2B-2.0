package firefist.wei.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.nostra13.universalimageloader.utils.L;

import static firefist.wei.main.MyConstants.IMAGES;

import firefist.wei.main.MyConstants.Extra;

import firefist.wei.main.R;
import firefist.wei.main.activity.ImageFilterActivity;
import firefist.wei.main.activity.ImagePagerActivity;
import firefist.wei.main.activity.Upload_Activity;
import firefist.wei.satellite.SatelliteMenu;
import firefist.wei.satellite.SatelliteMenuItem;
import firefist.wei.satellite.SatelliteMenu.SateliteClickedListener;
import firefist.wei.sliding.fragment.*;
import firefist.wei.sliding.fragment.MainFragment.MyPageChangeListener;

import firefist.wei.sliding.fragment.LeftFragment;
import firefist.wei.sliding.utils.IChangeFragment;
import firefist.wei.sliding.view.SlidingMenu;
import firefist.wei.utils.ActivityForResultUtil;
import firefist.wei.utils.PhotoUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;

public class MainActivity extends FragmentActivity implements IChangeFragment {

	private static final String TAG = "SlidingActivity";

	SlidingMenu mSlidingMenu;
	LeftFragment leftFragment;
	RightFragment rightFragment;
	MainFragment mainFragment;

	public static KXApplication mKXApplication;
	public KXActivity kxActivity;

	public static MainActivity instance = null;

	/**
	 * 屏幕的宽度和高度
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		instance = this;

		kxActivity = new KXActivity();
		mKXApplication = KXActivity.mKXApplication;

		init();
		initListener(mainFragment);

		/**
		 * 获取屏幕宽度和高度
		 */
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;

	}

	private void init() {

		File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
		if (!testImageOnSdCard.exists()) {
			copyTestImageToSdCard(testImageOnSdCard);
		}

		mSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);

		mSlidingMenu.setLeftView(getLayoutInflater().inflate(
				R.layout.left_frame, null));
		mSlidingMenu.setRightView(getLayoutInflater().inflate(
				R.layout.right_frame, null));
		mSlidingMenu.setCenterView(getLayoutInflater().inflate(
				R.layout.center_frame, null));

		FragmentTransaction t = this.getSupportFragmentManager()
				.beginTransaction();

		leftFragment = new LeftFragment(this.getSupportFragmentManager());
		leftFragment.setChangeFragmentListener(this);
		t.replace(R.id.left_frame, leftFragment);

		rightFragment = new RightFragment(this.getSupportFragmentManager());
		t.replace(R.id.right_frame, rightFragment);

		mainFragment = new MainFragment(this);
		t.replace(R.id.center_frame, mainFragment);
		t.commit();

	}

	private void initListener(final MainFragment fragment) {
		fragment.setMyPageChangeListener(new MyPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				Log.e(TAG, "onPageSelected : " + position);
				if (fragment.isFirst()) {
					mSlidingMenu.setCanSliding(true, false);

				} else if (fragment.isEnd()) {
					mSlidingMenu.setCanSliding(false, true);
				} else {
					mSlidingMenu.setCanSliding(false, false);
				}
			}
		});
	}

	public void showLeft() {
		mSlidingMenu.showLeftView();
	}

	public void showRight() {
		mSlidingMenu.showRightView();
	}

	@Override
	public void changeFragment(int position) {
		/*
		 * FragmentTransaction t = this.getSupportFragmentManager()
		 * .beginTransaction(); Fragment fragment = null; switch(position){ case
		 * 0: // fragment = new MainFragment(this); //
		 * initListener((MainFragment) fragment); fragment = new
		 * PageFragment1(); break; case 1: fragment = new
		 * PageFragment2(this,this,KXActivity.mKXApplication); break; case 2:
		 * fragment = new Fragment(); break;
		 * 
		 * } t.replace(R.id.center_frame, fragment); t.commit();
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		mSlidingMenu.getCenterView().scrollTo(0, 0);

		mSlidingMenu.getLeftView().setVisibility(View.INVISIBLE);
		mSlidingMenu.getRightView().setVisibility(View.INVISIBLE);
		mSlidingMenu.setCanSliding(true, false);

		/*
		 * WindowManager windowManager = ((Activity)
		 * getApplicationContext()).getWindow() .getWindowManager(); Display
		 * display = windowManager.getDefaultDisplay(); int screenWidth =
		 * display.getWidth();
		 * 
		 * int screenHeight = display.getHeight();
		 * 
		 * LayoutParams bgParams = new LayoutParams(screenWidth, screenHeight);
		 * bgParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		 * mSlidingMenu.getCenterView().bringToFront();
		 */

	}

	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";

	private void copyTestImageToSdCard(final File testImageOnSdCard) {
		new Thread(new Runnable() {
			public void run() {
				try {
					InputStream is = getAssets().open(TEST_FILE_NAME);
					FileOutputStream fos = new FileOutputStream(
							testImageOnSdCard);
					byte[] buffer = new byte[8192];
					int read;
					try {
						while ((read = is.read(buffer)) != -1) {
							fos.write(buffer, 0, read);
						}
					} finally {
						fos.flush();
						fos.close();
						is.close();
					}
				} catch (IOException e) {
					L.w("Can't copy test image onto SD card");
				}
			}
		}).start();
	}

	public void head_up_photo(View v) {
		PhotoDialog();
	}

	/**
	 * 照片对话框
	 */
	private void PhotoDialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("冒死上传2B照片");
		builder.setItems(new String[] { "拍照", "从相册中选择" },
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Intent intent = null;
						switch (which) {
						case 0:
							intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							File dir = new File("/sdcard/U2B/Camera/");
							if (!dir.exists()) {
								dir.mkdirs();
							}
							MyConstants.UploadPhotoPath = "/sdcard/U2B/Camera/"
									+ UUID.randomUUID().toString();
							File file = new File(MyConstants.UploadPhotoPath);

							if (!file.exists()) {
								try {
									file.createNewFile();
								} catch (IOException e) {

								}
							}
							intent.putExtra(MediaStore.EXTRA_OUTPUT,
									Uri.fromFile(file));
							startActivityForResult(
									intent,
									ActivityForResultUtil.REQUESTCODE_UPLOADPHOTO_CAMERA);
							break;

						case 1:
							startActivity(new Intent(MainActivity.this,
									Upload_Activity.class));
							break;
						}
					}
				});
		builder.create().show();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		/**
		 * 通过照相上传图片
		 */
		case ActivityForResultUtil.REQUESTCODE_UPLOADPHOTO_CAMERA:
			if (resultCode == RESULT_OK) {
				if (!Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Toast.makeText(this, "SD不可用", Toast.LENGTH_SHORT).show();
					return;
				}
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ImageFilterActivity.class);
				String path = PhotoUtil.saveToLocal(PhotoUtil.createBitmap(
						MyConstants.UploadPhotoPath, mScreenWidth,
						mScreenHeight));
				intent.putExtra("path", path);
				startActivity(intent);
			} else {
				Toast.makeText(this, "取消上传", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getRepeatCount() == 0) {
			
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Exit.class);
				startActivity(intent);
			}
		return false;
		
	}
}
