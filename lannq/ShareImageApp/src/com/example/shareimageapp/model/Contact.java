package com.example.shareimageapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
	private String mName;
	private String mIp;
	private int mAvatarId = -1;
	private long mContactId;
	private boolean mIsItemSelected = false;
	
	

	public boolean isItemSelected() {
		return mIsItemSelected;
	}

	public void setItemSelected(boolean isItemSelected) {
		this.mIsItemSelected = isItemSelected;
	}

	public Contact(String name, String ip, int avatarId, long contactId) {
		super();
		this.mName = name;
		this.mIp = ip;
		this.mAvatarId = avatarId;
		this.mContactId = contactId;
	}

	public long getContactId() {
		return mContactId;
	}

	public void setContactId(long contactId) {
		this.mContactId = contactId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getIp() {
		return mIp;
	}

	public void setIp(String ip) {
		this.mIp = ip;
	}

	public int getAvatarId() {
		return mAvatarId;
	}

	public void setAvatarId(int avatarId) {
		this.mAvatarId = avatarId;
	}
	
	public Contact(Parcel in) {
		readFromParcel(in);
	}
	
	public void copy(Contact other) {
		this.mAvatarId = other.mAvatarId;
		this.mContactId = other.mContactId;
		this.mIp = other.mIp;
		this.mName = other.mName;
	}
	

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(mContactId);
		dest.writeString(mName);
		dest.writeString(mIp);
		dest.writeInt(mAvatarId);
		
	}
	
	private void readFromParcel(Parcel in) {

		// We just need to read back each
		// field in the order that it was
		// written to the parcel
		mContactId = in.readLong();
		mName = in.readString();
		mIp = in.readString();
		mAvatarId = in.readInt();
	}
	
	public static final Creator<Contact> CREATOR = new Creator<Contact>() {
		public Contact createFromParcel(Parcel in) {
			return new Contact(in);
		}

		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};

}
