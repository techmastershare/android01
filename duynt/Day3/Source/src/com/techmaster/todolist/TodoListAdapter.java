package com.techmaster.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TodoListAdapter extends BaseAdapter {
	/**
	 * this interface define callback methods for adapter
	 * 
	 * @author duynt4
	 * 
	 */
	interface Callback {
		// call when user click on image
		public void onImageClicked(NoteItem item);

		// call when user click on text of note
		public void onTextClicked(final NoteItem item, View view);
	}

	// data
	private ArrayList<NoteItem> noteItems = null;
	private Context context;
	private LayoutInflater layoutInflater = null;
	private Callback callback = null;

	/**
	 * constructor
	 * 
	 * @param context
	 * @param noteItems
	 */
	public TodoListAdapter(Context context, ArrayList<NoteItem> noteItems) {
		this.context = context;
		this.noteItems = noteItems;
		this.layoutInflater = LayoutInflater.from(this.context);
	}

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	public Callback getCallback() {
		return this.callback;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return noteItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return noteItems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return noteItems.get(arg0).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final NoteItem item = (NoteItem) getItem(position);
		// re-use layout
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.avatar = (ImageView) convertView
					.findViewById(R.id.avatar);
			viewHolder.desribe = (TextView) convertView
					.findViewById(R.id.describle_text_view);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.name_text_view);
			viewHolder.date = (TextView) convertView
					.findViewById(R.id.date_text_view);
			// set tag object
			convertView.setTag(viewHolder);
			// set callback for item

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final View tempView = convertView;
		viewHolder.desribe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callback != null) {
					callback.onTextClicked(item, tempView);
				}
			}
		});
		viewHolder.name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callback != null) {
					callback.onTextClicked(item, tempView);
				}
			}
		});
		viewHolder.avatar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callback != null) {
					callback.onImageClicked(item);
				}
			}
		});
		// set display for view
		viewHolder.avatar.setImageResource(item.getImageID());
		viewHolder.date.setText(item.getDate());
		viewHolder.name.setText(item.getName());
		viewHolder.desribe.setText(item.getDescirbe());
		// return view
		return convertView;
	}

	private static class ViewHolder {
		private ImageView avatar;
		private TextView name;
		private TextView desribe;
		private TextView date;
	}

	/**
	 * @return the noteItems
	 */
	public ArrayList<NoteItem> getNoteItems() {
		return noteItems;
	}

	/**
	 * @param noteItems
	 *            the noteItems to set
	 */
	public void setNoteItems(ArrayList<NoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * @return the context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * @return the layoutInflater
	 */
	public LayoutInflater getLayoutInflater() {
		return layoutInflater;
	}

	/**
	 * @param layoutInflater
	 *            the layoutInflater to set
	 */
	public void setLayoutInflater(LayoutInflater layoutInflater) {
		this.layoutInflater = layoutInflater;
	}
}
