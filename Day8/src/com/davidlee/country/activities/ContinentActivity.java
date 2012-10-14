package com.davidlee.country.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.davidlee.country.Config;
import com.davidlee.country.adapters.ContinentListAdapter;
import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;

public class ContinentActivity extends Activity {

	private ListView mContinentListView = null;
	private ItemListModel mItemListModel = null;
	private ContinentListAdapter mContinentListAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.continent_list_layout);

		initUI();
	}

	private void initUI() {
		mContinentListView = (ListView) findViewById(R.id.continent_list_view);

		mItemListModel = new ItemListModel();
		mItemListModel.add(new ItemList(1, "Châu Mỹ", "", "","", "", -1));
		mItemListModel.add(new ItemList(2, "Châu Phi", "", "","", "", -1));
		mItemListModel.add(new ItemList(3, "Châu Âu", "", "","", "", -1));
		mItemListModel.add(new ItemList(4, "Châu Á", "", "","", "", -1));
		mItemListModel.add(new ItemList(5, "Châu Đại Dương", "", "","", "", -1));

		mContinentListAdapter = new ContinentListAdapter(this);
		mContinentListAdapter.setItemListModel(mItemListModel);
		mContinentListView.setAdapter(mContinentListAdapter);

		mContinentListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Object obj = adapter.getItemAtPosition(position);
				ItemList item = (ItemList) obj;

				Intent i = new Intent(ContinentActivity.this, CountryActivity.class);
				i.putExtra(Config.ID, position+1);
				i.putExtra(Config.CATEGORY_NAME, item.getName());
				startActivity(i);
			}
		});

		mContinentListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> adapter, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}
}
