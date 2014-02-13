package firefist.wei.main.activity;

import java.io.InputStream;
import java.util.Map;

import org.json.JSONArray;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import firefist.wei.main.KXActivity;
import firefist.wei.main.R;
import firefist.wei.utils.TextUtil;



/**
 * 照片分享类
 * 
 * @author rendongwei
 * 
 */
public class PhotoShareActivity extends KXActivity {
	private Button mCancel;
	private Button mUpload;
	private ImageView mDisplaySingle;
	private TextView mLocation;
	private Button mDelete;
	private TextView mAlbum;
 

	private int mCurrentPosition;// 当前图片的编号
	private String mCurrentPath;// 当前图片的地址
	private int mLocationPosition;// 当前选择的地理位置在列表的位置
	private String[] mAlbums = new String[] { "手机相册" };// 相册
	private int mAlbumPosition;// 当前选择的相册在列表的位置

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photoshare_activity);
		findViewById();
		setListener();
		init();
	}

	private void findViewById() {
		mCancel = (Button) findViewById(R.id.photoshare_cannel);
		mUpload = (Button) findViewById(R.id.photoshare_upload);
		mDisplaySingle = (ImageView) findViewById(R.id.photoshare_display_single);
		
	}

	private void setListener() {
		mCancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 关闭当前界面
				finish();
			}
		});
		mUpload.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				// 显示提示信息并关闭当前界面
				Toast.makeText(PhotoShareActivity.this, "上传图片成功",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		mDisplaySingle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// 将照片地址传递到照片编辑类
				Intent intent = new Intent();
				intent.setClass(PhotoShareActivity.this,
						ImageFilterActivity.class);
				intent.putExtra("path", mCurrentPath);
				intent.putExtra("isSetResult", true);
				startActivityForResult(intent, 0);
			}
		});
		
		
		
	}

	private void init() {
		
			mDisplaySingle.setVisibility(View.VISIBLE);
			mCurrentPosition = 0;
			mCurrentPath = mKXApplication.mAlbumList.get(mCurrentPosition).get(
					"image_path");
			mDisplaySingle.setImageBitmap(mKXApplication
					.getPhoneAlbum(mCurrentPath));
		
	}


	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			//获取新的照片地址
			mCurrentPath = data.getStringExtra("path");
			Map<String, String> map = mKXApplication.mAlbumList
					.get(mCurrentPosition);
			map.put("image_path", mCurrentPath);
			//更新界面显示
			
				mDisplaySingle.setImageBitmap(mKXApplication
						.getPhoneAlbum(mCurrentPath));

		}
	}


	protected void onDestroy() {
		super.onDestroy();
	}
}