package com.quysnhat.todolist;

import java.util.ArrayList;
import java.util.Date;

import com.quysnhat.todolist.TodoListAdapter.Callback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class TodoListActivity extends Activity {
	
	ArrayList<Task> array;
	TodoListAdapter arrayAdapter;
	ArrayList<Task> tasks = new ArrayList<Task>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        array = new ArrayList<Task>();
        
        arrayAdapter = new TodoListAdapter(this, tasks);
        
        final EditText taskNameEditText = (EditText) findViewById(R.id.task_name_editText); 
        final EditText deadlineEditText = (EditText) findViewById(R.id.deadline_editText); 
        
        final String currentDateString = java.text.DateFormat.getDateInstance().format(new Date());
        deadlineEditText.setText(currentDateString);
        
        final RadioButton workingRadioButton = (RadioButton) findViewById(R.id.working_radio_button);
        workingRadioButton.setChecked(true);
        final RadioButton pendingRadioButton = (RadioButton) findViewById(R.id.pending_radio_button);
        final RadioButton completedRadioButton = (RadioButton) findViewById(R.id.completed_radio_button);
        		         
        final Button addButton = (Button) findViewById(R.id.add_button);
        final Button deleteButton = (Button) findViewById(R.id.delete_button);
        
        final ListView lv = (ListView) findViewById(R.id.srlistView);
        
        
        OnClickListener add = new OnClickListener() {
			
			public void onClick(View v) {
				if(taskNameEditText.getText().toString().equals("")){
					AlertDialog.Builder builder = new AlertDialog.Builder(TodoListActivity.this);
					builder.setTitle("Info missing");
					builder.setMessage("Please enter all information of the work!");
					
					builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					
					builder.show();
				}
				else{
					String taskName = taskNameEditText.getText().toString();
					String createDate = currentDateString;
					String deadlineDate = deadlineEditText.getText().toString();
					
					String createTime = createDate + " - " +  deadlineDate;
					
					String status = "Working";
					if(workingRadioButton.isChecked())
						status = "Working";
					else if(pendingRadioButton.isChecked())
						status = "Pending";
					else if(completedRadioButton.isChecked())
						status = "Completed";
					
					Task task = new Task();
					task.setTaskName(taskName);
					task.setCreateTime(createTime);
					task.setStatus(status);
					
					array.add(0,task);
					tasks.add(task);
					
					arrayAdapter.notifyDataSetChanged();
					taskNameEditText.setText("");
					workingRadioButton.setChecked(true);
					taskNameEditText.requestFocus();
				}
				
			}
		};
		
		addButton.setOnClickListener(add);
		
		
		OnClickListener delete = new OnClickListener() {
			
			public void onClick(View v) {
				if(array.size() > 0){
					for(int i = 0; i < array.size(); i++){
						if(i > array.size()){
							break;
						}
						if(array.get(i).isChecked()){
							Toast.makeText(TodoListActivity.this, "You have chosen: " + " " + array.get(i).getTaskName(), Toast.LENGTH_LONG).show();
							
							array.remove(i);
							((TodoListAdapter) arrayAdapter).notifyDataSetChanged();
							
							continue;
						}
					}
				}	
			}
		};
		
		deleteButton.setOnClickListener(delete);
		
		
		arrayAdapter.setCallback(new Callback() {
			public void OnTaskNameClick(Task task) {
				String taskName = task.getTaskName();
    			int duration = taskName.length();
    			Toast toast = Toast.makeText(getApplicationContext(), taskName, duration);
    			toast.show();
				
			}
		});
		
		lv.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
