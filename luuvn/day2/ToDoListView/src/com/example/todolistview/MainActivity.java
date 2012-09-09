package com.example.todolistview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private static final int DELETE_WORK = Menu.FIRST;
	private static final int ABOUT = Menu.FIRST + 2;
	
	ArrayList<Work> mListWork;
	MyCustomBaseAdapter mCustomAdapter;
	private EditText mWorkEnter;
	private EditText mHourEdit;
	private EditText mMinuteEdit;
	private Button mButton;
	private ListView mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		mListWork = new ArrayList<Work>();
		mCustomAdapter = new MyCustomBaseAdapter(getApplicationContext(),mListWork);

		mWorkEnter = (EditText) findViewById(R.id.work_enter);
		mHourEdit = (EditText) findViewById(R.id.hour_edit);
		mMinuteEdit = (EditText) findViewById(R.id.minute_edit);
		mButton = (Button) findViewById(R.id.button_add_work);
		mList = (ListView) findViewById(R.id.list);
		mList.setAdapter(mCustomAdapter);

		mButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (mWorkEnter.getText().toString().equals("")
						|| mHourEdit.getText().toString().equals("")
						|| mMinuteEdit.getText().toString().equals("")) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							MainActivity.this);
					builder.setTitle("Info missing");
					builder.setMessage("Please enter all information of the work");
					builder.setPositiveButton("Continue",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							});
					builder.show();
				} else {
					String workContent = mWorkEnter.getText().toString();
					String timeContent = mHourEdit.getText().toString() + ":"
							+ mMinuteEdit.getText().toString();
					Work work = new Work(workContent, timeContent);
					mListWork.add(0,work);
					mCustomAdapter.notifyDataSetChanged();
					mWorkEnter.setText("");
					mHourEdit.setText("");
					mMinuteEdit.setText("");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, DELETE_WORK, 0, "Delete").setIcon(
				android.R.drawable.ic_delete);
		menu.add(0, ABOUT, 0, "About").setIcon(
				android.R.drawable.ic_menu_info_details);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_WORK: {
			deleteCheckedWork();
			break;
		}
		case ABOUT: {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("To Do List View");
			builder.setMessage("Luuvn");
			builder.setPositiveButton("Close",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			builder.setIcon(android.R.drawable.ic_dialog_info);
			builder.show();
			break;
		}
		}
		return true;
	}

	private void deleteCheckedWork() {
		if (mListWork.size() > 0) {
			for (int i = 0; i < mListWork.size(); i++) {
				if (i > mListWork.size()) {
					break;
				}
				if (mListWork.get(i).isChecked()) {
					mListWork.remove(i);
					mCustomAdapter.notifyDataSetChanged();
					continue;
				}
			}
		}
	}

}
