package com.davidlee.country.views;

import com.davidlee.country.activities.R;
import com.davidlee.country.common.ItemList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ContinentItemView extends RelativeLayout implements View.OnClickListener {
	
	private TextView mNameTextView = null;
	private ItemList mItemList = null;
	private Callback callback = null;
	
	public interface Callback {
		public void onItemClicked(ItemList itemList);
	}
	
	public void setCallback(Callback cb) {
		this.callback = cb;
	}

	public ContinentItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}
	
	public ItemList getItemList() {
		return mItemList;
	}
	
	public void setItemList(ItemList itemList) {
		this.mItemList = itemList;
	}

	protected boolean initialize() {
		initUI();

		return true;
	}

	protected boolean initUI() {
		
		LayoutInflater layoutInlater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInlater.inflate(R.layout.continent_item_view, this);
		
		// Initialize controls
		mNameTextView = (TextView) findViewById(R.id.name);
		this.setOnClickListener(this);
		
		return true;
	}
	
	public void updateView() {
		mNameTextView.setText(mItemList.getName());
	}
	
	

	@Override
	public void onClick(View v) {
		if (callback != null) {
			callback.onItemClicked(mItemList);
		}
	}

}
