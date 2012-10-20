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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shareimageapp.model.Contact;
import com.example.shareimageapp.model.ContactModel;

public class ContactDAO {
	private SQLiteDatabase mDatabase;

	/**
	 * Contact table related constants.
	 */
	public final static String CONTACT_TABLE = "contacts";
	public final static String CONTACT_ID = "id";
	public final static String CONTACT_NAME = "name";
	public final static String CONTACT_IP = "ip";
	public final static String CONTACT_AVATAR = "avatar";

	public ContactDAO(SQLiteDatabase database) {
		mDatabase = database;
	}

	public long createContact(Contact contact) {
		ContentValues values = new ContentValues();
		values.put(CONTACT_NAME, contact.getName());
		values.put(CONTACT_IP, contact.getIp());
		values.put(CONTACT_AVATAR, contact.getAvatarId());
		return mDatabase.insert(CONTACT_TABLE, null, values);
	}

	/**
	 * Fetch all contacts
	 * 
	 * @return
	 */
	public ContactModel getAllContacts() {
		ContactModel cm = new ContactModel();
		Cursor cursor = mDatabase.query(true, CONTACT_TABLE, new String[] {
				CONTACT_ID, CONTACT_NAME, CONTACT_IP, CONTACT_AVATAR }, null,
				null, null, null, null, null);
		int idIndex = cursor.getColumnIndex(CONTACT_ID);
		int nameIndex = cursor.getColumnIndex(CONTACT_NAME);
		int ipIndex = cursor.getColumnIndex(CONTACT_IP);
		int avatarIndex = cursor.getColumnIndex(CONTACT_AVATAR);
		if ((cursor != null) && (cursor.getCount() > 0)) {
			cursor.moveToFirst();
			while (true) {
				Contact contact = new Contact(cursor.getString(nameIndex),
						cursor.getString(ipIndex), cursor.getInt(avatarIndex),
						cursor.getLong(idIndex));
				cm.add(contact);
				if (!cursor.moveToNext())
					break;
			}
		}
		return cm;
	}

	public int updateContact(Contact contact) {
		ContentValues values = new ContentValues();
		values.put(CONTACT_NAME, contact.getName());
		values.put(CONTACT_IP, contact.getIp());
		values.put(CONTACT_AVATAR, contact.getAvatarId());
		String id = String.format("%d", contact.getContactId());
		return mDatabase.update(CONTACT_TABLE, values, CONTACT_ID + "=?",
				new String[] { id });
	}

	/**
	 * Deletes a contact
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(long contactId) {
		String id = String.format("%d", contactId);
		return mDatabase.delete(CONTACT_TABLE, CONTACT_ID + "=" + id, null) > 0;
	}

	public boolean delete(Contact contact) {
		if (contact == null)
			return false;
		long id = contact.getContactId();
		if (id < 0)
			return false;

		return delete(id);
	}
}
