package firefist.wei.main.activity;

import java.io.IOException;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import firefist.wei.main.KXActivity;
import firefist.wei.main.R;
import firefist.wei.utils.PhotoUtil;



/**
 * ͼƬ�߿���
 * 
 * @author rendongwei
 * 
 */
public class ImageFilterFrameActivity extends KXActivity {
	private Button mCancel;
	private Button mDetermine;
	private ImageView mDisplay;
	private ImageButton mFrame_1;
	private ImageButton mFrame_2;
	private ImageButton mFrame_3;
	private ImageButton mFrame_4;
	private ImageButton mFrame_5;
	private ImageButton mFrame_6;
	private ImageButton mFrame_7;
	private ImageButton mFrame_8;
	private ImageButton mFrame_9;
	private ImageButton mFrame_10;

	private String mPath;// ͼƬ��ַ
	private Bitmap mOldBitmap;// ��ͼƬ
	private Bitmap mNewBitmap;// ��ͼƬ
	private int mFrameId = 0;// �߿���

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagefilter_frame_activity);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.imagefilter_frame_cancel);
		mDetermine = (Button) findViewById(R.id.imagefilter_frame_determine);
		mDisplay = (ImageView) findViewById(R.id.imagefilter_frame_display);
		mFrame_1 = (ImageButton) findViewById(R.id.imagefilter_frame_frame1);
		mFrame_2 = (ImageButton) findViewById(R.id.imagefilter_frame_frame2);
		mFrame_3 = (ImageButton) findViewById(R.id.imagefilter_frame_frame3);
		mFrame_4 = (ImageButton) findViewById(R.id.imagefilter_frame_frame4);
		mFrame_5 = (ImageButton) findViewById(R.id.imagefilter_frame_frame5);
		mFrame_6 = (ImageButton) findViewById(R.id.imagefilter_frame_frame6);
		mFrame_7 = (ImageButton) findViewById(R.id.imagefilter_frame_frame7);
		mFrame_8 = (ImageButton) findViewById(R.id.imagefilter_frame_frame8);
		mFrame_9 = (ImageButton) findViewById(R.id.imagefilter_frame_frame9);
		mFrame_10 = (ImageButton) findViewById(R.id.imagefilter_frame_frame10);
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				finish();
			}
		});
		mDetermine.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ���idΪ0,����û���κβ���,�����践��ֵ,�����򱣴浱ǰ�޸ĵ�ͼƬ�����ص�ַ
				if (mFrameId == 0) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
					mPath = PhotoUtil.saveToLocal(mNewBitmap);
					Intent intent = new Intent();
					intent.putExtra("path", mPath);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
		mFrame_1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 1;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame1");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
		mFrame_2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 2;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame2");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
		mFrame_3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 3;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame3");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
		mFrame_4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 4;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame4");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
		mFrame_5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 5;
				try {
					mNewBitmap = PhotoUtil.addBigFrame(
							mOldBitmap,
							BitmapFactory.decodeStream(getAssets().open(
									"frames/frame5/mist.png")));
					mDisplay.setImageBitmap(mNewBitmap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFrame_6.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 6;
				try {
					mNewBitmap = PhotoUtil.addBigFrame(
							mOldBitmap,
							BitmapFactory.decodeStream(getAssets().open(
									"frames/frame6/love.png")));
					mDisplay.setImageBitmap(mNewBitmap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		mFrame_7.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 7;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame7");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});

		mFrame_8.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 8;
				try {
					mNewBitmap = PhotoUtil.addBigFrame(
							mOldBitmap,
							BitmapFactory.decodeStream(getAssets().open(
									"frames/frame8/transparent.png")));
					mDisplay.setImageBitmap(mNewBitmap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		mFrame_9.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 9;
				try {
					mNewBitmap = PhotoUtil.addBigFrame(
							mOldBitmap,
							BitmapFactory.decodeStream(getAssets().open(
									"frames/frame9/black.png")));
					mDisplay.setImageBitmap(mNewBitmap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		mFrame_10.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mFrameId = 10;
				mNewBitmap = PhotoUtil.combinateFrame(
						ImageFilterFrameActivity.this, mOldBitmap, "frame10");
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
	}

	private void init() {
		// ��ȡͼƬ��ַ
		mPath = getIntent().getStringExtra("path");
		// ��ȡͼƬ
		mOldBitmap = mKXApplication.getPhoneAlbum(mPath);
		// ��ʾͼƬ
		mDisplay.setImageBitmap(mOldBitmap);
	}

	public void onBackPressed() {
		// ���ضԻ���
		finish();
	}
}