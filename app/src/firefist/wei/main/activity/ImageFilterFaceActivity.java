package firefist.wei.main.activity;

import java.io.IOException;
import java.util.LinkedList;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import firefist.wei.main.KXActivity;
import firefist.wei.main.R;
import firefist.wei.main.uibase.FaceImage;
import firefist.wei.main.uibase.FaceImageView;
import firefist.wei.utils.PhotoUtil;


public class ImageFilterFaceActivity extends KXActivity {
	private Button mCancel;
	private Button mDetermine;
	private RelativeLayout mDisplayLayout;
	private FaceImageView mDisplay;
	private ImageButton mFace_1;
	private ImageButton mFace_2;
	private ImageButton mFace_3;
	private ImageButton mFace_4;
	private ImageButton mFace_5;
	private ImageButton mFace_6;
	private ImageButton mFace_7;
	private ImageButton mFace_8;
	private ImageButton mFace_9;
	private ImageButton mFace_10;
	private ImageButton mFace_11;
	private ImageButton mFace_12;
	private ImageButton mFace_13;

	private String mPath;// ͼƬ�ĵ�ַ
	private Bitmap mOldBitmap;// ��ͼƬ
	private Bitmap mNewBitmap;// ��ͼƬ
	private Bitmap mFaceBitmap; // ����ͼƬ
	private int mFaceId = 0;// ������
	private boolean mIsMeasured;// �Ƿ��Ѿ������С
	private float mMaxWidth;// ͼƬ�����
	private float mMaxHeight;// ͼƬ���߶�

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagefilter_face_activity);
		findViewById();
		setListener();
		// ��ȡRelativeLayout�ĸ߶ȺͿ��
		ViewTreeObserver vto = mDisplayLayout.getViewTreeObserver();
		vto.addOnPreDrawListener(new OnPreDrawListener() {

			public boolean onPreDraw() {
				if (mIsMeasured == false) {
					mMaxWidth = mDisplayLayout.getMeasuredWidth();
					mMaxHeight = mDisplayLayout.getMeasuredHeight();
					init();
					mIsMeasured = true;
				}
				return true;
			}
		});
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.imagefilter_face_cancel);
		mDetermine = (Button) findViewById(R.id.imagefilter_face_determine);
		mDisplayLayout = (RelativeLayout) findViewById(R.id.imagefilter_face_display_layout);
		mDisplay = (FaceImageView) findViewById(R.id.imagefilter_face_display);
		mFace_1 = (ImageButton) findViewById(R.id.imagefilter_face_face1);
		mFace_2 = (ImageButton) findViewById(R.id.imagefilter_face_face2);
		mFace_3 = (ImageButton) findViewById(R.id.imagefilter_face_face3);
		mFace_4 = (ImageButton) findViewById(R.id.imagefilter_face_face4);
		mFace_5 = (ImageButton) findViewById(R.id.imagefilter_face_face5);
		mFace_6 = (ImageButton) findViewById(R.id.imagefilter_face_face6);
		mFace_7 = (ImageButton) findViewById(R.id.imagefilter_face_face7);
		mFace_8 = (ImageButton) findViewById(R.id.imagefilter_face_face8);
		mFace_9 = (ImageButton) findViewById(R.id.imagefilter_face_face9);
		mFace_10 = (ImageButton) findViewById(R.id.imagefilter_face_face10);
		mFace_11 = (ImageButton) findViewById(R.id.imagefilter_face_face11);
		mFace_12 = (ImageButton) findViewById(R.id.imagefilter_face_face12);
		mFace_13 = (ImageButton) findViewById(R.id.imagefilter_face_face13);
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ���ضԻ���
				finish();
			}
		});
		mDetermine.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ���idΪ0,����û���κβ���,�����践��ֵ,�����򱣴浱ǰ�޸ĵ�ͼƬ�����ص�ַ
				if (mFaceId == 0) {
					setResult(RESULT_CANCELED);
					finish();
				} else {
					// �����޸ĺ��ͼƬ
					mNewBitmap = Bitmap.createBitmap(mOldBitmap.getWidth(),
							mOldBitmap.getHeight(), Config.ARGB_8888);
					LinkedList<FaceImage> mFaceImages = mDisplay
							.getFaceImages();
					Canvas canvas = new Canvas(mNewBitmap);
					canvas.drawBitmap(mOldBitmap, 0, 0, null);
					for (int i = mFaceImages.size(); i > 0; i--) {
						mFaceImages.get(i - 1).draw(canvas);
					}
					canvas.save(Canvas.ALL_SAVE_FLAG);
					canvas.restore();
					// ���浽����
					mPath = PhotoUtil.saveToLocalPNG(mNewBitmap);
					// ����ͼƬ��ַ���رյ�ǰ����
					Intent intent = new Intent();
					intent.putExtra("path", mPath);
					setResult(RESULT_OK, intent);
					finish();
				}
			}
		});
		mFace_1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 1;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/new_year_1.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_2.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 2;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/new_year_2.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_3.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 3;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/new_year_3.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_4.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 4;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/new_year_4.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_5.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 5;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face_forbite.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_6.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 6;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face_rabbit.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_7.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 7;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face1.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_8.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 8;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face2.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_9.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 9;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face3.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_10.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 10;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face4.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_11.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 11;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face9.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_12.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 12;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face10.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		mFace_13.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				try {
					mFaceId = 13;
					mFaceBitmap = BitmapFactory.decodeStream(getAssets().open(
							"accessories/image_face11.png"));
					mDisplay.addFace(mFaceBitmap);
					mDisplay.invalidate();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void init() {
		// ��ȡͼƬ��ַ
		mPath = getIntent().getStringExtra("path");
		// ��ȡ���ŵ�ͼƬ
		mOldBitmap = zoom(mKXApplication.getPhoneAlbum(mPath));
		// ��ʾͼƬ
		mDisplay.setImageBitmap(mOldBitmap);
	}

	/**
	 * ����ͼƬ
	 * 
	 * @param bitmap
	 *            ��Ҫ���ŵ�ͼƬ
	 * @return ���ź��ͼƬ
	 */
	public Bitmap zoom(Bitmap bitmap) {
		// ��ȡͼƬ�ĸ߶ȺͿ��
		float width = bitmap.getWidth();
		float height = bitmap.getHeight();
		// ��ȡ40dip��pxֵ
		int padding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 40, getResources()
						.getDisplayMetrics());
		// ��������Ⱥ͸߶�
		float maxWidth = mMaxWidth - padding;
		float maxHeight = mMaxHeight - padding;
		// �ж������Ȼ�߶ȳ������ֵ,������,���򷵻�ԭͼƬ
		if (width > maxWidth || height > maxHeight) {
			// ��ȡ���ű���
			float scale = getScale(width, height, maxWidth, maxHeight);

			// ���ź��ͼƬ�Ŀ�Ⱥ͸߶�
			int bitmapWidth = (int) (width * scale);
			int bitmapHeight = (int) (height * scale);
			// �������ŵ�ͼƬ
			Bitmap zoomBitmap = Bitmap.createScaledBitmap(bitmap, bitmapWidth,
					bitmapHeight, true);
			return zoomBitmap;
		} else {
			return bitmap;
		}

	}

	/**
	 * ��ȡ���ű���
	 * 
	 * @param width
	 *            ��ǰͼƬ�Ŀ��
	 * @param height
	 *            ��ǰͼƬ�ĸ߶�
	 * @param maxWidth
	 *            �����
	 * @param maxHeight
	 *            ���߶�
	 * @return
	 */
	private float getScale(float width, float height, float maxWidth,
			float maxHeight) {
		float scaleWidth = maxWidth / width;
		float scaleHeight = maxHeight / height;
		return Math.min(scaleWidth, scaleHeight);
	}



	public void onBackPressed() {
		finish();
	}
}
