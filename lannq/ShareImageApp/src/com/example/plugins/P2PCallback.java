package com.example.plugins;

public interface P2PCallback {
	public void onSuccess(final TransferInfo ti);
	public void onFailed(final TransferInfo ti);
	public void onProgress(final TransferInfo ti);
}
