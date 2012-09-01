package com.example.todoapp;

import java.io.Serializable;

public class TodoItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DONE = "Done";
	public static final String PENDING = "Pending";
	
	private String mTodoContent;
	private String mDeadline;
	private String mStatus;

	public String getStatus() {
		return mStatus;
	}

	public void setStatus(String status) {
		this.mStatus = status;
	}

	public TodoItem(String todoContent, String deadline, String status) {
		super();
		this.mTodoContent = todoContent;
		this.mDeadline = deadline;
		this.mStatus = status;
	}

	public String getTodoContent() {
		return mTodoContent;
	}

	public void setTodoContent(String mTodoContent) {
		this.mTodoContent = mTodoContent;
	}

	public String getDeadline() {
		return mDeadline;
	}

	public void setDeadline(String mDeadline) {
		this.mDeadline = mDeadline;
	}

}
