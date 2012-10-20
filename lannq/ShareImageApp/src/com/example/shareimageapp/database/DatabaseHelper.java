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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private final String TAG = "DatabaseHelper";

	// The Android's default system path of your application database.
	private String mDbPath = "";
	private Context mContext = null;
	private SQLiteDatabase mDatabase = null;

	private static final String DB_NAME = "contact.db";
	private static final String DB_FOLDER = "/databases/";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = String.format(
			"CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, ip TEXT, avatar INTEGER); ",
			ContactDAO.CONTACT_TABLE);
	
	private static final String DATABASE_CREATE2 = String
			.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "photoPath TEXT NOT NULL, contact TEXT NOT NULL, status TEXT NOT NULL); ",
					TransactionDAO.TRANSACTION_TABLE);

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		mContext = context;
		// The Android's default system path of your application database.
		mDbPath = "/data/data/" + mContext.getPackageName() + DB_FOLDER;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public boolean createDataBase() {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			// do nothing - database already exist
		} else {
			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.

			this.getReadableDatabase();

			if (!copyDataBase()) {
				return false;
			}
		}

		openDatabase();
		return true;
	}

	public void openDatabase() {
		// Open the database
		String dbPath = mDbPath + DB_NAME;
		mDatabase = SQLiteDatabase.openDatabase(dbPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {
		boolean isDbExisting = false;

		try {
			String dbPath = mDbPath + DB_NAME;
			File dbFile = new File(dbPath);
			isDbExisting = dbFile.exists();
		} catch (SQLiteException e) {
			// database does't exist yet.
		}

		return isDbExisting;
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private boolean copyDataBase() {
		// Open your local db as the input stream
		InputStream is;
		try {
			String localDbFileName = DB_NAME;
			is = mContext.getAssets().open(localDbFileName);

			// Path to the just created empty db
			String outFileName = mDbPath + DB_NAME;

			// Open the empty db as the output stream
			OutputStream os = new FileOutputStream(outFileName);

			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

			// Close the streams
			os.flush();
			os.close();
			is.close();

			return true;
		} catch (IOException e) {
			Log.i(TAG, e.toString());
		}
		return false;
	}

	@Override
	public synchronized void close() {
		if (mDatabase != null)
			mDatabase.close();

		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(DATABASE_CREATE2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		String sql = String.format("DROP TABLE IF EXISTS %s",
				ContactDAO.CONTACT_TABLE);
		db.execSQL(sql);
		onCreate(db);
	}
}
