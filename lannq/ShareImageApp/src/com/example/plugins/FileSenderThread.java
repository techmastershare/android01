package com.example.plugins;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.util.Log;

class FileSenderThread extends P2PThread
{
	private static final String TAG = FileSenderThread.class.toString();
	private int mDestinationPort = 0;

	private P2PTransferManager mP2PTransferManager = P2PTransferManager.getInstance();
	
	public FileSenderThread(int destinationPort, final TransferInfo ti) {
		this.mDestinationPort = destinationPort;
		this.mTransferInfo = ti;
	}

	public void run() {
		try {
			if (mTransferInfo == null)
				return;
			
			Socket socket = new Socket(mTransferInfo.getIp(), mDestinationPort);
			
			// Create session Id
			String sessionId = Utils.generateSessionId();
			mTransferInfo.setSessionId(sessionId);
			
			// Add the thread to the sender thread manager
			mP2PTransferManager.addSenderThread(this);
			
			String fullFilePathName = mTransferInfo.getFullFilePathName();
			File file = new File(fullFilePathName);
			// Get the size of the file
			long length = file.length();
			int pos = fullFilePathName.lastIndexOf("\\") + 1;
			pos = fullFilePathName.lastIndexOf("/") + 1;
			String fileName = fullFilePathName.substring(pos) + ":" + length;
			Log.i(TAG, "file = " + fileName);
			// Send the header of the file
			socket.getOutputStream().write(fileName.getBytes("UTF-8"), 0, fileName.getBytes("UTF-8").length);
			socket.getOutputStream().flush();
			Thread.sleep(10);

			FileInputStream fos = new FileInputStream(file);   
            OutputStream netOutputStream = socket.getOutputStream();   
            OutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(netOutputStream));   
            byte[] buf = new byte[Common.PACKET_SEND_LENGTH];   
            int num = fos.read(buf);
            int totalSentBytes = num; // Get sent bytes
            while (num != (-1)) {
                dataOutputStream.write(buf, 0, num);
                dataOutputStream.flush();
                num = fos.read(buf);
                /*boolean stopped = false;
                mLocker.lock();
                stopped = mStopped;
                mLocker.unlock();
                
                if (stopped)
                	break;*/
                
                //Log.i(TAG, String.format("==>Total sent bytes: %d/%d", totalSentBytes, length));
                
                if (mCallback != null && Common.SUPPORT_PROGRESS == true) {
					// Emit progress signal
					TransferInfo ti = new TransferInfo(mTransferInfo);
					ti.setIp(mTransferInfo.getIp());
					ti.setTransferDirection(Common.OUTGOING_TRANSFER);
					ti.setSignalId(Common.PROGRESS_SIGNAL);
					ti.setCurrentSize(totalSentBytes);
					ti.setTotalSize((int)length);
					mCallback.onProgress(ti);
				}
				
                totalSentBytes += num;
            }   
            
            fos.close();   
			
            if (mCallback != null) {
				// Emit data received signal
				TransferInfo ti = new TransferInfo(mTransferInfo);
				ti.setIp(mTransferInfo.getIp());
				ti.setTransferDirection(Common.OUTGOING_TRANSFER);
				ti.setSignalId(Common.COMPLETED_SENDING_DATA_SIGNAL);
				ti.setTotalSize((int)length);
				ti.setFullFilePathName(fullFilePathName);
				mCallback.onSuccess(ti);
			}
			
			dataOutputStream.close();   
			socket.close();
		} catch (IOException e) {
			if (mCallback != null) {
				TransferInfo ti = new TransferInfo(mTransferInfo);
				ti.setSignalId(Common.UNKNOWN_ERROR_SIGNAL);
				ti.setDescription(e.toString());
				mCallback.onFailed(ti);
			}
		} catch (InterruptedException e) {
			if (mCallback != null) {
				TransferInfo ti = new TransferInfo(mTransferInfo);
				ti.setSignalId(Common.UNKNOWN_ERROR_SIGNAL);
				ti.setDescription(e.toString());
				mCallback.onFailed(ti);
			}
		} finally {
			// Remove this thread out of the sender thread manager
			mP2PTransferManager.removeSenderThread(this);
		}
	}
}