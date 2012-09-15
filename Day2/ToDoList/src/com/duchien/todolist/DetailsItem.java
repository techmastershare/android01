package com.duchien.todolist;

import java.util.Calendar;
import java.util.Date;

import com.duchien.todolist.MainActivity.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.RadioButton;

public class DetailsItem extends Activity {

	private EditText mNameTask = null;
	private RadioButton mComplete = null;
	private RadioButton mPending = null;
	private DatePicker mDate = null;
	private Button mButton = null;
	private Intent mIntent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		initUI();
		mIntent = getIntent();
		if (mIntent.getAction().equals(Constant.KEY_UPDATE)) {
			TaskItem task = (TaskItem) mIntent.getExtras().getSerializable(
					Constant.KEY_UPDATE);
			setUI(task);
		}
	}

	public void initUI() {
		setContentView(R.layout.detailts_view);

		mNameTask = (EditText) findViewById(R.id.name_task_edit_text);
		mComplete = (RadioButton) findViewById(R.id.complete_radio_button);
		mComplete.setChecked(true);
		mPending = (RadioButton) findViewById(R.id.pending_radio_button);
		mDate = (DatePicker) findViewById(R.id.date_picker);
		Calendar c = Calendar.getInstance();
		mDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), null);

		mButton = (Button) findViewById(R.id.done_button);

		mButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(Constant.KEY_ADD_NEW, getTaskItem());
				setResult(Constant.RESULT_ADD_NEW, intent);
				finish();
			}
		});
	}

	public void setUI(TaskItem task) {

		mNameTask.setText(task.getNameTask());
		if (task.getStatus()) {
			mComplete.setChecked(true);
		} else {
			mPending.setChecked(true);
		}
		Date date = task.getDate();
		mDate.init(date.getYear(), date.getMonth(), date.getDay(), null);

		Button btnDone = (Button) findViewById(R.id.done_button);

		btnDone.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra(Constant.KEY_UPDATE, getTaskItem());
				setResult(Constant.RESULT_UPDATE, intent);
				finish();
			}
		});
	}

	private TaskItem getTaskItem() {

		boolean status = true;
		if (!mComplete.isChecked()) {
			status = false;
		}

		Date date = new Date(mDate.getYear() - 1900, mDate.getMonth(),
				mDate.getDayOfMonth());

		return new TaskItem(mNameTask.getText().toString(), status, date);
	}

}
