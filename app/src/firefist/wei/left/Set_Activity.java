package firefist.wei.left;

import firefist.wei.main.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;
import android.view.Window;
import android.view.WindowManager;

public class Set_Activity extends PreferenceActivity implements
		OnPreferenceClickListener {
	private Preference mMessageRefreshInterval;
	private Preference mNewMessageDetectInterval;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		findViewByKey();
		setListener();
	}

	private void findViewByKey() {
		mMessageRefreshInterval = (Preference) findPreference("message_refresh_interval");
		mNewMessageDetectInterval = (Preference) findPreference("new_message_detect_interval");
	}

	private void setListener() {
		mMessageRefreshInterval.setOnPreferenceClickListener(this);
		mNewMessageDetectInterval.setOnPreferenceClickListener(this);
	}

	public boolean onPreferenceClick(Preference preference) {
		if (preference == mMessageRefreshInterval) {
			RefreshDialog();
		} else if (preference == mNewMessageDetectInterval) {
			DetectDialog();
		}
		return false;
	}

	/**
	 * ˢ�¼���Ի���
	 */
	private void RefreshDialog() {
		final String[] items = { "��Сʱ", "1Сʱ", "2Сʱ", "4Сʱ" };
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("ˢ�¼��");
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				mMessageRefreshInterval.setSummary(items[which]);
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/**
	 * ����Ϣ���ʱ�����Ի���
	 */
	private void DetectDialog() {
		final String[] items = { "1����", "10����", "30����", "1Сʱ", "2Сʱ", "4Сʱ" };
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("����Ϣ���ʱ����");
		builder.setSingleChoiceItems(items, 0, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				mNewMessageDetectInterval.setSummary(items[which]);
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}