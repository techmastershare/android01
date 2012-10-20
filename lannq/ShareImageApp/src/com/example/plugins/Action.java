package com.example.plugins;

public class Action {
	public static final int UNKNOWN = -1;
	public static final int READ_ONLY = 0;
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;
	
	private int mAction = UNKNOWN;
	
	public Action() {
	}
	
	public Action(int action) {
		mAction = action;
	}
	
	public void set(int action) {
		mAction = action;
	}
	
	public int get() {
		return mAction;
	}
}
