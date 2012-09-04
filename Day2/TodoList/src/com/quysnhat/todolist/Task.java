package com.quysnhat.todolist;


public class Task {
	private String taskName;
	private String createTime;
	private String status;
	private boolean isChecked;
	
	public Task(String taskName, String createTime, String status){
		this.taskName = taskName;
		this.createTime = createTime;
		this.status = status;
		this.isChecked = false;
	}
	
	public String getTaskName(){
		return this.taskName;
	}
	
	public String getCreateTime(){
		return this.createTime;
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setChecked(boolean isChecked){
		this.isChecked = isChecked;
	}
	
	public boolean isChecked(){
		return isChecked;
	}

}
