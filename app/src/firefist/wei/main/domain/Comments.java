package firefist.wei.main.domain;

public class Comments {

	private int comment_id;
	private String comment_content;
	private long pid;
	private long uid;
	private String url; //Â¼ÒôµÄurl
	private String comment_time;

	public Comments() {

	}

	public Comments(int comment_id, String comment_content, long pid, long uid,
			String url, String comment_time) {
		super();
		this.comment_id = comment_id;
		this.comment_content = comment_content;
		this.pid = pid;
		this.uid = uid;
		this.url = url;
		this.comment_time = comment_time;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

}
