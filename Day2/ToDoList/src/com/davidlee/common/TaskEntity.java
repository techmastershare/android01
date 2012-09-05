package com.davidlee.common;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskEntity implements Parcelable {

	private String mTitle;
	private String mCreatedDate;
	private String mStatus;

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getCreatedDate() {
		return mCreatedDate;
	}

	public void setCreatedDate(String createdDate) {
		this.mCreatedDate = createdDate;
	}

	public String getStatus() {
		return mStatus;
	}

	public void setStatus(String status) {
		this.mStatus = status;
	}
	
	public TaskEntity()
	{
		this.mTitle = "";
		this.mCreatedDate = "";
		this.mStatus = "";
	}
	public TaskEntity(String title, String createdDate, String status)
	{
		this.mTitle = title;
		this.mCreatedDate = createdDate;
		this.mStatus = status;
	}
	
	/////////////////////////////////////
	public TaskEntity(Parcel in) {
		mTitle = in.readString();
		mCreatedDate = in.readString();
		mStatus = in.readString();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTitle);
		dest.writeString(mCreatedDate);
		dest.writeString(mStatus);
	}
	
	public static final Parcelable.Creator<TaskEntity> CREATOR = new Parcelable.Creator<TaskEntity>() {

		public TaskEntity createFromParcel(Parcel source) {
			return new TaskEntity(source);
		}

		public TaskEntity[] newArray(int size) {
			return new TaskEntity[size];
		}
		
	};

}
