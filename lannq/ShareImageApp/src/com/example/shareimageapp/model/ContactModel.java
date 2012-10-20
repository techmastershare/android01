package com.example.shareimageapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactModel {
	private ArrayList<Contact> mContactList = new ArrayList<Contact>();

	public void add(final Contact contact) {
		mContactList.add(contact);
	}

	public Contact get(final int index) {
		if (index < 0)
			return null;
		return mContactList.get(index);
	}
	
	public Contact getById(long contactId) {
		for (int i = 0; i < count(); i++) {
			Contact contact = get(i);
			if (contact != null && contact.getContactId() == contactId) {
				return contact;
			}
		}
		return null;
	}

	protected boolean remove(Contact contact) {
		return mContactList.remove(contact);
	}

	public boolean remove(long contactId) {
		for (int i = 0; i < count(); i++) {
			Contact contact = get(i);
			if (contact != null && contact.getContactId() == contactId) {
				remove(contact);
				return true;
			}
		}

		return false;
	}
	
	public boolean update(Contact newContact) {
		long contactId = newContact.getContactId();
		for (int i = 0; i < count(); i++) {
			Contact contact = get(i);
			if (contact != null && contact.getContactId() == contactId) {
				contact.copy(newContact);
				return true;
			}
		}

		return false;
	}

	public int count() {
		return mContactList.size();
	}

	public void clear() {
		mContactList.clear();
	}

	public void sortByName() {
		Collections.sort(mContactList, new Comparator<Contact>() {
			public int compare(Contact o1, Contact o2) {
				String name1 = o1.getName();
				String name2 = o2.getName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
}
