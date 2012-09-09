package com.davidlee.scrollview.models;

import com.davidlee.scrollview.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter {

	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private CategoryModel mCategoryModel = null;
	
	public CategoryListAdapter(final Context context, final CategoryModel categoryModel) {
		this.mContext = context;
		mCategoryModel = categoryModel;
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	////////////////////////////////////
	public int getCount() {
		return mCategoryModel.count();
	}

	public Object getItem(int position) {
		return mCategoryModel.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.category_list_row, null);
			holder = new ViewHolder();
			holder.mName = (TextView) convertView.findViewById(R.id.name);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		CategoryEntity category = mCategoryModel.get(position);
		holder.mName.setText(category.getName());
		
		return convertView;
	}
	
	static class ViewHolder {
		TextView mName;
	}
}
