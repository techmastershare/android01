/**
 * 
 */
package com.techmaster.todolist;

/**
 * @author duynt4
 * 
 */
public class NoteItem {
	private static int ID = 1;
	// id
	private int id;
	// id of image
	private int imageID;
	// name of note
	private String name;
	// content of note
	private String content;
	// description about content
	private String descirbe;
	// date create of edit note
	private String date;
	// is selected
	private boolean isSelected = false;

	public NoteItem(int imageID, String name, String content, String date) {
		this.id = ++ID;
		this.setImageID(imageID);
		this.setName(name);
		this.setContent(content);
		this.setDate(date);
		this.setDescribe();
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public void setName(String name) {
		if (name == null || name.equals("")) {
			this.name = "No name";
		} else {
			this.name = name;
		}
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDescribe() {
		if (this.content.length() < 10) {
			this.descirbe = new String(this.content);
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(this.content.substring(0, 9)).append(" ...");
			this.descirbe = buffer.toString();
		}
	}

	/**
	 * @return the descirbe
	 */
	public String getDescirbe() {
		return descirbe;
	}

	/**
	 * @param descirbe
	 *            the descirbe to set
	 */
	public void setDescirbe(String descirbe) {
		this.descirbe = descirbe;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 *            the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
}
