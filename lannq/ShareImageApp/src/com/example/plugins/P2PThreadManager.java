package com.example.plugins;

import java.util.ArrayList;

public class P2PThreadManager {
	private ArrayList<P2PThread> mP2PThreadList = new ArrayList<P2PThread>();
	
	public boolean add(final P2PThread thread) {
		mP2PThreadList.add(thread);
		return true;
	}
	
	public boolean remove(final P2PThread thread) {
		mP2PThreadList.remove(thread);
		return true;
	}
	
	public P2PThread getThreadBySessionId(final String sessionId) {
		for (int i = 0; i < size(); i++) {
			P2PThread thread = mP2PThreadList.get(i);
			TransferInfo ti = thread.getTransferInfo();
			if (ti == null)
				continue;
			if (ti.getSessionId() == sessionId)
				return thread;
		}
		
		return null;
	}
	
	public int size() {
		return mP2PThreadList.size();
	}
}
