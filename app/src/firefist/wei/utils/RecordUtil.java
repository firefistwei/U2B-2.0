package firefist.wei.utils;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

import android.media.MediaRecorder;

/**
 * ¼��������
 * 
 * @author rendongwei
 * 
 */
public class RecordUtil {
	private static final int SAMPLE_RATE_IN_HZ = 8000;
	private MediaRecorder recorder = null;
	// ¼����·��
	private String mPath;

	public RecordUtil(String path) {
		mPath = path;
	}

	/**
	 * ��ʼ¼��
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		String state = android.os.Environment.getExternalStorageState();
		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
			throw new IOException("SD Card is not mounted,It is  " + state
					+ ".");
		}
		File directory = new File(mPath).getParentFile();
		if (!directory.exists()) {
			directory.mkdirs();
			// throw new IOException("Path to file could not be created");
		}
		
		recorder = new MediaRecorder();
		// ������ԴΪMicphone
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);

		// ���÷�װ��ʽ
		recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
		recorder.setOutputFile(mPath);
		// ���ñ����ʽ
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setAudioSamplingRate(SAMPLE_RATE_IN_HZ);
		recorder.setOutputFile(mPath);
		try {
			recorder.prepare();
		} catch (IOException e) {
		}
		recorder.start();
	}

	/**
	 * ����¼��
	 * 
	 * @throws IOException
	 */
	public void stop() throws IOException {
		recorder.stop();
		recorder.release();
		
		recorder = null;
	}

	/**
	 * ��ȡ¼��ʱ��
	 * 
	 * @return
	 */
	public double getAmplitude() {
		if (recorder != null) {
			return (recorder.getMaxAmplitude());
		}
		return 0;
	}
}
