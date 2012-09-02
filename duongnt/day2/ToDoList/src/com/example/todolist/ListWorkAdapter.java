package com.example.todolist;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ListWorkAdapter extends ArrayAdapter<Work> {
	ArrayList<Work> mArray;
	int mResource;
	Context mContext;

	public ListWorkAdapter(Context mContext, int textViewResourceId, ArrayList<Work> objects) {
		super(mContext, textViewResourceId, objects);
		this.mContext = mContext;
		mResource = textViewResourceId;
		mArray = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View workView = convertView;

		if (workView == null) {
			workView = new CustomViewGroup(getContext());
		}

		final Work work = mArray.get(position);

		if (work != null) {
			TextView mWorkContent = ((CustomViewGroup) workView).mWorkContent;
			TextView mTimeContent = ((CustomViewGroup) workView).mTimeContent;
			CheckBox checkWork = ((CustomViewGroup) workView).mCb;

			checkWork.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					work.setChecked(isChecked);
				}
			});

			mWorkContent.setText(work.getContent());
			mTimeContent.setText(work.getTime());
			checkWork.setChecked(work.isChecked());
		}
		return workView;
	}

}
