package com.davidlee.country.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidlee.country.activities.R;
import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;

public class CountryListAdapter extends BaseAdapter {

	private ItemListModel mItemListModel = null;
	private LayoutInflater mLayoutInflater;
	private Context mContext;

	public CountryListAdapter(Context context) {
		this.mContext = context;
		this.mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ItemListModel getItemListModel() {
		return mItemListModel;
	}

	public void setItemListModel(ItemListModel itemListModel) {
		this.mItemListModel = itemListModel;
	}

	public int getCount() {
		if (mItemListModel == null)
			return 0;
		return mItemListModel.count();
	}

	public Object getItem(int position) {
		if (mItemListModel == null)
			return null;
		return mItemListModel.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHoler;

		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.country_item_view,
					null);
			viewHoler = new ViewHolder();

			viewHoler.itemImage = (ImageView) convertView.findViewById(R.id.poster);
			viewHoler.tvName = (TextView) convertView.findViewById(R.id.name_text);
			viewHoler.tvArea = (TextView) convertView.findViewById(R.id.area_text);
			viewHoler.tvPopulation = (TextView) convertView.findViewById(R.id.population_text);
			viewHoler.tvCapital = (TextView) convertView.findViewById(R.id.capital_text);

			convertView.setTag(viewHoler);
		} else {
			viewHoler = (ViewHolder) convertView.getTag();
		}

		ItemList item = mItemListModel.get(position);
		if (item != null) {
			viewHoler.tvName.setText("" + item.getName());
			viewHoler.tvArea.setText(" " + item.getArea());
			viewHoler.tvPopulation.setText(" " + item.getPopulation());
			viewHoler.tvCapital.setText(" " + item.getCapital());
			viewHoler.itemImage.setImageResource(item.getImageId());
		}
		return convertView;
	}

	static class ViewHolder {
		TextView tvName;
		TextView tvArea;
		TextView tvPopulation;
		TextView tvCapital;
		ImageView itemImage;
	}
}