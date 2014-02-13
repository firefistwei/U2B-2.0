/**
 * $id$
 */
package firefist.wei.main.renren;

import com.renren.api.connect.android.Renren;

import firefist.wei.main.R;
import firefist.wei.main.R.id;
import firefist.wei.main.R.layout;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Base class for request Activity in the demo application
 * 
 * @author Shaofeng Wang (shaofeng.wang@renren-inc.com)
 */
public class RenRenBaseActivity extends Activity {
	/**
	 * �����ֶ���
	 */
	protected LinearLayout root;

	/**
	 * ��������߰�ť
	 */
	protected Button titlebarLeftButton;

	/**
	 * �������м�����
	 */
	protected TextView titlebarText;

	/**
	 * �������ұ߰�ť
	 */
	protected Button titlebarRightButton;

	private ProgressDialog progressDialog;

	/**
	 * ����SDK�ӿڵ�Renren����
	 */
	protected Renren renren;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ò���ʾ������
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// ���ò���




		Intent intent = getIntent();
		renren = intent.getParcelableExtra(Renren.RENREN_LABEL);
		if (renren != null) {
			renren.init(this);
		}
	}

	/**
	 * �������ŷָ���ַ���
	 * 
	 * @return
	 */
	protected String[] parseCommaIds(String s) {
		if (s == null) {
			return null;
		}
		String[] ids = s.split(",");
		return ids;
	}

	/**
	 * ��ʾ�ȴ���
	 */
	protected void showProgress() {
		showProgress("Please wait", "progressing");
	}

	/**
	 * ��ʾ�ȴ���
	 * 
	 * @param title
	 * @param message
	 */
	protected void showProgress(String title, String message) {
		progressDialog = ProgressDialog.show(this, title, message);
	}

	/**
	 * ȡ���ȴ���
	 */
	protected void dismissProgress() {
		if (progressDialog != null) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * ��ʾToast��ʾ
	 * 
	 * @param message
	 */
	protected void showTip(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ������ProgressDialog�����view not attached to window manager�쳣
		dismissProgress();
	}

}
