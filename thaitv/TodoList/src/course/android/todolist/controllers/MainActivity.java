package course.android.todolist.controllers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import course.android.todolist.models.MyNoteAdapter;
import course.android.todolist.models.Note;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final int ACTIVITY_ID = 0;
	private ListView mNoteListView;
	private Button mAddButton;
	private List<Note> listNote;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        mNoteListView = (ListView) findViewById(R.id.noteListView);
        mNoteListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Note note = (Note) mNoteListView.getItemAtPosition(position);
				
				Intent intent = new Intent(view.getContext(),DetailActivity.class);
				intent.putExtra("noteSelected", note);
				intent.putExtra("id", position);
				startActivityForResult(intent, ACTIVITY_ID);
			}
        	
		});
        mAddButton = (Button) findViewById(R.id.addButton);
        mAddButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(v.getContext(),DetailActivity.class);
				Bundle bundle = new Bundle();
				
				startActivityForResult(intent, ACTIVITY_ID);
			}
		});
        
        
        
        listNote = new ArrayList<Note>();
        listNote.add(new Note("Note 1", "success", "2012-09-01", "Nap bai tap"));
        listNote.add(new Note("Note 2", "unsuccess", "2012-09-02", "Đi chơi với bạn gái nhân dịp cuối tuần"));
        
        MyNoteAdapter noteAdapter = new MyNoteAdapter(this,listNote);
        mNoteListView.setAdapter(noteAdapter);
    }
    


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	switch (requestCode) {
		case ACTIVITY_ID:
			if(resultCode == RESULT_OK){
				Bundle extras = data.getExtras();
				Note note = (Note)extras.getSerializable("noteObj");
				int pos = extras.getInt("id");
				if(pos == -1){
					listNote.add(note);
					
				}else{
					listNote.remove(pos);
					listNote.add(pos, note);
				}
				
				MyNoteAdapter noteAdapter = new MyNoteAdapter(this,listNote);
		        mNoteListView.setAdapter(noteAdapter);
			}
			break;
		default:
			break;
		}
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_view, menu);
        return true;
    }
}
