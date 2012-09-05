package course.android.todolist.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import course.android.todolist.models.MyNoteAdapter;
import course.android.todolist.models.Note;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private Button mSaveButton;
	private Button mCancelButton;
	
	private EditText mTitleEditText;
	private EditText mDetailEditText;
	private RadioButton mSuccessRadioButton;
	private RadioButton mUnSuccessRadioButton;
	private DatePicker mDeadlineDatePicket;
	
	private int mPos;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        
        mTitleEditText = (EditText) findViewById(R.id.note_title_edit_text);
        mDetailEditText = (EditText) findViewById(R.id.note_detail_edit_text);
        mSuccessRadioButton = (RadioButton) findViewById(R.id.success_radio_button);
        mUnSuccessRadioButton = (RadioButton) findViewById(R.id.unsuccess_radio_button);
        //mUnSuccessRadioButton.setChecked(true);
        mDeadlineDatePicket = (DatePicker) findViewById(R.id.deadline_picker);
        
        mSaveButton = (Button) findViewById(R.id.saveButton);
        mPos = -1;
        try{
        	Bundle extras = getIntent().getExtras();
        	Note note = (Note)extras.getSerializable("noteSelected");
        	
        	mPos = extras.getInt("id");
        	
        	mTitleEditText.setText(note.getTitle());
        	mDetailEditText.setText(note.getDetail());
        	SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd"); 
        	Date dateObj = curFormater.parse(note.getDeadLine().toString()); 
        	int year = dateObj.getYear();
        	int monthOfYear = dateObj.getMonth();
        	int dayOfMonth = dateObj.getDate();
        	mDeadlineDatePicket.init(year, monthOfYear, dayOfMonth, null);
        	if(note.getStatusCode().equalsIgnoreCase(Note.SUCCESS)){
        		mSuccessRadioButton.setChecked(true);
        		mUnSuccessRadioButton.setChecked(false);
        	}
        }catch(Exception e){
        	
        }
        mSaveButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Note note = generateNote();
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				//bundle.putSerializable("noteObj", note);
				intent.putExtra("noteObj", note);
				intent.putExtra("id", mPos);
                setResult(RESULT_OK, intent);
                finish();
			}
		});
        
        mCancelButton = (Button) findViewById(R.id.cancelButton);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = getIntent();
				setResult(RESULT_CANCELED,intent);
				finish();
			}
		});
    }
	
	private Note generateNote(){
		String status = Note.UNSUCCESS;
		if(mSuccessRadioButton.isChecked()){
			status = Note.SUCCESS;
		}
		int year = mDeadlineDatePicket.getYear();
		int month = mDeadlineDatePicket.getMonth();
		int day = mDeadlineDatePicket.getDayOfMonth();
		Date date = (Date) new Date(year, month, day);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		Note note = new Note(mTitleEditText.getText().toString(), status, dateString, mDetailEditText.getText().toString());
		return note;
	}
}
