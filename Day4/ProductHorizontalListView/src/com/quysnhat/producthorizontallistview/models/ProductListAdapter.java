package com.quysnhat.producthorizontallistview.models;


import com.quysnhat.producthorizontallistview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ProductListAdapter extends BaseAdapter {
	
	private ProductModel productModel = null;
	private Context context = null;
	private LayoutInflater inflater;

	public ProductListAdapter(Context _context, ProductModel _productModel) {
		context = _context;
		productModel = _productModel;
		inflater = LayoutInflater.from(_context);
	}

	public int getCount() {
		return productModel.count();
	}

	public Object getItem(int position) {
		return productModel.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null ) {
			convertView = inflater.inflate(R.layout.product_item_view, null);

			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.avatar_image_view);
			holder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);
			holder.priceTextView = (TextView) convertView.findViewById(R.id.price_text_view);
			holder.statusTextView = (TextView) convertView.findViewById(R.id.category_text_view);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		Product product = productModel.get(position);
		holder.imageView.setImageResource(product.getImageId());
		holder.nameTextView.setText(product.getName());
		holder.priceTextView.setText(product.getPriceString());
		holder.statusTextView.setText(product.getCategoryString());
		
		return convertView;
	}
	
	static class ViewHolder{
		ImageView imageView;
		TextView nameTextView;
		TextView descriptionTextView;
		TextView priceTextView;
		TextView statusTextView;
	}

}
