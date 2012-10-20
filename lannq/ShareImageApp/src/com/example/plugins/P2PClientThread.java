package com.example.plugins;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import android.content.Context;
import android.os.Environment;

class P2PClientThread extends P2PThread
{
	private static final String TAG = P2PClientThread.class.toString();
	protected Socket mClientSocket;
	protected Context mContext = null;
	
	private P2PTransferManager mP2PTransferManager = P2PTransferManager.getInstance();
	
	public P2PClientThread(final Socket clientSocket, Context context) {
		this.mClientSocket = clientSocket;
		this.mContext = context;
	}

	public void run() {
		String clientIp = mClientSocket.getInetAddress().toString();
		if (mTransferInfo == null)
			mTransferInfo = new TransferInfo();
		mTransferInfo.setIp(clientIp);
		mTransferInfo.setTransferDirection(Common.INCOMING_TRANSFER);
		
		try {
			InputStream is = mClientSocket.getInputStream();
			mP2PTransferManager.addReceiverThread(this);
			
			byte[] fileInfo = new byte[Common.MAX_FILE_NAME_LENGTH];
			is.read(fileInfo);
			String roundTrip = new String(fileInfo, "UTF-8");
			String fileName = roundTrip.split(":")[0];
			String fileSizeString = roundTrip.split(":")[1];
			int fileSize = Integer.parseInt(getFileSize(fileSizeString));

			/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			// Get current date time with Date()
			Date date = new Date();
			String dateFormatString = dateFormat.format(date);

			String fileName = "data_sharing_" + dateFormatString; */

			// Get external folder path
			File externalStorage = Environment.getExternalStorageDirectory();
			String externalPath = externalStorage.getAbsolutePath();

			String outputDirPath = externalPath + "/DCIM/" + Common.SHARED_FOLDER_NAME;
			File outputDirFile = new File(outputDirPath);
			if (!outputDirFile.exists())
				outputDirFile.mkdirs();

			String fullFilePathName = outputDirPath + "/" + fileName;

			// Create session Id
			String sessionId = Utils.generateSessionId();
			mTransferInfo.setSessionId(sessionId);

			// Accept file content
			byte[] byteArray = new byte[fileSize];
			File file = new File(fullFilePathName);
			int dataSize = 0;
			int totalReceivedBytes = 0; // Get received bytes from other peer
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			while((dataSize = is.read(byteArray, 0, byteArray.length)) > 0) {
				bos.write(byteArray, 0, dataSize);  
				totalReceivedBytes += dataSize;

				/*Log.i(TAG, String.format("Got data size: %d", dataSize));
				Log.i(TAG, String.format("Total received bytes: %d/%d", totalReceivedBytes, fileSize));*/

				if (mCallback != null && Common.SUPPORT_PROGRESS == true) {
					// Emit progress signal
					TransferInfo ti = new TransferInfo(mTransferInfo);
					ti.setIp(clientIp);
					ti.setTransferDirection(Common.INCOMING_TRANSFER);
					ti.setSignalId(Common.PROGRESS_SIGNAL);
					ti.setCurrentSize(totalReceivedBytes);
					ti.setTotalSize(fileSize);
					mCallback.onProgress(ti);
				}
			}

			bos.close();

			// Update file to the system database
			MediaScanRequest msr = new MediaScanRequest(mContext, fullFilePathName);
			msr.scan();
			
			if (mCallback != null) {
				// Emit data received signal
				TransferInfo ti = new TransferInfo(mTransferInfo);
				ti.setIp(clientIp);
				ti.setTransferDirection(Common.INCOMING_TRANSFER);
				ti.setSignalId(Common.COMPLETED_RECEIVING_DATA_SIGNAL);
				ti.setTotalSize(fileSize);
				ti.setFullFilePathName(fullFilePathName);
				mCallback.onSuccess(ti);
			}

			// Close the client socket
			if (mClientSocket != null)
				mClientSocket.close();
		} catch (IOException e){
			if (mCallback != null) {
				// Emit failed signal
				TransferInfo ti = new TransferInfo(mTransferInfo);
				ti.setIp(clientIp);
				ti.setSignalId(Common.UNKNOWN_ERROR_SIGNAL);
				ti.setTransferDirection(Common.INCOMING_TRANSFER);
				ti.setDescription(e.toString());
				mCallback.onFailed(ti);
			}
		} finally {
			mP2PTransferManager.removeReceiverThread(this);
		}
	}

	private String getFileSize(final String str) {
		int strlength = str.length();
		int endpos = 0;
		for(int i = 0; i < strlength; i++) {
			char digit = str.charAt(i);
			if(digit >= '0' && digit <= '9') {
				continue;
			}
			endpos = i;
			break;
		}
		return str.substring(0, endpos);
	}
}
