package firefist.wei.main.activity;

import java.util.HashMap;
import java.util.Map;

import firefist.wei.main.KXActivity;
import firefist.wei.main.R;
import firefist.wei.utils.ActivityForResultUtil;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;



/**
 * ͼƬ�༭��
 * 
 * @author wei
 * 
 */
public class ImageFilterActivity extends KXActivity {
	private Button mCancel;
	private Button mFinish;
	private ImageButton mBack;
	private ImageButton mForward;
	private ImageView mDisplay;
	private Button mCut;
	private Button mEffect;
	private Button mFace;
	private Button mFrame;

	private String mOldPath;// ��ͼƬ��ַ
	private Bitmap mOldBitmap;// ��ͼƬ
	private String mNewPath;// ��ͼƬ��ַ
	private Bitmap mNewBitmap;// ��ͼƬ
	private boolean mIsOld = true;// �Ƿ���ѡ���˾�ͼƬ
	private boolean mIsSetResult = false;// �Ƿ���Ҫ��������

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagefilter_activity);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.imagefilter_cancel);
		mFinish = (Button) findViewById(R.id.imagefilter_finish);
		mBack = (ImageButton) findViewById(R.id.imagefilter_back);
		mForward = (ImageButton) findViewById(R.id.imagefilter_forward);
		mDisplay = (ImageView) findViewById(R.id.imagefilter_display);
		mCut = (Button) findViewById(R.id.imagefilter_cut);
		mEffect = (Button) findViewById(R.id.imagefilter_effect);
		mFace = (Button) findViewById(R.id.imagefilter_face);
		mFrame = (Button) findViewById(R.id.imagefilter_frame);
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// �رյ�ǰ����
				finish();
			}
		});
		mFinish.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// �ж��Ƿ�Ҫ��������
				if (mIsSetResult) {
					// �����Ƿ�ѡ���ͼƬ����ͼƬ��ַ
					Intent intent = new Intent();
					if (mIsOld) {
						intent.putExtra("path", mOldPath);
					} else {
						intent.putExtra("path", mNewPath);
					}
					setResult(RESULT_OK, intent);
				} else {
					// �����Ƿ�ѡ���ͼƬ���һ���µ�ͼƬ����ת���ϴ�ͼƬ����
					Map<String, String> map = new HashMap<String, String>();
					if (mIsOld) {
						map.put("image_path", mOldPath);
					} else {
						map.put("image_path", mNewPath);
					}
					mKXApplication.mAlbumList.add(map);
					startActivity(new Intent(ImageFilterActivity.this,
							PhotoShareActivity.class));
				}
				finish();
			}
		});
		mBack.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ѡ���ͼƬ
				mIsOld = true;
				mBack.setImageResource(R.drawable.image_action_back_arrow_normal);
				mForward.setImageResource(R.drawable.image_filter_action_forward_arrow);
				mBack.setEnabled(false);
				mForward.setEnabled(true);
				mDisplay.setImageBitmap(mOldBitmap);
			}
		});
		mForward.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ѡ����ͼƬ
				mIsOld = false;
				mBack.setImageResource(R.drawable.image_filter_action_back_arrow);
				mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
				mBack.setEnabled(true);
				mForward.setEnabled(false);
				mDisplay.setImageBitmap(mNewBitmap);
			}
		});
		mCut.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ת���ü�����,������ͼƬ��ַ
				Intent intent = new Intent();
				intent.setClass(ImageFilterActivity.this,
						ImageFilterCropActivity.class);
				if (mIsOld) {
					intent.putExtra("path", mOldPath);
				} else {
					intent.putExtra("path", mNewPath);
				}
				startActivityForResult(intent,
						ActivityForResultUtil.REQUESTCODE_IMAGEFILTER_CROP);
			}
		});
		mEffect.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ת����Ч����,������ͼƬ��ַ
				Intent intent = new Intent();
				intent.setClass(ImageFilterActivity.this,
						ImageFilterEffectActivity.class);
				if (mIsOld) {
					intent.putExtra("path", mOldPath);
				} else {
					intent.putExtra("path", mNewPath);
				}
				startActivityForResult(intent,
						ActivityForResultUtil.REQUESTCODE_IMAGEFILTER_EFFECT);
			}
		});
		mFace.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ת���������,������ͼƬ��ַ
				Intent intent = new Intent();
				intent.setClass(ImageFilterActivity.this,
						ImageFilterFaceActivity.class);
				if (mIsOld) {
					intent.putExtra("path", mOldPath);
				} else {
					intent.putExtra("path", mNewPath);
				}
				startActivityForResult(intent,
						ActivityForResultUtil.REQUESTCODE_IMAGEFILTER_FACE);
			}
		});
		mFrame.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ת���߿����,������ͼƬ��ַ
				Intent intent = new Intent();
				intent.setClass(ImageFilterActivity.this,
						ImageFilterFrameActivity.class);
				if (mIsOld) {
					intent.putExtra("path", mOldPath);
				} else {
					intent.putExtra("path", mNewPath);
				}
				startActivityForResult(intent,
						ActivityForResultUtil.REQUESTCODE_IMAGEFILTER_FRAME);
			}
		});
	}

	private void init() {
		// ��ʼ�����水ť��Ϊ������
		mBack.setImageResource(R.drawable.image_action_back_arrow_normal);
		mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
		mBack.setEnabled(false);
		mForward.setEnabled(false);
		// ��ȡ�Ƿ񷵻�����
		mIsSetResult = getIntent().getBooleanExtra("isSetResult", false);
		// ���մ��ݵ�ͼƬ��ַ
		mOldPath = getIntent().getStringExtra("path");
		mNewPath = getIntent().getStringExtra("path");
		mOldBitmap = mKXApplication.getPhoneAlbum(mOldPath);
		mNewBitmap = mKXApplication.getPhoneAlbum(mNewPath);
		// ��ʾͼƬ
		mDisplay.setImageBitmap(mOldBitmap);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			// �����޸ĺ��ͼƬ��ַ,������
			if (mIsOld) {
				mNewPath = data.getStringExtra("path");
				mNewBitmap = mKXApplication.getPhoneAlbum(mNewPath);
			} else {
				mOldPath = mNewPath;
				mOldBitmap = mNewBitmap;
				mNewPath = data.getStringExtra("path");
				mNewBitmap = mKXApplication.getPhoneAlbum(mNewPath);
			}
			mIsOld = false;
			mBack.setImageResource(R.drawable.image_filter_action_back_arrow);
			mForward.setImageResource(R.drawable.image_action_forward_arrow_normal);
			mBack.setEnabled(true);
			mForward.setEnabled(false);
			mDisplay.setImageBitmap(mNewBitmap);

		}
	}
}
