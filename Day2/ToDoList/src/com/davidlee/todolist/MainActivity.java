package com.davidlee.todolist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	static final String[] homework = new String[] {"Todo List", "Slide Image View"};
	private ListView listHomework;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.main, homework));
		listHomework = getListView();
		listHomework.setTextFilterEnabled(false);
				
		listHomework.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Intent i = null;
				switch (position) {
				case 0:
					i = new Intent(getApplicationContext(), TodoList.class);
					startActivity(i);
					break;
					
				case 1:
					i = new Intent(getApplicationContext(), SlideImage.class);
					startActivity(i);
					break;

				default:
					break;
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
