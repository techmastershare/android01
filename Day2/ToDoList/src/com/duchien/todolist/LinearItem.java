package com.duchien.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.duchien.todolist.MainActivity.Constant;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearItem extends LinearLayout {

	private TaskItem mTask = null;
	private TextView mNameTask = null;
	private TextView mStatus = null;
	private TextView mDate = null;

	public LinearItem(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public LinearItem(Context context) {
		super(context);

	}

	public LinearItem(Context context, TaskItem task) {
		super(context);
		this.mTask = task;

		initUI();
	}

	private void initUI() {
		LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = layoutInflater.inflate(R.layout.item_view, this);

		mNameTask = (TextView) itemView.findViewById(R.id.name_task_text_view);
		mStatus = (TextView) itemView.findViewById(R.id.status_text_view);
		mDate = (TextView) itemView.findViewById(R.id.date_task_text_view);

	}

	protected void updateItem() {
		mNameTask.setText(mTask.getNameTask());

		if (mTask.getStatus())
			mStatus.setText(Constant.COMPLETE);
		else
			mStatus.setText(Constant.PENDING);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		mDate.setText(sdf.format(mTask.getDate()));
	}
	protected void updateItem(TaskItem task) {
		mNameTask.setText(task.getNameTask());

		if (task.getStatus())
			mStatus.setText(Constant.COMPLETE);
		else
			mStatus.setText(Constant.PENDING);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		mDate.setText(sdf.format(task.getDate()));
	}

	public TaskItem getTask() {
		mTask.setNameTask(mNameTask.getText().toString());

		if (mStatus.getText().toString().equals(Constant.COMPLETE))
			mTask.setStatus(true);
		else
			mTask.setStatus(false);

		String dateString = mDate.getText().toString();
		String[] date = dateString.split("-");
		mTask.setDate(new Date(Integer.parseInt(date[2]), Integer
				.parseInt(date[1]), Integer.parseInt(date[0])));

		return mTask;
	}

	public void setTask(TaskItem mTask) {
		this.mTask = mTask;
	}

	
}
