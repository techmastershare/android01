/**
 * 
 */
package com.techmaster.todolist;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author duynt4
 * 
 */
public class EditActivity extends EditorActivity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.techmaster.todolist.EditorActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String name = intent.getStringExtra(Constant.NOTE_NAME);
		String content = intent.getStringExtra(Constant.NOTE_CONTENT);
		ui.nameEditText.setText(name);
		ui.contentEditText.setText(content);

		ui.cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(Constant.CANCEL, null);
				finish();
			}
		});

		ui.okButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"MMM\ndd yyyy");
				String date = dateFormat.format(calendar.getTime());
				Intent intent = new Intent();
				intent.putExtra(Constant.NOTE_CONTENT, ui.contentEditText
						.getText().toString());
				intent.putExtra(Constant.NOTE_NAME, ui.nameEditText.getText()
						.toString());
				intent.putExtra(Constant.NOTE_DATE, date);

				setResult(Constant.SAVE, intent);

				finish();
			}
		});
	}

}
