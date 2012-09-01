package com.davidlee.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button btnAdd;
	private Button btnEdit;
	private Button btnDelete;
	private ListView lvTodo;
	private AlertDialog.Builder builder;
	private AlertDialog alertDialog;
	private ArrayList<TaskEntity> lstTask;
	private int idItemSelected; 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        lvTodo = (ListView) findViewById(R.id.lstToDo);
        
        //Get lstTask to Details
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (i != null && extras != null) {
	        
	        //boolean result = extras.getBoolean("type");
	        lstTask = new ArrayList<TaskEntity>();
	        lstTask = extras.getParcelableArrayList("result");
	        lvTodo.setAdapter(new ItemListAdapter(getApplicationContext(), lstTask));
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (v.getId() == R.id.btnAdd) {
					Intent intent = new Intent(getApplicationContext(), Details.class);
					intent.putExtra("type", "add");
					Bundle b = new Bundle();
					b.putParcelableArrayList("send", lstTask);
					intent.putExtras(b);
					startActivity(intent);
				}
			}
		});
        
        btnEdit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (v.getId() == R.id.btnEdit) {
					Intent intent = new Intent(getApplicationContext(), Details.class);
					intent.putExtra("type", "edit");
					intent.putExtra("value", "");
					Bundle b = new Bundle();
					b.putParcelableArrayList("send", lstTask);
					intent.putExtras(b);
					startActivity(intent);
				}
			}
		});
        
        //Set Properties AlertDialog
        builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete?");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				//Delete item list array
				lstTask.remove(idItemSelected);
				lvTodo.setAdapter(new ItemListAdapter(getApplicationContext(), lstTask));
			}
		});
		
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
        
        btnDelete.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (v.getId() == R.id.btnDelete) {
					alertDialog = builder.show();
				}
			}
		});
        
        //list view selected item
        lvTodo.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> adapter, View v,
					int postion, long id) {
				idItemSelected = lvTodo.getSelectedItemPosition();
				Toast.makeText(getApplicationContext(), "You are choose item " + (int)(idItemSelected+1), Toast.LENGTH_SHORT).show();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
