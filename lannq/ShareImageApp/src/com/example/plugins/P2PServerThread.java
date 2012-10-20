package com.example.plugins;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;

public class P2PServerThread extends P2PThread {
	public P2PServerThread(Context context) {
		super(context);
	}

	private static final String TAG = P2PServerThread.class.toString();
	
	private ServerSocket mServerSocket = null;
	
	public void run() {
		try {
			mServerSocket = new ServerSocket(Common.SERVER_LISTEN_PORT);
			
			// Notify created socket successfully 
			if (mCallback != null) {
				// Emit data received signal
				TransferInfo ti = new TransferInfo();
				ti.setSignalId(Common.SUCCESSFUL_CREATED_SERVER_SIGNAL);
				mCallback.onSuccess(ti);
			}
			
			while(true) {
	            Socket clientSocket = mServerSocket.accept();
	            if (clientSocket != null) {
	                P2PClientThread clientConnectionThread = new P2PClientThread(clientSocket, mContext);
	                clientConnectionThread.setCallback(new P2PCallback() {
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
	                
	                // Start the client connection thread
	                clientConnectionThread.start();
	            }
	            
	            try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// Emit failed signal
					TransferInfo ti = new TransferInfo();
					ti.setDescription(e.toString());
					mCallback.onFailed(ti);
				}
	        }
		} catch (IOException e) {
			// Emit failed signal
			TransferInfo ti = new TransferInfo();
			ti.setDescription(e.toString());
			mCallback.onFailed(ti);
		}
	}
}
