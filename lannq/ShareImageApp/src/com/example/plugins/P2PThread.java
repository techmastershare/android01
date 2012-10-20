package com.example.plugins;

import android.content.Context;

public class P2PThread extends Thread {
	protected TransferInfo mTransferInfo = null;
	protected Context mContext = null;
	
	//private boolean mStopped = false;
	//private Lock mLocker = new ReentrantLock();
	
	public P2PThread() {
	}
	
	public P2PThread(Context context) {
		mContext = context;
	}
	
	protected P2PCallback mCallback = null;

	public void setCallback(final P2PCallback callback) {
		this.mCallback = callback;
	}

	public void setTransferInfo(final TransferInfo ti) {
		mTransferInfo = ti;
	}
	public TransferInfo getTransferInfo() {
		return mTransferInfo;
	}
}
