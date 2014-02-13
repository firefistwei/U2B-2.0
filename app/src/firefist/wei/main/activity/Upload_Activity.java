package firefist.wei.main.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import firefist.wei.main.MyConstants;
import firefist.wei.main.R;
import firefist.wei.main.service.MyUploadService;
import firefist.wei.utils.upload.FormFile;
import firefist.wei.utils.upload.SocketHttpRequester;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Upload_Activity extends Activity {

	private EditText titleText;
	private EditText descriptionText;

	private ImageView imageView; // 图片

	Bitmap myBitmap = null;
	private byte[] bytePic;

	File myUpFile = null;

	ProgressDialog progressDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tiny_upload);
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		titleText = (EditText) this.findViewById(R.id.file_title);
		descriptionText = (EditText) this.findViewById(R.id.file_description);
		imageView = (ImageView) this.findViewById(R.id.file_image);

	}

	public void upSave(View v) {
		// 从相册中选取图片
		Intent intent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, 1);

	}

	public void tiny_up_back(View v) {
		this.finish();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1) {
			try {
				// 获得图片的uri
				Uri originalUri = data.getData();
				ContentResolver resolver = this.getContentResolver();

				// 将图片显示在UI上
				bytePic = readStream(resolver.openInputStream(Uri
						.parse(originalUri.toString())));

				if (bytePic != null)
					myBitmap = BitmapFactory.decodeByteArray(bytePic, 0,
							bytePic.length);

				imageView.setImageBitmap(myBitmap);

				/* 获取图片的物理地址，遍历cursor，其中_data字段 就是图片的物理路径 */
				/*Cursor c = resolver.query(originalUri, null, null, null, null);
				c.moveToFirst();
				String pathname = c.getString(c.getColumnIndex("_data"));*/
				
				String[] proj = {MediaStore.Images.Media.DATA};
				Cursor cursor = resolver.query(originalUri, proj, null, null, null);
				//按我个人理解 这个是获得用户选择的图片的索引值 
				int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); 
				cursor.moveToFirst(); 
				//最后根据索引值获取图片路径 
				String pathname = cursor.getString(column_index); 

				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)
						|| Environment.getExternalStorageState().equals(
								Environment.MEDIA_MOUNTED_READ_ONLY)) {

					File uploadFile = new File(pathname);
					if (!uploadFile.exists()){
						uploadFile.mkdir();
					}
					if (uploadFile.exists()) {
						myUpFile = uploadFile;
					}
				}
			} catch (Exception e) {
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void tiny_upload_btn(View v) {
		// 真正的上传

		if (myUpFile != null && titleText.getText().toString().trim() != "") {
			// ！！此时 title 和description 很可能为空，后面还得加上
			String _title = titleText.getText().toString();
			String _description = descriptionText.getText().toString();

			progressDialog = ProgressDialog.show(Upload_Activity.this, "请稍候",
					"正在上传图片...", true, true);
			upload(_title, _description, myUpFile);

		} else {
			Toast.makeText(getApplicationContext(), "图片上传失败，请确认图片或标题不为空", 1)
					.show();
		}
	}

	public void upload(final String title, final String description,
			final File uploadFile) {

		new Thread() {
			public void run() {
				Looper.prepare(); // 为该线程初始化一个消息队列
				String path = MyConstants.WebURL + "UploadPhotoService";
				Map<String, String> params = new HashMap<String, String>();
				if (MyConstants.meUser != null
						&& MyConstants.meUser.getUid() > 0) {
					params.put("uid",
							String.valueOf(MyConstants.meUser.getUid()));
				} else {
					progressDialog.dismiss();
					Toast.makeText(Upload_Activity.this, "用户Id出错，照片上传失败！",
							Toast.LENGTH_LONG).show();
					Looper.loop();
				}
				params.put("title", title);
				params.put("description", description);
				// FormFile(File file, String parameterName, String contentType)
				// 这里的prameterName 在index.jsp中
				/*FormFile formFile = new FormFile(uploadFile, "file",
						"image/pjpeg");*/

				try {

					/*boolean flag = SocketHttpRequester.post(path, params,
							formFile);*/
					String wtf = MyUploadService.uploadPhoto(path,
							params,  uploadFile);
					

					if (Long.valueOf(wtf)>0) { // 上传成功
						progressDialog.dismiss();
						Toast.makeText(Upload_Activity.this, "照片上传成功！",
								Toast.LENGTH_LONG).show();
						Looper.loop();
					} else {
						progressDialog.dismiss();
						Toast.makeText(Upload_Activity.this, "照片上传失败！",
								Toast.LENGTH_LONG).show();
						Looper.loop();
					}
				} catch (Exception e) {

					e.printStackTrace();
				}

				// return false;
			}
		}.start();
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}

	@Override
	protected void onDestroy() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
		super.onDestroy();
	}

}
