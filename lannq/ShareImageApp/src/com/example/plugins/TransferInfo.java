package com.example.plugins;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TransferInfo {
	private static final String TAG = "TransferInfo";
	
	private String mSessionId = ""; // Session Id of a transfer
	private int mTransferDirection = Common.UNKNOWN_TRANSFER;
	private int mSignalId = Common.UNKNOWN_SIGNAL; // Signal Id
	private String mIp = ""; // Ip of other peer to send or receive data	
	private String mFullFilePathName = ""; // Sent or received file name
	private int mCurrentSize = 0; // Current sent/received data size
	private int mTotalSize = 0; // Total sent/received data size
	private String mDescription = ""; // Error string if an error occurs
	
	private static final String SESSION_ID = "sessionId";
	private static final String TRANSFER_DIRECTION = "transferDirection";
	private static final String SIGNAL_ID = "signalId";
	private static final String IP = "ip";
	private static final String FULL_FILE_PATH_NAME = "fullFilePathName";
	private static final String CURRENT_SIZE = "currentSize";
	private static final String TOTAL_SIZE = "totalSize";
	private static final String DESCRIPTION = "description";
	
	public TransferInfo() {
	}
	
	public TransferInfo(final TransferInfo other) {
		if (other == null)
			return;
		
		mSessionId = other.getSessionId();
		mTransferDirection = other.getTransferDirection();
		mSignalId = other.getSignalId();
		mIp = other.getIp();
		mFullFilePathName = other.getFullFilePathName();
		mCurrentSize = other.getCurrentSize();
		mTotalSize = other.getTotalSize();
		mDescription = other.getDescription();
	}
	
	public TransferInfo(final String fromJSONString) {
		JSONObject transferInfo;
		try {
			transferInfo = new JSONObject(fromJSONString);
			parseDataFromJSON(transferInfo);
		} catch (JSONException e) {
			Log.i(TAG, e.toString());
		}
	}
	
	public TransferInfo(final JSONObject transferInfo) {
		parseDataFromJSON(transferInfo);
	}

	protected void parseDataFromJSON(final JSONObject transferInfo) {
		if (transferInfo == null)
			return;
		
		try {
			mSessionId = transferInfo.has(SESSION_ID) ? transferInfo.getString(SESSION_ID) : "";
			mTransferDirection = transferInfo.has(TRANSFER_DIRECTION) ? transferInfo.getInt(TRANSFER_DIRECTION) : Common.UNKNOWN_TRANSFER;
			mSignalId = transferInfo.has(SIGNAL_ID) ? transferInfo.getInt(SIGNAL_ID) : Common.UNKNOWN_SIGNAL;
			mIp = transferInfo.has(IP) ? transferInfo.getString(IP) : "";
			mFullFilePathName = transferInfo.has(FULL_FILE_PATH_NAME) ? transferInfo.getString(FULL_FILE_PATH_NAME) : "";
			mCurrentSize = transferInfo.has(CURRENT_SIZE) ? transferInfo.getInt(CURRENT_SIZE) : 0;
			mTotalSize = transferInfo.has(TOTAL_SIZE) ? transferInfo.getInt(TOTAL_SIZE) : 0;
			mDescription = transferInfo.has(DESCRIPTION) ? transferInfo.getString(DESCRIPTION) : "";
		} catch (JSONException e) {
			Log.i(TAG, e.toString());
		}
	}
	
	public TransferInfo(final String sessionId, int transferDirection, int signalId, final String ip, 
			final String callbackId, final String fullFilePathName,
			int currentSize, int totalSize, final String description) {
		mSessionId = sessionId;
		mTransferDirection = transferDirection;
		mSignalId = signalId;
		mIp = ip;
		mFullFilePathName = fullFilePathName;
		mCurrentSize = currentSize;
		mTotalSize = totalSize;
		mDescription = description;
	}
	
	public void setSessionId(final String sessionId) {
		mSessionId = sessionId;
	}
	public String getSessionId() {
		return mSessionId;
	}
	
	public void setTransferDirection(int transferDirection) {
		mTransferDirection = transferDirection;
	}
	public int getTransferDirection() {
		return mTransferDirection;
	}
	
	public void setSignalId(int signalId) {
		mSignalId = signalId;
	}
	public int getSignalId() {
		return mSignalId;
	}
	
	public void setIp(final String ip) {
		mIp = ip;
	}
	public String getIp() {
		return mIp;
	}
	
	public void setFullFilePathName(final String fullFilePathName) {
		mFullFilePathName = fullFilePathName;
	}
	public String getFullFilePathName() {
		return mFullFilePathName;
	}
	
	public void setCurrentSize(int currentSize) {
		mCurrentSize = currentSize;
	}
	public int getCurrentSize() {
		return mCurrentSize;
	}
	
	public void setTotalSize(int totalSize) {
		mTotalSize = totalSize;
	}
	public int getTotalSize() {
		return mTotalSize;
	}
	
	public void setDescription(final String description) {
		mDescription = description;
	}
	public String getDescription() {
		return mDescription;
	}
	
	public JSONObject toJSONObject() {
		JSONObject data = new JSONObject();
		try {
			data.put(SESSION_ID, mSessionId);
			data.put(TRANSFER_DIRECTION, mTransferDirection);
			data.put(SIGNAL_ID, mSignalId);
			data.put(IP, mIp);
			data.put(FULL_FILE_PATH_NAME, mFullFilePathName);
			data.put(CURRENT_SIZE, mCurrentSize);
			data.put(TOTAL_SIZE, mTotalSize);
			data.put(DESCRIPTION, mDescription);
			return data;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toJSONString() {
		String str = "";
		JSONObject data = this.toJSONObject();
		if (data != null)
			str = data.toString();
		
		return str;
	}
}
