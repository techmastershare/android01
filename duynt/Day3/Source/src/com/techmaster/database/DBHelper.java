/**
 * 
 */
package com.techmaster.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.techmaster.todolist.NoteItem;

/**
 * @author duynt4
 * 
 */
public class DBHelper extends SQLiteOpenHelper {
	public static final int DB_VERSION = 1;
	public static final String DB_NAME = "techmaster_todo_database";
	public static final String TABLE = "NoteItem";
	public static final String KEY_IMAGE = "image";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_CONTENT = "content";
	public static final String KEY_DATE = "date";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE " + TABLE + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_IMAGE + " INTEGER, " + KEY_NAME
				+ " TEXT," + KEY_CONTENT + " TEXT," + KEY_DATE + " TEXT )";
		db.execSQL(CREATE_TABLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		String UPDATE_TABLE = "DROP TABLE IF EXISTS " + TABLE;
		db.execSQL(UPDATE_TABLE);
	}

	// Query, Insert, Delete collumn
	public void insert(NoteItem noteItem) {
		// open database
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		// insert into database
		contentValues.put(KEY_ID, noteItem.getId());
		contentValues.put(KEY_IMAGE, noteItem.getImageID());
		contentValues.put(KEY_NAME, noteItem.getName());
		contentValues.put(KEY_CONTENT, noteItem.getContent());
		contentValues.put(KEY_DATE, noteItem.getDate());
		database.insert(TABLE, null, contentValues);
		// close database
		database.close();
	}

	public ArrayList<NoteItem> query() {
		ArrayList<NoteItem> noteItems = new ArrayList<NoteItem>();
		String query = "SELECT * FROM " + TABLE;
		// open database for reading
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(query, null);
		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				int image = cursor.getInt(1);
				String name = cursor.getString(2);
				String content = cursor.getString(3);
				String date = cursor.getString(4);
				NoteItem item = new NoteItem(image, name, content, date);
				item.setId(id);
				noteItems.add(item);
			} while (cursor.moveToNext());
		}
		database.close();
		return noteItems;
	}

	public int update(NoteItem item, int image, String newName,
			String newContent) {
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_IMAGE, image);
		contentValues.put(KEY_NAME, newName);
		contentValues.put(KEY_CONTENT, newContent);
		int result = database.update(TABLE, contentValues, KEY_ID + " = ? ",
				new String[] { String.valueOf(item.getId()) });
		database.close();
		return result;
	}

	public void delete(NoteItem noteItem) {
		SQLiteDatabase database = this.getWritableDatabase();
		database.delete(TABLE, KEY_ID + " = ? ",
				new String[] { String.valueOf(noteItem.getId()) });
		database.close();
	}
}
