package com.quysnhat.todolist;

import java.util.ArrayList;
import java.util.Date;

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

public class TodoListActivity extends Activity {
    
    ArrayList<Task> array; 
    ListTaskApdapter arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        array = new ArrayList<Task>();
        arrayAdapter = new ListTaskApdapter(this, R.layout.list, array);
        
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
        		        
        final ListView list = (ListView) findViewById(R.id.list); 
        list.setAdapter(arrayAdapter);
        
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
				else {
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
					
					Task work = new Task(taskName, createTime, status);
					
					array.add(0,work);
					
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
							array.remove(i);
							arrayAdapter.notifyDataSetChanged();
							continue;
						}
					}
				}	
			}
		};
		
		deleteButton.setOnClickListener(delete);
    }

   // @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
