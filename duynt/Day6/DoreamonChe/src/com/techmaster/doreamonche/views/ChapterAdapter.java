/*
 * Copyright (C) 2011 Patrik �kerfeldt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.techmaster.doreamonche.views;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleProvider;
import org.taptwo.android.widget.viewflow.R;

import com.techmaster.doreamonche.models.ChapterModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChapterAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater mLayoutInflater = null;
	private ArrayList<ChapterModel> mModelList = null;
	private OnSelectChapter mCallback = null;

	public interface OnSelectChapter {
		public void onSelected(ChapterModel chapterModel);
	}

	public ChapterAdapter(Context context, ArrayList<ChapterModel> list) {
		mLayoutInflater = LayoutInflater.from(context);
		mModelList = list;
	}

	public void setCallback(OnSelectChapter callback) {
		mCallback = callback;
	}

	@Override
	public int getCount() {
		return mModelList.size();
	}

	@Override
	public Object getItem(int position) {
		return mModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final int p = position;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.flow_item, null);
			holder = new ViewHolder();
			holder.descrition = (TextView) convertView
					.findViewById(R.id.text_label);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.descrition.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mCallback != null) {
					mCallback.onSelected(mModelList.get(p));
				}
			}
		});
		holder.descrition.setText(mModelList.get(position)
				.getChapterDescition());

		return convertView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	@Override
	public String getTitle(int position) {
		return "Chapter " + mModelList.get(position).getChapterIndex();
	}

	private class ViewHolder {
		TextView descrition;
	}
}
