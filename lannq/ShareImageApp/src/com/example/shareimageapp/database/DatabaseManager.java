/**
 * @author: Nguyen Truong Duong (raycad)
 * 
 * @email: seedotech@gmail.com
 * 
 * Copyright of Seedotech Ltd (http://www.seedotech.com). All rights reserved.
 *
 * Any other legal text to be defined later
 */
package com.example.shareimageapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
	private static DatabaseManager sSingleton = new DatabaseManager(); 
	
	private SQLiteDatabase mDatabase = null;
	private DatabaseHelper mDatabaseHelper = null;
	
	private DatabaseManager() {};
	
	public static DatabaseManager getInstance() {
		return sSingleton;
	}
	
	public void open(Context context) {
		mDatabaseHelper = new DatabaseHelper(context);
		
		mDatabase = mDatabaseHelper.getWritableDatabase();
	}
	
	public void close() {
		if (mDatabaseHelper != null) {
			mDatabaseHelper.close();
		}
	}
	public SQLiteDatabase getDatabase() {
		return mDatabase;
	}
}
