package com.example.todoapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView mLvTodoList = null;
	private Button mAddNewButton = null;
	private List<TodoItem> mTodoList = new ArrayList<TodoItem>();
	private TotoListAdapter mAdapter = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mLvTodoList = (ListView) findViewById(R.id.todo_list);
		mAddNewButton = (Button) findViewById(R.id.addnew_button);

		mTodoList.add(new TodoItem("Di ngu", "24:00", "Done"));
		mTodoList.add(new TodoItem("Di an", "12:14", "Pending"));
		mTodoList.add(new TodoItem("Di uong", "14:15", "Done"));

		mAdapter = new TotoListAdapter(this, mTodoList);
		mLvTodoList.setAdapter(mAdapter);

		mLvTodoList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				int activityID = 1233;
				Intent intent;
				intent = new Intent(view.getContext(), TodoItemActivity.class);
				intent.setAction("Edit");
				intent.putExtra("position", String.valueOf(pos + 1));
				TodoItem todoItem = mTodoList.get(pos);
				intent.putExtra("todoItem", todoItem);
				startActivityForResult(intent, activityID);
			}

		});

		mAddNewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int activityID = 1234;
				Intent intent;
				intent = new Intent().setClass(v.getContext(),
						TodoItemActivity.class);
				intent.setAction("Create New");
				startActivityForResult(intent, activityID);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			TodoItem todoItem = (TodoItem) extras.getSerializable("todoItem");
			String position = extras.getString("position");

			switch (requestCode) {
			case 1233: {
				int pos = Integer.parseInt(position);
				if (todoItem != null) {
					mTodoList.set(pos - 1, todoItem);
				} else {
					mTodoList.remove(pos - 1);
				}
				break;
			}
			case 1234: {
				mTodoList.add(todoItem);
				break;
			}
			}

		}
	}

	@Override
	protected void onResume() {
		mLvTodoList.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		super.onResume();
	}
}
