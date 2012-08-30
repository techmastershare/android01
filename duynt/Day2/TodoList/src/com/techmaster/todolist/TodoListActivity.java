package com.techmaster.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TodoListActivity extends Activity {
	/**
	 * 
	 * @author duynt4
	 * 
	 *         This class contain main menu button of program
	 */
	private class TitleBar {
		public ImageButton addImageButton;
		public ImageButton selecteImageButton;
		public ImageButton deleteImageButton;
		public ImageButton editImageButton;
		public TextView countNoteTextview;
		public boolean isUpdate = true;

		public TitleBar() {
			// TODO Auto-generated constructor stub
			addImageButton = (ImageButton) findViewById(R.id.function_add_button);
			selecteImageButton = (ImageButton) findViewById(R.id.function_select_button);
			deleteImageButton = (ImageButton) findViewById(R.id.function_delete_button);
			editImageButton = (ImageButton) findViewById(R.id.function_edit_button);
			countNoteTextview = (TextView) findViewById(R.id.count_text_view);
		}
	}

	/**
	 * this class update number of note status run every 2000 millisecond
	 * 
	 * @author duynt4
	 * 
	 */
	private class CountThread extends Thread {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				if (titleBar.isUpdate) {
					titleBar.isUpdate = false;
					Message message = new Message();
					message.what = numOfNote;
					handler.sendMessage(message);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	private class CountHandler extends Handler {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int count = msg.what;
			titleBar.countNoteTextview.setText("+ " + count);
		}

	}

	private int numOfNote = 0;
	private CountHandler handler = null;
	private CountThread thread = null;
	private CustomLinearLayout linearLayout = null;
	private TitleBar titleBar = null;
	// in multi-selecting mode
	private boolean isSelecting = false;
	private ArrayList<NoteInfalter> selectedChildrens = new ArrayList<NoteInfalter>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		titleBar = new TitleBar();
		linearLayout = (CustomLinearLayout) findViewById(R.id.view_layout);
		handler = new CountHandler();
		thread = new CountThread();
		thread.start();
		// click add button
		titleBar.addImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TodoListActivity.this, AddActivity.class);
				startActivityForResult(intent, Constant.REQUEST_ADD);
				Log.d("LOG", "KO hieu");
			}
		});
		// click edit button
		titleBar.editImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				selectedChildrens.clear();
				ArrayList<NoteInfalter> noteInfalters = linearLayout
						.getSeletedChildrens();
				if (!noteInfalters.isEmpty()) {
					int size = noteInfalters.size();
					// get last selected item
					NoteInfalter noteInfalter = noteInfalters.get(size - 1);
					// add to selected item --> editing item
					noteInfalter.setSelected(false);
					selectedChildrens.add(noteInfalter);
					NoteItem noteItem = noteInfalter.getNoteItem();

					Intent intent = new Intent();
					intent.setClass(TodoListActivity.this, EditActivity.class);
					intent.putExtra(Constant.NOTE_CONTENT,
							noteItem.getContent());
					intent.putExtra(Constant.NOTE_NAME, noteItem.getName());
					intent.putExtra(Constant.NOTE_DATE, noteItem.getDate());
					startActivityForResult(intent, Constant.REQUEST_EDIT);
				}
			}
		});
		// click select button
		titleBar.selecteImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				isSelecting = !isSelecting;
				if (isSelecting) {
					titleBar.selecteImageButton
							.setImageResource(R.drawable.selected);
				} else {
					titleBar.selecteImageButton
							.setImageResource(R.drawable.select);

				}
				linearLayout.setSelected(isSelecting);
				if (!selectedChildrens.isEmpty()) {
					ArrayList<NoteInfalter> noteInfalters = linearLayout
							.getSeletedChildrens();
					if (!noteInfalters.isEmpty()) {
						for (int i = 0, n = noteInfalters.size(); i < n; ++i) {
							noteInfalters.get(i).setSelected(false);
						}
						noteInfalters.clear();
					}
					selectedChildrens.clear();
				}
			}
		});

		// click delete button
		titleBar.deleteImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ArrayList<NoteInfalter> noteInfalters = linearLayout
						.getSeletedChildrens();
				if (!noteInfalters.isEmpty()) {
					for (int i = 0, n = noteInfalters.size(); i < n; ++i) {
						--numOfNote;
						linearLayout.getChildren().remove(noteInfalters.get(i));
						linearLayout.removeView(noteInfalters.get(i));
					}
					noteInfalters.clear();
					titleBar.isUpdate = true;
					isSelecting = false;
					titleBar.selecteImageButton
							.setImageResource(R.drawable.select);
					selectedChildrens.clear();
				}
			}
		});
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
		if (requestCode == Constant.REQUEST_ADD && resultCode == Constant.SAVE) {
			String content = data.getStringExtra(Constant.NOTE_CONTENT);
			String name = data.getStringExtra(Constant.NOTE_NAME);
			String date = data.getStringExtra(Constant.NOTE_DATE);
			titleBar.isUpdate = true;
			++numOfNote;
			NoteItem noteItem = new NoteItem(name, content, date,
					R.drawable.unknown);
			NoteInfalter infalter = new NoteInfalter();
			infalter.add(noteItem, this, linearLayout);
			// reset selecting mode
			isSelecting = false;
			titleBar.selecteImageButton.setImageResource(R.drawable.select);
			selectedChildrens.clear();
			ArrayList<NoteInfalter> noteInfalters = linearLayout
					.getSeletedChildrens();
			for (int i = 0, n = noteInfalters.size(); i < n; ++i) {
				noteInfalters.get(i).setSelected(false);
			}
			noteInfalters.clear();
		} else if (requestCode == Constant.REQUEST_EDIT
				&& resultCode == Constant.SAVE) {
			String name = data.getStringExtra(Constant.NOTE_NAME);
			String content = data.getStringExtra(Constant.NOTE_CONTENT);
			NoteInfalter noteInfalter = selectedChildrens.get(0);
			noteInfalter.setContent(content);
			noteInfalter.setName(name);
			selectedChildrens.clear();
			ArrayList<NoteInfalter> noteInfalters = linearLayout
					.getSeletedChildrens();
			for (int i = 0, n = noteInfalters.size(); i < n; ++i) {
				noteInfalters.get(i).setSelected(false);
			}
			noteInfalters.clear();
		} else {
			selectedChildrens.clear();
			ArrayList<NoteInfalter> noteInfalters = linearLayout
					.getSeletedChildrens();
			for (int i = 0, n = noteInfalters.size(); i < n; ++i) {
				noteInfalters.get(i).setSelected(false);
			}
			noteInfalters.clear();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_todo_list, menu);
		return true;
	}

	public void init() {

	}

	public void update() {

	}
}
