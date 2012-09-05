package com.davidlee.todolist;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.davidlee.common.TaskEntity;

public class Details extends Activity {
	
	private EditText edtTitle;
	private EditText edtCreatedDate;
	private RadioGroup rdbGroup;
	private RadioButton rdbWorking;
	private RadioButton rdbPending;
	private RadioButton rdbCompleted;
	private Button btnCreate;
	private Button btnCancel;
	
	ArrayList<TaskEntity> arrayListTask;
	private String type;
	private int idItemSelected = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtCreatedDate = (EditText) findViewById(R.id.edtCreatedDate);
		rdbGroup = (RadioGroup) findViewById(R.id.radioGroup);
		rdbWorking = (RadioButton) findViewById(R.id.rdbWorking);
		rdbPending = (RadioButton) findViewById(R.id.rdbPending);
		rdbCompleted = (RadioButton) findViewById(R.id.rdbCompleted);
		btnCreate = (Button) findViewById(R.id.btnCreate);
		btnCancel = (Button) findViewById(R.id.btnCancel);
		
		//Set date
		String currentDateString = java.text.DateFormat.getDateInstance().format(new Date());
		edtCreatedDate.setText(currentDateString);
		
		//Get type and value
		Intent intent = getIntent();
		if (intent != null) {
			Bundle extras = intent.getExtras();
			type = intent.getStringExtra("type");
			String id = intent.getStringExtra("value");
			if (id != null)
				idItemSelected = Integer.parseInt(id.toString());
			arrayListTask = new ArrayList<TaskEntity>();
			arrayListTask = extras.getParcelableArrayList("send");
		}
		
		if (type.equals("add")) { //Create
			btnCreate.setText("Create");
		}
		else { //Edit
			btnCreate.setText("Update");
			TaskEntity task = arrayListTask.get(idItemSelected);
			edtTitle.setText(task.getTitle());
			edtCreatedDate.setText(task.getCreatedDate());
			if (rdbWorking.getText().equals(task.getStatus())){
				rdbWorking.setChecked(true);
			}
			else if (rdbPending.getText().equals(task.getStatus())) {
				rdbPending.setChecked(true);
			}
			else 
				rdbCompleted.setChecked(true);
		}
		
		btnCreate.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (v.getId() == R.id.btnCreate) {
					
					int id = rdbGroup.getCheckedRadioButtonId();
					RadioButton rdbChecked = (RadioButton) findViewById(id);
					
					if (type.toString().equals("add")) {
						TaskEntity task = new TaskEntity();
						task.setTitle(edtTitle.getText().toString());
						task.setCreatedDate(edtCreatedDate.getText().toString());
						task.setStatus(rdbChecked.getText().toString());
						if(arrayListTask == null)
							arrayListTask = new ArrayList<TaskEntity>();
						arrayListTask.add(task);
						
						//Hien thong bao
						Toast.makeText(getApplicationContext(), "A new task created", Toast.LENGTH_SHORT).show();							
					}
					else //Update
					{
						arrayListTask.get(idItemSelected).setTitle(edtTitle.getText().toString());
						arrayListTask.get(idItemSelected).setCreatedDate(edtCreatedDate.getText().toString());
						arrayListTask.get(idItemSelected).setStatus(rdbChecked.getText().toString());
						Toast.makeText(getApplicationContext(), "A task updated", Toast.LENGTH_SHORT).show();
					}
					Intent i = new Intent(getApplicationContext(), TodoList.class);
					Bundle b = new Bundle();
					b.putParcelableArrayList("result", arrayListTask);
					i.putExtras(b);
					startActivity(i);
				}//btnCreated
			}
		});
		
		btnCancel.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				if (v.getId() == R.id.btnCancel) {
					edtTitle.setText("");
					edtCreatedDate.setText("");
					rdbCompleted.setChecked(true);
					Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		
	}

}
