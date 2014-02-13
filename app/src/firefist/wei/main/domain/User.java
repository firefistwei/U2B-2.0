package firefist.wei.main.domain;

import java.util.Date;
import java.util.List;

public class User {

	private long uid;
	private String nick_name;
	private String head_url;
	private String email;
	private String password;

	private int gender;// 性别   0男  1女
	private int degree;// 级别
	private String signature;// 签名
	private String birthday;
	private String school;

	private int score;
	private String register_day;
	private int upload_max;

	/*
	 * private List<Friend> friends;// 好友 private List<Photo> Album;// 照片集
	 */
	public User() {
	}

	public User(long uid, String nick_name, String head_url, String email,
			String password, int gender, int degree, String signature,
			String birthday, String school, int score, String register_day,
			int upload_max) {
		super();
		this.uid = uid;
		this.nick_name = nick_name;
		this.head_url = head_url;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.degree = degree;
		this.signature = signature;
		this.birthday = birthday;
		this.school = school;
		this.score = score;
		this.register_day = register_day;
		this.upload_max = upload_max;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getHead_url() {
		return head_url;
	}

	public void setHead_url(String head_url) {
		this.head_url = head_url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRegister_day() {
		return register_day;
	}

	public void setRegister_day(String register_day) {
		this.register_day = register_day;
	}

	public int getUpload_max() {
		return upload_max;
	}

	public void setUpload_max(int upload_max) {
		this.upload_max = upload_max;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", nick_name=" + nick_name + ", head_url="
				+ head_url + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", degree=" + degree + ", signature="
				+ signature + ", birthday=" + birthday + ", school=" + school
				+ ", score=" + score + ", register_day=" + register_day
				+ ", upload_max=" + upload_max + "]";
	}

}
