package com.example.shareimageapp.model;

public class Transaction {
	private String mContact;
	private String mPhotoPath;
	private String mStatus;
	private long mTransactionId;
	private boolean mIsSelectedItem;
	
	public boolean isSelectedItem() {
		return mIsSelectedItem;
	}

	public void setSelectedItem(boolean isSelectedItem) {
		this.mIsSelectedItem = isSelectedItem;
	}

	public Transaction(String contact, String photoPath, String status,
			long transactionId) {
		super();
		this.mContact = contact;
		this.mPhotoPath = photoPath;
		this.mStatus = status;
		this.mTransactionId = transactionId;
	}

	public long getTransactionId() {
		return mTransactionId;
	}

	public void setTransactionId(long transactionId) {
		this.mTransactionId = transactionId;
	}

	public String getContact() {
		return mContact;
	}

	public void setContact(String contactId) {
		this.mContact = contactId;
	}

	public String getPhotoPath() {
		return mPhotoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.mPhotoPath = photoPath;
	}

	public String getStatus() {
		return mStatus;
	}

	public void setStatus(String status) {
		this.mStatus = status;
	}

	public void copy(Transaction transaction) {
		this.mContact = transaction.getContact();
		this.mPhotoPath = transaction.getPhotoPath();
		this.mStatus = transaction.getStatus();
		this.mTransactionId = transaction.getTransactionId();
	}

}
