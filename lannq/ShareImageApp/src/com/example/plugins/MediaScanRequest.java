package com.example.plugins;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

public class MediaScanRequest implements MediaScannerConnection.MediaScannerConnectionClient {
	private MediaScannerConnection mConnection;
	private String mPath;
	private Callback mCallback = null;
	
	public interface Callback {
		public void onMediaScanCompleted();
	}

	// filePath - where to scan; 
	// mime type of media to scan i.e. "image/jpeg". 
	// use "*/*" for any media
	public MediaScanRequest(Context ctx, String filePath) {
		mPath = filePath;
		mConnection = new MediaScannerConnection(ctx, this);
	}

	public void setCallback(Callback callback) {
		mCallback = callback;
	}
	
	// do the scanning
	public void scan() {
		mConnection.connect();
	}

	// start the scan when scanner is ready
	public void onMediaScannerConnected() {
		mConnection.scanFile(mPath, null);
	}

	public void onScanCompleted(String path, Uri uri) {
		// When scan is completes, update media file tags
		mConnection.disconnect();
		mConnection = null;
		// Generate thumbnail if needed
		
		if (mCallback != null)
			mCallback.onMediaScanCompleted();
	}
}
