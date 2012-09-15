package com.duchien.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	public class Constant {
		public static final int RESULT_ADD_NEW = 1111;
		public static final int RESULT_UPDATE = 1112;
		public static final String KEY_ADD_NEW = "AddNew";
		public static final String KEY_UPDATE = "Update";
		public static final String COMPLETE = "Complete";
		public static final String PENDING = "Pending";
	}

	// private LayoutInflater mLayoutInflaterContent = null;
	private LinearLayout mLayoutContent = null;

	private LinearItem mSelectItem = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		initUI();
	}

	private void initUI() {

		// mLayoutInflaterContent = LayoutInflater.from(this);
		mLayoutContent = (LinearLayout) findViewById(R.id.content_linearLayout);

		Button btnAdd = (Button) findViewById(R.id.add_button);
		btnAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				int activityID = 1234;
				Intent intent = new Intent().setClass(v.getContext(),
						DetailsItem.class);
				intent.setAction(Constant.KEY_ADD_NEW);
				startActivityForResult(intent, activityID);

			}
		});

		Button btnUpdate = (Button) findViewById(R.id.update_button);
		btnUpdate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (mSelectItem != null) {
					int activityID = 1235;
					Intent intent = new Intent().setClass(v.getContext(),
							DetailsItem.class);
					intent.setAction(Constant.KEY_UPDATE);
					intent.putExtra(Constant.KEY_UPDATE, mSelectItem.getTask());
					startActivityForResult(intent, activityID);
				}
			}
		});

		Button btnDelete = (Button) findViewById(R.id.delete_button);
		btnDelete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (mSelectItem != null) {
					mLayoutContent.removeView(mSelectItem);
				}

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Constant.RESULT_ADD_NEW) {

			TaskItem itemResult = (TaskItem) data.getExtras().getSerializable(
					Constant.KEY_ADD_NEW);

			final LinearItem item = new LinearItem(getApplicationContext(),
					itemResult);
			item.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					if (mSelectItem != null)
						mSelectItem.setBackgroundResource(0);
					mSelectItem = item;
					item.setBackgroundResource(R.drawable.background);
				}
			});

			item.updateItem();
			mLayoutContent.addView(item);

		} else if (resultCode == Constant.RESULT_UPDATE) {

			TaskItem itemResult = (TaskItem) data.getExtras().getSerializable(
					Constant.KEY_UPDATE);
			mSelectItem.updateItem(itemResult);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}
}
