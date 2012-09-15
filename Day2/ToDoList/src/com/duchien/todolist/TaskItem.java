package com.duchien.todolist;

import java.io.Serializable;
import java.util.Date;

public class TaskItem implements Serializable {

	private String nameTask;
	private boolean status;
	private Date date;

	public TaskItem(String nameTask, Boolean status, Date date) {
		super();
		this.nameTask = nameTask;
		this.status = status;
		this.date = date;
	}

	public TaskItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
