package com.davidlee.country.adapters;

import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;
import com.davidlee.country.views.ContinentItemView;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ContinentListAdapter extends BaseAdapter {

	private Activity mActivity = null;
	private ItemListModel mItemListModel = null;

	private Callback mCallback = null;

	public interface Callback {
		public void onItemClicked(ItemList itemList);
	}

	public void setCallback(Callback callback) {
		this.mCallback = callback;
	}
	
	public ItemListModel getItemListModel() {
		return mItemListModel;
	}
	
	public void setItemListModel(ItemListModel itemListModel) {
		this.mItemListModel = itemListModel;
	}

	@Override
	public int getCount() {
		if (mItemListModel == null)
			return 0;
		return mItemListModel.count();
	}

	@Override
	public Object getItem(int position) {
		if (mItemListModel == null)
			return null;
		return mItemListModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ContinentItemView view = (ContinentItemView) convertView;
		ItemList item = mItemListModel.get(position);
		
		if (convertView == null) {
			view = new ContinentItemView(this.mActivity, null);
			view.setCallback(new ContinentItemView.Callback() {

				@Override
				public void onItemClicked(ItemList itemList) {
					if (mCallback != null) {
						mCallback.onItemClicked(itemList);
					}
				}
				
			});
		}
		
		view.setItemList(item);
		view.updateView();
		
		return null;
	}
}
