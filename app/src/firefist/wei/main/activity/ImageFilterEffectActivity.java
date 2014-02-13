package firefist.wei.main.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import firefist.wei.main.KXActivity;
import firefist.wei.main.R;
import firefist.wei.utils.PhotoUtil;



/**
 * ͼƬ��Ч��
 * 
 * @author rendongwei
 * 
 */
public class ImageFilterEffectActivity extends KXActivity {
	private Button mCancel;
	private Button mDetermine;
	private ImageView mDisplay;
	private ImageButton mEffect_1;
	private ImageButton mEffect_2;
	private ImageButton mEffect_3;
	private ImageButton mEffect_4;
	private ImageButton mEffect_5;
	private ImageButton mEffect_6;
	private ImageButton mEffect_7;
	private ImageButton mEffect_8;
	private ImageButton mEffect_9;
	private ImageButton mEffect_10;

	private String mPath;// ͼƬ��ַ
	private Bitmap mOldBitmap;// ��ͼƬ
	private Bitmap mNewBitmap;// ��ͼƬ
	private int mEffectId = 1;// ��Ч�ı��

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagefilter_effect_activity);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.imagefilter_effect_cancel);
		mDetermine = (Button) findViewById(R.id.imagefilter_effect_determine);
		mDisplay = (ImageView) findViewById(R.id.imagefilter_effect_display);
		mEffect_1 = (ImageButton) findViewById(R.id.imagefilter_effect_effect1);
		mEffect_2 = (ImageButton) findViewById(R.id.imagefilter_effect_effect2);
		mEffect_3 = (ImageButton) findViewById(R.id.imagefilter_effect_effect3);
		mEffect_4 = (ImageButton) findViewById(R.id.imagefilter_effect_effect4);
		mEffect_5 = (ImageButton) findViewById(R.id.imagefilter_effect_effect5);
		mEffect_6 = (ImageButton) findViewById(R.id.imagefilter_effect_effect6);
		mEffect_7 = (ImageButton) findViewById(R.id.imagefilter_effect_effect7);
		mEffect_8 = (ImageButton) findViewById(R.id.imagefilter_effect_effect8);
		mEffect_9 = (ImageButton) findViewById(R.id.imagefilter_effect_effect9);
		mEffect_10 = (ImageButton) findViewById(R.id.imagefilter_effect_effect10);
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ��ʾ���ضԻ���
				finish();
			}
		});
		mDetermine.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// �����1����ԭͼ,�����κβ�������,�����򱣴�ͼƬ�����ز����ص�ַ
				if (mEffectId == 1) {
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
		// ԭͼ
		mEffect_1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mDisplay.setImageBitmap(mOldBitmap);
				mEffectId = 1;
			}
		});
		// Lomo
		mEffect_2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mNewBitmap = PhotoUtil.lomoFilter(mOldBitmap);
				mDisplay.setImageBitmap(mNewBitmap);
				mEffectId = 2;
			}
		});
		// ӡ����
		mEffect_3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(ImageFilterEffectActivity.this, "��ʱû���ҵ���Ч��",
						Toast.LENGTH_SHORT).show();
			}
		});
		// ����
		mEffect_4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(ImageFilterEffectActivity.this, "��ʱû���ҵ���Ч��",
						Toast.LENGTH_SHORT).show();
			}
		});
		// С����
		mEffect_5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(ImageFilterEffectActivity.this, "��ʱû���ҵ���Ч��",
						Toast.LENGTH_SHORT).show();
			}
		});
		// ����
		mEffect_6.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mNewBitmap = PhotoUtil.handleImage(mOldBitmap, 0, 127, 127);
				mDisplay.setImageBitmap(mNewBitmap);
				mEffectId = 6;
			}
		});
		// ����
		mEffect_7.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(ImageFilterEffectActivity.this, "��ʱû���ҵ���Ч��",
						Toast.LENGTH_SHORT).show();
			}
		});
		// ����
		mEffect_8.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Toast.makeText(ImageFilterEffectActivity.this, "��ʱû���ҵ���Ч��",
						Toast.LENGTH_SHORT).show();
			}
		});
		// ��ʱ��
		mEffect_9.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mNewBitmap = PhotoUtil.oldTimeFilter(mOldBitmap);
				mDisplay.setImageBitmap(mNewBitmap);
				mEffectId = 9;
			}
		});
		// ů��
		mEffect_10.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mNewBitmap = PhotoUtil.warmthFilter(mOldBitmap,
						mOldBitmap.getWidth() / 2, mOldBitmap.getHeight() / 2);
				mDisplay.setImageBitmap(mNewBitmap);
				mEffectId = 10;
			}
		});
	}

	private void init() {
		// ��ȡͼƬ��ַ��ͼƬ����ʾͼƬ
		mPath = getIntent().getStringExtra("path");
		mOldBitmap = mKXApplication.getPhoneAlbum(mPath);
		mDisplay.setImageBitmap(mOldBitmap);
	}


	public void onBackPressed() {
		finish();
	}
}