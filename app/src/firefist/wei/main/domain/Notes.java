package firefist.wei.main.domain;

public class Notes {

	private int uid;
	private int num_note;
	private int con_note;
	
	public Notes(){
		
	}
	
	public Notes(int uid, int num_note, int con_note) {
		super();
		this.uid = uid;
		this.num_note = num_note;
		this.con_note = con_note;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getNum_note() {
		return num_note;
	}
	public void setNum_note(int num_note) {
		this.num_note = num_note;
	}
	public int getCon_note() {
		return con_note;
	}
	public void setCon_note(int con_note) {
		this.con_note = con_note;
	}
	@Override
	public String toString() {
		return "Notes [uid=" + uid + ", num_note=" + num_note + ", con_note="
				+ con_note + "]";
	}
	
	
	
	
}
