package com.techmaster.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.techmaster.database.DBHelper;
import com.techmaster.slideimage.AvatarActivity;
import com.techmaster.todolist.TodoListAdapter.Callback;

public class TodoListActivity extends Activity {
	// Menu bar
	private class FunctionBar {
		private ImageButton addButton;
		private ImageButton selectButton;
		private ImageButton deleteButton;
		private ImageButton editButton;
		private TextView countNote;

		public FunctionBar() {
			addButton = (ImageButton) findViewById(R.id.add_button);
			selectButton = (ImageButton) findViewById(R.id.select_button);
			deleteButton = (ImageButton) findViewById(R.id.delete_button);
			editButton = (ImageButton) findViewById(R.id.edit_button);
			countNote = (TextView) findViewById(R.id.notification_text_view);
		}
	}

	private class NotificationUpdateThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				if (isUpdate) {
					isUpdate = false;
					Message message = new Message();
					message.what = countOfNotification;
					handler.sendMessage(message);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private class NotificationUpdateHandler extends Handler {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int i = msg.what;
			functionBar.countNote.setText(" + " + i);
		}

	}

	private FunctionBar functionBar = null;
	private int countOfNotification = 0;
	private NotificationUpdateHandler handler = null;
	private NotificationUpdateThread thread = null;
	private boolean isUpdate = true;
	private ArrayList<NoteItem> noteItems = null;
	private ListView listView = null;
	private boolean isSelectedMode = false;
	// mapping data and selected view
	private HashMap<NoteItem, View> selectedHashMap = null;
	// contains data of selected view by selected order
	private ArrayList<NoteItem> selectedList = null;
	private NoteItem editNote = null;
	// database
	private DBHelper dbHelper = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		dbHelper = new DBHelper(this);
		functionBar = new FunctionBar();
		handler = new NotificationUpdateHandler();
		thread = new NotificationUpdateThread();
		selectedHashMap = new HashMap<NoteItem, View>();
		thread.start();
		initUI();
		// add click listener
		functionBar.addButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TodoListActivity.this, AddActivity.class);
				startActivityForResult(intent, Constant.REQUEST_ADD);
			}
		});
		// select click listener
		functionBar.selectButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isSelectedMode) {
					isSelectedMode = true;
					functionBar.selectButton
							.setImageResource(R.drawable.selected);
				} else {
					isSelectedMode = false;
					functionBar.selectButton
							.setImageResource(R.drawable.select);
				}
			}
		});
		// delete click listener
		functionBar.deleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ArrayList<NoteItem> deleteList = new ArrayList<NoteItem>();

				functionBar.selectButton.setImageResource(R.drawable.select);
				isSelectedMode = false;
				for (int i = 0; i < noteItems.size();) {
					NoteItem item = noteItems.get(i);
					if (item.isSelected()) {
						isUpdate = true;
						--countOfNotification;
						item.setSelected(false);
						deleteList.add(item); // add to deleted list for update
												// database
						noteItems.remove(item);
						View view = selectedHashMap.remove(item);
						view.setBackgroundResource(R.drawable.normal_note);
					} else {
						++i;
					}
				}
				BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
				// update database
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (!deleteList.isEmpty()) {
							NoteItem item = deleteList.remove(0);
							dbHelper.delete(item);
						}
					}
				});
				thread.start();
			}
		});
		// edit click listener
		functionBar.editButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				functionBar.selectButton.setImageResource(R.drawable.select);
				isSelectedMode = false;
				if (!selectedList.isEmpty()) {
					int lastIndex = selectedList.size() - 1;
					editNote = selectedList.get(lastIndex);
					// reset other view state
					selectedList.clear();
					Iterator<Entry<NoteItem, View>> it = selectedHashMap
							.entrySet().iterator();
					while (it.hasNext()) {
						Entry<NoteItem, View> entry = it.next();
						entry.getKey().setSelected(false);
						entry.getValue().setBackgroundResource(
								R.drawable.normal_note);
					}
					selectedHashMap.clear();
					// start edit activity
					String name = editNote.getName();
					String content = editNote.getContent();
					Intent intent = new Intent();
					intent.setClass(TodoListActivity.this, EditActivity.class);
					intent.putExtra(Constant.NOTE_NAME, name);
					intent.putExtra(Constant.NOTE_CONTENT, content);
					TodoListActivity.this.startActivityForResult(intent,
							Constant.REQUEST_EDIT);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_todo_list, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == Constant.REQUEST_ADD) {
			if (resultCode == Constant.SAVE) {
				// add new note
				String name = data.getStringExtra(Constant.NOTE_NAME);
				String content = data.getStringExtra(Constant.NOTE_CONTENT);
				String date = data.getStringExtra(Constant.NOTE_DATE);
				final NoteItem item = new NoteItem(R.drawable.unknown, name,
						content, date);
				noteItems.add(item);
				BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
				isUpdate = true;
				++countOfNotification;
				// save into database
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbHelper.insert(item);
					}
				});
				thread.start();
			}
		} else if (requestCode == Constant.REQUEST_EDIT) {
			if (resultCode == Constant.SAVE) {
				// edit note
				final String name = data.getStringExtra(Constant.NOTE_NAME);
				final String content = data
						.getStringExtra(Constant.NOTE_CONTENT);
				editNote.setName(name);
				editNote.setContent(content);
				editNote.setDescribe();
				BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
				// save into database
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbHelper.update(editNote, editNote.getImageID(), name,
								content);
						editNote = null;
					}
				});
				thread.start();
			}
		} else if (requestCode == Constant.REQUEST_CHANGE_AVATAR) {
			if (resultCode == Constant.SAVE) {
				// change avatar
				int image = data.getIntExtra(Constant.NOTE_AVATAR, -1);
				if (image < 0) {
					image = editNote.getImageID();
				}
				final int i = image;
				editNote.setImageID(image);
				BaseAdapter adapter = (BaseAdapter) listView.getAdapter();
				adapter.notifyDataSetChanged();
				// update database
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbHelper.update(editNote, i, editNote.getName(),
								editNote.getContent());
						editNote = null;
					}
				});
				thread.start();
			}
		}
	}

	public void initUI() {
		noteItems = dbHelper.query();
		countOfNotification = noteItems.size();
		isUpdate = true;
		selectedList = new ArrayList<NoteItem>();
		listView = (ListView) findViewById(R.id.list_view);
		TodoListAdapter adapter = new TodoListAdapter(this, noteItems);
		adapter.setCallback(new Callback() {

			@Override
			public void onTextClicked(NoteItem item, View tempView) {
				// TODO Auto-generated method stub
				if (item.isSelected()) {
					// if view being selected then un-select view
					item.setSelected(false);
					tempView.setBackgroundResource(R.drawable.normal_note);
					if (selectedHashMap.containsKey(item)) {
						selectedHashMap.remove(item);
					}
					// remove from selectedList
					selectedList.remove(item);
				} else {
					if (!isSelectedMode) {
						for (NoteItem noteItem : noteItems) {
							if (selectedHashMap.containsKey(noteItem)) {
								View view = selectedHashMap.remove(noteItem);
								view.setBackgroundResource(R.drawable.normal_note);
								noteItem.setSelected(false);
								// remove from selectedList
								selectedList.remove(item);
							}
						}
						item.setSelected(true);
						tempView.setBackgroundResource(R.drawable.select_note);
						selectedHashMap.put(item, tempView);
						// add to selectedList
						selectedList.add(item);
					} else {
						item.setSelected(true);
						tempView.setBackgroundResource(R.drawable.select_note);
						selectedHashMap.put(item, tempView);
						selectedList.add(item);
					}
				}
			}

			@Override
			public void onImageClicked(NoteItem item) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TodoListActivity.this, AvatarActivity.class);
				TodoListActivity.this.startActivityForResult(intent,
						Constant.REQUEST_CHANGE_AVATAR);
				editNote = item;
			}
		});
		listView.setAdapter(adapter);

	}
}
