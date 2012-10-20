package com.example.plugins;

import android.content.Context;

public class P2PTransferPlugin {
	private static final String TAG = P2PTransferPlugin.class.toString();
	private P2PTransferManager mP2PTransferManager = P2PTransferManager
			.getInstance();

	public void startP2PServer(Context context) {
		// Update database
		/*
		 * Context context = cordova.getActivity().getApplicationContext();
		 * 
		 * BroadcastReceiver mReceiver = new BroadcastReceiver() {
		 * 
		 * @Override public void onReceive(Context context, Intent intent) { if
		 * (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) {
		 * Log.i(TAG, "Scan completed"); } } };
		 * 
		 * IntentFilter filter = new IntentFilter();
		 * filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		 * filter.addDataScheme("file"); context.registerReceiver(mReceiver,
		 * filter);
		 * 
		 * context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
		 * Uri.parse("file://" + Environment.getExternalStorageDirectory())));
		 */

		final String p2pCallbackId = mP2PTransferManager.getP2PCallbackId();

		mP2PTransferManager.setContext(context);
		mP2PTransferManager.startServer();
	}

	public void sendFile(int port, final TransferInfo ti) {
		final String p2pCallbackId = mP2PTransferManager.getP2PCallbackId();
		FileSender fileSender = new FileSender(ti);
		fileSender.sendFile(port, ti);
	}

	protected void cancelP2P(final TransferInfo ti) {
		P2PThread p2pThread = mP2PTransferManager.getThread(ti);

		final String p2pCallbackId = mP2PTransferManager.getP2PCallbackId();
		if (p2pThread == null) {
			TransferInfo retTi = new TransferInfo(ti);
			retTi.setSignalId(Common.SESSION_NOT_EXISTING_SIGNAL);
		} else {
			p2pThread.stop();
		}
	}

	protected String getAllImages(Context context) {

		MediaStoreManager mediaStoreManager = new MediaStoreManager(context);
		MediaInfoList mediaInfoList = mediaStoreManager.getAllImages();
		String jsonString = "";
		if (mediaInfoList != null)
			jsonString = mediaInfoList.toJSONString();

		return jsonString;
	}

	protected String getLocalIpAddress() {
		String ip = Utils.getLocalIpAddress();

		return ip;
	}
}
