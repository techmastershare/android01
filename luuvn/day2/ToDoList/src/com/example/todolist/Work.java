package com.example.todolist;

public class Work {
	private String workContent;
	private String timeContent;
	private boolean isChecked;

	public Work(String workContent, String timeContent) {
		this.workContent = workContent;
		this.timeContent = timeContent;
		isChecked = false;
	}

	public String getContent() {
		return workContent;
	}

	public String getTime() {
		return timeContent;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isChecked() {
		return isChecked;
	}

}
