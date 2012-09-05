package com.davidlee.todolist;

import com.davidlee.model.GalleryAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

public class SlideImage extends Activity {
	
	private Gallery gallery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide_image);
		
		gallery = (Gallery) findViewById(R.id.gallery1);
		gallery.setAdapter(new GalleryAdapter(getApplicationContext()));
		gallery.setSpacing(10);
		gallery.setSelection(3);
		
		gallery.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(getApplicationContext(),"You have selected picture " + (int) (position + 1),Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}
