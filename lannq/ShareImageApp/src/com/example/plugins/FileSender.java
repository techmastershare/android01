package com.example.plugins;

public class FileSender {
	private TransferInfo mTransferInfo = null;
	private P2PCallback mCallback = null;

	public FileSender(final TransferInfo ti) {
		mTransferInfo = ti;
	}
	
	public TransferInfo getTransferInfo() {
		return mTransferInfo;
	}
	
	public void setCallback(final P2PCallback callback) {
		this.mCallback = callback;
	}

	public void sendFile(int port, final TransferInfo ti) {
		FileSenderThread fileSenderThread = new FileSenderThread(port, ti);
		fileSenderThread.setCallback(new P2PCallback() {
			@Override
			public void onSuccess(final TransferInfo ti) {
				if (mCallback != null)
					mCallback.onSuccess(ti);
			}
			
			@Override
			public void onFailed(final TransferInfo ti) {
				if (mCallback != null)
					mCallback.onFailed(ti);
			}

			@Override
			public void onProgress(TransferInfo ti) {
				if (mCallback != null)
					mCallback.onProgress(ti);
			}
		});
		
		// Start sender thread
		fileSenderThread.start();
	}
}
