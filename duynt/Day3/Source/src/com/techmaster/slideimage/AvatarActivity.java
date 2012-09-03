package com.techmaster.slideimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.techmaster.todolist.Constant;
import com.techmaster.todolist.R;

public class AvatarActivity extends Activity {
	private Integer[] pictures = { R.drawable.birthday, R.drawable.die,
			R.drawable.email, R.drawable.familly, R.drawable.friend,
			R.drawable.healthy, R.drawable.idea, R.drawable.learning,
			R.drawable.love, R.drawable.meeting, R.drawable.money,
			R.drawable.train, R.drawable.unknown };

	private class UI {
		Gallery gallery = null;
		ImageView imageView = null;
		ImageButton cancelButton = null;
		ImageButton saveButton = null;

		public UI() {
			gallery = (Gallery) findViewById(R.id.gallery);
			imageView = (ImageView) findViewById(R.id.scale_image_view);
			cancelButton = (ImageButton) findViewById(R.id.cancel_avatar_button);
			saveButton = (ImageButton) findViewById(R.id.save_avatar_button);
		}
	}

	private UI uiAvatar = null;
	private int selectedImage = -1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avata_layout);
		uiAvatar = new UI();
		initUI();
	}

	public void initUI() {
		uiAvatar.imageView.setImageResource(pictures[0]);
		AvatarAdapter adapter = new AvatarAdapter(this, pictures);
		uiAvatar.gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				uiAvatar.imageView.setImageResource(pictures[arg2]);
				selectedImage = pictures[arg2];
			}

		});
		uiAvatar.gallery.setAdapter(adapter);
		uiAvatar.cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(Constant.CANCEL);
				finish();
			}
		});
		uiAvatar.saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra(Constant.NOTE_AVATAR, selectedImage);
				setResult(Constant.SAVE, intent);
				finish();
			}
		});
	}
}
