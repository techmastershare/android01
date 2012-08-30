package com.techmaster.todolist;

/**
 * this class create an instance note item
 * 
 * @author duynt4
 * 
 */
public class NoteItem {
	// name of note item
	private String name;
	// content of note item
	private String content;
	// date created of note item
	private String date;
	// icon of note item
	private int imageID;

	public NoteItem(String name, String content, String date, int imageID) {
		this.name = name;
		this.content = content;
		this.date = date;
		this.imageID = imageID;
	}

	/**
	 * @return the name
	 */
	public String getName() {

		if (name == null || name.length() == 0) {
			this.name = "No name";
		}
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the imageID
	 */
	public int getImageID() {
		return imageID;
	}

	/**
	 * @param imageID
	 *            the imageID to set
	 */
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
}
