package com.techmaster.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NoteInfalter {
	private RelativeLayout itemLayout;
	private LinearLayout.LayoutParams layoutParams;
	private NoteItem noteItem;
	private ImageView iconImageView;
	private TextView nameTextView;
	private TextView contentTextView;
	private TextView dateTextView;
	private boolean isSelected = false;

	/**
	 * initialize layout parameters
	 */
	public NoteInfalter() {
		int width = LinearLayout.LayoutParams.FILL_PARENT;
		int height = LinearLayout.LayoutParams.FILL_PARENT;
		layoutParams = new LinearLayout.LayoutParams(width, height);
		layoutParams.setMargins(0, 0, 0, 10);
	}

	/**
	 * Add new item to layout
	 * 
	 * @param context
	 * @param parent
	 */
	public void add(NoteItem noteItem, Context context,
			CustomLinearLayout parent) {
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		itemLayout = (RelativeLayout) layoutInflater.inflate(R.layout.note,
				null);
		this.noteItem = noteItem;
		parent.addView(itemLayout, layoutParams, this);
		// get children views
		iconImageView = (ImageView) itemLayout.getChildAt(0);
		dateTextView = (TextView) itemLayout.getChildAt(1);
		nameTextView = (TextView) itemLayout.getChildAt(2);
		contentTextView = (TextView) itemLayout.getChildAt(3);
		// set data
		setContent(noteItem.getContent());
		setIconImage(noteItem.getImageID());
		setName(noteItem.getName());
		setDate(noteItem.getDate());
	}

	/**
	 * set image icon for note icon is supported by program
	 * 
	 * @param id
	 *            : identifier of image in drawable
	 */
	public void setIconImage(int id) {
		iconImageView.setImageResource(id);
	}

	/**
	 * set name of note item
	 * 
	 * @param name
	 */
	public void setName(String name) {
		nameTextView.setText(name);
	}

	/**
	 * set and get content of note item
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		String describle = null;
		if (content.length() <= 20) {
			describle = content;
		} else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(content.subSequence(0, 20));
			buffer.append(".......");
			describle = buffer.toString();
		}
		contentTextView.setText(describle);
	}

	/**
	 * set and get date string
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		dateTextView.setText(date);
	}

	/**
	 * get note item
	 * 
	 * @return
	 */
	public NoteItem getNoteItem() {
		return this.noteItem;
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
		if (isSelected) {
			itemLayout.setBackgroundResource(R.drawable.select_note);
		} else {
			itemLayout.setBackgroundResource(R.drawable.normal_note);
		}
	}

	public ImageView getIcon() {
		return (ImageView) itemLayout.getChildAt(0);
	}

	public TextView getName() {
		return (TextView) itemLayout.getChildAt(2);
	}

	public TextView getDescrible() {
		return (TextView) itemLayout.getChildAt(3);
	}

	public TextView getDate() {
		return (TextView) itemLayout.getChildAt(1);
	}

	public RelativeLayout getMainLayout() {
		return itemLayout;
	}
}
