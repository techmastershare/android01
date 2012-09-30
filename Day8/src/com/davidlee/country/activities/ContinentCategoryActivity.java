package com.davidlee.country.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.davidlee.country.adapters.ContinentListAdapter;
import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;

public class ContinentCategoryActivity extends Activity {

	private ListView mContinentListView = null;
	private ItemListModel mItemListModel = null;
	private ContinentListAdapter mContinentListAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.continent_category_layout);

		initUI();
	}

	private void initUI() {
		mContinentListView = (ListView) findViewById(R.id.continent_list_view);

		mItemListModel = new ItemListModel();
		mItemListModel.add(new ItemList("Châu Mỹ", "", "", -1));
		mItemListModel.add(new ItemList("Châu Phi", "", "", -1));
		mItemListModel.add(new ItemList("Châu Âu", "", "", -1));
		mItemListModel.add(new ItemList("Châu Á", "", "", -1));
		mItemListModel.add(new ItemList("Châu Đại Dương", "", "", -1));

		mContinentListAdapter = new ContinentListAdapter();
		mContinentListAdapter.setItemListModel(mItemListModel);

		mContinentListView.setAdapter(mContinentListAdapter);
	}
}
