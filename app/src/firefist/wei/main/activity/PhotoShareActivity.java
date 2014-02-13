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
 * ��Ƭ������
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
 

	private int mCurrentPosition;// ��ǰͼƬ�ı��
	private String mCurrentPath;// ��ǰͼƬ�ĵ�ַ
	private int mLocationPosition;// ��ǰѡ��ĵ���λ�����б��λ��
	private String[] mAlbums = new String[] { "�ֻ����" };// ���
	private int mAlbumPosition;// ��ǰѡ���������б��λ��

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
				// �رյ�ǰ����
				finish();
			}
		});
		mUpload.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				// ��ʾ��ʾ��Ϣ���رյ�ǰ����
				Toast.makeText(PhotoShareActivity.this, "�ϴ�ͼƬ�ɹ�",
						Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		mDisplaySingle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ����Ƭ��ַ���ݵ���Ƭ�༭��
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
			//��ȡ�µ���Ƭ��ַ
			mCurrentPath = data.getStringExtra("path");
			Map<String, String> map = mKXApplication.mAlbumList
					.get(mCurrentPosition);
			map.put("image_path", mCurrentPath);
			//���½�����ʾ
			
				mDisplaySingle.setImageBitmap(mKXApplication
						.getPhoneAlbum(mCurrentPath));

		}
	}


	protected void onDestroy() {
		super.onDestroy();
	}
}