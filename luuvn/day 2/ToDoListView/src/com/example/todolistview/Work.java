package com.example.todolistview;

public class Work {
	private String mWorkContent;
	private String mTimeContent;
	private boolean mIsChecked;

	public void setWorkContent(String WorkContent) {
		this.mWorkContent = WorkContent;
	}

	public String getWorkContent() {
		return mWorkContent;
	}

	public void setTimeContent(String TimeContent) {
		this.mTimeContent = TimeContent;
	}

	public String getTimeContent() {
		return mTimeContent;
	}

	public Work(String WorkContent, String TimeContent) {
		this.mTimeContent = TimeContent;
		this.mWorkContent = WorkContent;
		mIsChecked = false;
	}

	public boolean isChecked() {
		return mIsChecked;
	}
	
	public void setChecked(boolean isChecked) {
		this.mIsChecked = isChecked;
	}

}
