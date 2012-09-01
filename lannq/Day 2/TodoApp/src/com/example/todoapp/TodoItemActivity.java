package com.example.todoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class TodoItemActivity extends Activity {

	private EditText mTodoEditText = null;
	private TimePicker mDeadlineTP = null;
	private RadioGroup mStatusRadioGroup = null;
	private TextView mTitleTextView = null;
	private TextView mTimeTextView = null;
	private Button mDoneButton = null;
	private Button mCloseButton = null;
	private Button mGetTimeButton = null;
	private String mPosition = null;
	private String mDelete = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todoitem);

		Bundle extras = getIntent().getExtras();
		String action = getIntent().getAction();

		mTodoEditText = (EditText) findViewById(R.id.todo_editText);
		mTitleTextView = (TextView) findViewById(R.id.title_textview);
		mDeadlineTP = (TimePicker) findViewById(R.id.deadline_timePicker);
		mDoneButton = (Button) findViewById(R.id.ok_button);
		mCloseButton = (Button) findViewById(R.id.close_button);
		mGetTimeButton = (Button) findViewById(R.id.getTime_button);
		mTimeTextView = (TextView) findViewById(R.id.timeDeadline_textView);
		mStatusRadioGroup = (RadioGroup) findViewById(R.id.status_radioGroup);
		mDelete = getString(R.string.delete_button);

		if (action != "Create New") {

			mPosition = extras.getString("position");
			TodoItem todoItem = (TodoItem) extras.getSerializable("todoItem");
			mCloseButton.setText(mDelete);

			mTodoEditText.setText(todoItem.getTodoContent());
			mTitleTextView.setText("Todo " + mPosition);
			mTimeTextView.setText(todoItem.getDeadline());
			String status = todoItem.getStatus();
			if (status.equalsIgnoreCase(TodoItem.DONE)) {
				mStatusRadioGroup.check(R.id.done_radioButton);
			} else if (status.equalsIgnoreCase(TodoItem.PENDING)) {
				mStatusRadioGroup.check(R.id.pending_radioButton);
			}
		}

		mCloseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();

				Bundle extras = new Bundle();
				if (mCloseButton.getText().toString().equalsIgnoreCase(mDelete)) {
					extras.putString("position", mPosition);
					i.putExtras(extras);
					setResult(RESULT_OK, i);
				} else {
					setResult(RESULT_CANCELED, i);
				}

				finish();// Finish current activity
			}
		});

		mDoneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();

				Bundle extras = new Bundle();
				String todoContent = mTodoEditText.getText().toString();
				String deadline = mTimeTextView.getText().toString();
				int checkedRadioId;
				checkedRadioId = mStatusRadioGroup.getCheckedRadioButtonId();
				RadioButton checkedButton = (RadioButton) findViewById(checkedRadioId);
				String status = checkedButton.getText().toString();

				TodoItem todoItem = new TodoItem(todoContent, deadline, status);

				extras.putString("position", mPosition);
				i.putExtras(extras);
				i.putExtra("todoItem", todoItem);
				setResult(RESULT_OK, i);
				finish();// Finish current activity
			}
		});

		mGetTimeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String hour = mDeadlineTP.getCurrentHour().toString();
				String minute = mDeadlineTP.getCurrentMinute().toString();
				mTimeTextView.setText(hour + ":" + minute);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
