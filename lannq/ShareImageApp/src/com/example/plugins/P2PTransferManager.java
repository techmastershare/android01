package com.example.plugins;

import android.content.Context;
import android.util.Log;

public class P2PTransferManager {
	private static final String TAG = P2PTransferManager.class.toString();
	
	private static P2PTransferManager sInstance;
	private P2PServerThread mServerThread = null;
	private P2PCallback mCallback = null;
	private Context mContext = null;

	private String mP2PCallbackId = null; // CallbackId to emit signals from plugin to Javascript
	
	private P2PThreadManager mSenderThreadList = new P2PThreadManager();
	private P2PThreadManager mReceiverThreadList = new P2PThreadManager();
	
	public void setCallback(final P2PCallback callback) {
		this.mCallback = callback;
	}
	
	public void setP2PCallbackId(final String callbackId) {
		mP2PCallbackId = callbackId;
	}
	public String getP2PCallbackId() {
		return mP2PCallbackId;
	}

	private P2PTransferManager() {
	}

	public void setContext(Context context) {
		mContext = context;
	}
	
	public static synchronized P2PTransferManager getInstance() {
		if (sInstance == null) {
			sInstance = new P2PTransferManager();
		}
		return sInstance;
	}
	
	public synchronized boolean addSenderThread(final P2PThread thread) {
		return mSenderThreadList.add(thread);
	}
	public synchronized boolean removeSenderThread(final P2PThread thread) {
		return mSenderThreadList.remove(thread);
	}
	
	public synchronized boolean addReceiverThread(final P2PThread thread) {
		return mReceiverThreadList.add(thread);
	}
	public synchronized boolean removeReceiverThread(final P2PThread thread) {
		return mReceiverThreadList.remove(thread);
	}
	
	public synchronized P2PThread getThread(final TransferInfo ti) {
		if (ti == null)
			return null;
		P2PThreadManager threadManager = null;
		if (ti.getTransferDirection() == Common.INCOMING_TRANSFER) 
			threadManager = mReceiverThreadList;
		else if (ti.getTransferDirection() == Common.OUTGOING_TRANSFER) 
			threadManager = mSenderThreadList;
		
		if (threadManager == null)
			return null;
		
		return threadManager.getThreadBySessionId(ti.getSessionId());
	}
	
	public void startServer() {
		if (mServerThread != null) {
			if (mCallback != null) {
				TransferInfo ti = new TransferInfo();
				ti.setSignalId(Common.SERVER_HAS_BEEN_CREATED_SIGNAL);
				ti.setDescription("Server has been started");
				mCallback.onFailed(ti);
			}
			
			return;
		}
		
		mServerThread = new P2PServerThread(mContext);
		mServerThread.setCallback(new P2PCallback() {
			@Override
			public void onSuccess(TransferInfo ti) {
				if (mCallback != null) {
					mCallback.onSuccess(ti);
				}
			}

			@Override
			public void onFailed(TransferInfo ti) {
				if (mCallback != null) {
					mCallback.onFailed(ti);
				}
			}

			@Override
			public void onProgress(TransferInfo ti) {
				if (mCallback != null) {
					mCallback.onProgress(ti);
				}
			}
		});
		
		// Start server thread
		mServerThread.start();
		
		Log.i(TAG, "Started server successfully");
	}
}
