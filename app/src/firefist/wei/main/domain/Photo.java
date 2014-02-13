package firefist.wei.main.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable{
	private long pid;
	private String url;// ·��
	private long uid;// ������һ���û�
	private String date;// ��������
	private String title;
	private String information;// �û�����Ƭ������
	private int like_max;
	private int comment_max;
	
	public Photo(){
		
	}

	public Photo(long pid, String url, long uid, String date, String title,
			String information, int like_max, int comment_max) {
		super();
		this.pid = pid;
		this.url = url;
		this.uid = uid;
		this.date = date;
		this.title = title;
		this.information = information;
		this.like_max = like_max;
		this.comment_max = comment_max;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getLike_max() {
		return like_max;
	}

	public void setLike_max(int like_max) {
		this.like_max = like_max;
	}

	public int getComment_max() {
		return comment_max;
	}

	public void setComment_max(int comment_max) {
		this.comment_max = comment_max;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	/*int pid, String url, int uid, String date, String title,
	String information, int like_max, int comment_max*/
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(pid);
		dest.writeString(url);
		dest.writeLong(uid);
		dest.writeString(date);
		dest.writeString(title);
		dest.writeString(information);
		dest.writeInt(like_max);
		dest.writeInt(comment_max);
	}

	public static final Parcelable.Creator<Photo> CREATOR 
			= new Parcelable.Creator<Photo>() {
		public Photo createFromParcel(Parcel source) {
			Photo result = new Photo();
			result.setPid(source.readLong());
			result.setUrl(source.readString());
			result.setUid(source.readLong());
			
			result.setDate(source.readString());
			result.setTitle(source.readString());
			result.setInformation(source.readString());
			
			result.setLike_max(source.readInt());
			result.setComment_max(source.readInt());
			return result;
		}

		public Photo[] newArray(int size) {
			return new Photo[size];
		}
		
	};
	
}
