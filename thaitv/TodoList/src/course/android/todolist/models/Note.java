package course.android.todolist.models;

import java.io.Serializable;

public class Note implements Serializable {
	public static final String SUCCESS = "success";
	public static final String UNSUCCESS = "unsuccess";
	
	private String mTitle;
	private String mStatusCode;
	private String mDeadLine;
	private String mDetail;

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	/**
	 * @param title
	 * @param statusCode
	 * @param deadLine
	 * @param detail
	 */
	public Note(String title, String statusCode, String deadLine,
			String detail) {
		super();
		this.mTitle = title;
		this.mStatusCode = statusCode;
		this.mDeadLine = deadLine;
		this.mDetail = detail;
	}

	public String getDetail() {
		return mDetail;
	}

	public void setDetail(String detail) {
		this.mDetail = detail;
	}

	public String getDeadLine() {
		return mDeadLine;
	}

	public void setDeadLine(String deadLine) {
		this.mDeadLine = deadLine;
	}

	public String getStatusCode() {
		return mStatusCode;
	}

	public void setStatusCode(String statusCode) {
		this.mStatusCode = statusCode;
	}
	
	
	
	
}
