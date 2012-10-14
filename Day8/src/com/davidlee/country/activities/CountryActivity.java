package com.davidlee.country.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.davidlee.country.Config;
import com.davidlee.country.adapters.CountryListAdapter;
import com.davidlee.country.common.ItemList;
import com.davidlee.country.models.ItemListModel;

public class CountryActivity extends Activity {

	private TextView categoryNameTextView;
	private ListView countryListView;
	private int id = 1;
	private String categoryName = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.country_list_layout);

		Intent i = getIntent();
		id = i.getIntExtra(Config.ID, 1);
		categoryName = i.getStringExtra(Config.CATEGORY_NAME);

		initUI();
	}

	private void initUI() {

		categoryNameTextView = (TextView) findViewById(R.id.catelogy_name);
		countryListView = (ListView) findViewById(R.id.country_list_view);		
		categoryNameTextView.setText(categoryName);
		ItemListModel mItemListMode = getData(id);

		// Set adapter
		CountryListAdapter adapter = new CountryListAdapter(this);
		adapter.setItemListModel(mItemListMode);
		countryListView.setAdapter(adapter);

		// Set OnItemclick
		countryListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Object obj = adapter.getItemAtPosition(position);
				ItemList item = (ItemList) obj;

				Intent i = new Intent(CountryActivity.this, CountryDetailActivity.class);
				i.putExtra(Config.URL, item.getLinkContent());
				startActivity(i);
			}
		});
	}

	private ItemListModel getData(int id) {
		ItemListModel listModel = new ItemListModel();
		switch (id) {
		case 1: // Châu Mỹ
			listModel.add(new ItemList(1, "Antigua and Arbuda", "442 km2", "88,000", "St. John's", Config.URL + "Antigua_and_Barbuda", R.drawable.cm_flag_of_antigua_and_barbuda));
			listModel.add(new ItemList(2, "Argentina", "2,766,890 km2", "40,482,000", "Buenos Aires", Config.URL + "Argentina", R.drawable.cm_flag_of_argentina));
			listModel.add(new ItemList(3, "Barbados", "430 km2", "256,000", "Bridgetown", Config.URL + "Barbados", R.drawable.cm_flag_of_barbados));
			listModel.add(new ItemList(4, "Belize", "22,966 km2", "307,000", "Belmopan", Config.URL + "Belize", R.drawable.cm_flag_of_belize));
			listModel.add(new ItemList(5, "Bolivia", "1,098,580 km2", "9,863,000", "La Paz", Config.URL + "Bolivia", R.drawable.cm_flag_of_bolivia));
			listModel.add(new ItemList(6, "Brazil", "8,514,877 km2", "191,241,714", "Brasília", Config.URL + "Brazil", R.drawable.cm_flag_of_brazil));
			listModel.add(new ItemList(7, "Canada", "9,984,670 km2", "33,573,000", "Ottawa", Config.URL + "Canada", R.drawable.cm_flag_of_canada));
			listModel.add(new ItemList(8, "Colombia", "1,138,910 km2", "45,928,970", "Bogotá", Config.URL + "Colombia", R.drawable.cm_flag_of_colombia));
			listModel.add(new ItemList(9, "Costa Rica", "51,100 km2", "4,579,000", "San José", Config.URL + "Costa_Rica", R.drawable.cm_flag_of_costa_rica));
			listModel.add(new ItemList(10, "Cuba", "109,886 km2", "11,204,000", "Havana", Config.URL + "Cuba", R.drawable.cm_flag_of_cuba));
			listModel.add(new ItemList(11, "Chile", "756,950 km2", "16,928,873", "Santiago", Config.URL + "Chile", R.drawable.cm_flag_of_chile));
			listModel.add(new ItemList(12, "The Bahamas", "13,943 km2", "342,000", "Nassau", Config.URL + "The_Bahamas", R.drawable.cm_flag_of_the_bahamas));
			listModel.add(new ItemList(13, "United States", "9,629,091 km2", "311,630,000", "Washington, D.C.", Config.URL + "United_States", R.drawable.cm_flag_of_the_united_states));
			break;
		case 2: // Châu Phi
			listModel.add(new ItemList(1, "Antigua and Arbuda", "442 km2", "88,000", "St. John's", Config.URL + "Antigua_and_Barbuda", R.drawable.cm_flag_of_antigua_and_barbuda));
			listModel.add(new ItemList(2, "Argentina", "2,766,890 km2", "40,482,000", "Buenos Aires", Config.URL + "Argentina", R.drawable.cm_flag_of_argentina));
			listModel.add(new ItemList(3, "Barbados", "430 km2", "256,000", "Bridgetown", Config.URL + "Barbados", R.drawable.cm_flag_of_barbados));
			listModel.add(new ItemList(4, "Belize", "22,966 km2", "307,000", "Belmopan", Config.URL + "Belize", R.drawable.cm_flag_of_belize));
			listModel.add(new ItemList(5, "Bolivia", "1,098,580 km2", "9,863,000", "La Paz", Config.URL + "Bolivia", R.drawable.cm_flag_of_bolivia));
			listModel.add(new ItemList(6, "Brazil", "8,514,877 km2", "191,241,714", "Brasília", Config.URL + "Brazil", R.drawable.cm_flag_of_brazil));
			listModel.add(new ItemList(7, "Canada", "9,984,670 km2", "33,573,000", "Ottawa", Config.URL + "Canada", R.drawable.cm_flag_of_canada));
			listModel.add(new ItemList(8, "Colombia", "1,138,910 km2", "45,928,970", "Bogotá", Config.URL + "Colombia", R.drawable.cm_flag_of_colombia));
			listModel.add(new ItemList(9, "Costa Rica", "51,100 km2", "4,579,000", "San José", Config.URL + "Costa_Rica", R.drawable.cm_flag_of_costa_rica));
			listModel.add(new ItemList(10, "Cuba", "109,886 km2", "11,204,000", "Havana", Config.URL + "Cuba", R.drawable.cm_flag_of_cuba));
			listModel.add(new ItemList(11, "Chile", "756,950 km2", "16,928,873", "Santiago", Config.URL + "Chile", R.drawable.cm_flag_of_chile));
			listModel.add(new ItemList(12, "The Bahamas", "13,943 km2", "342,000", "Nassau", Config.URL + "The_Bahamas", R.drawable.cm_flag_of_the_bahamas));
			listModel.add(new ItemList(13, "United States", "9,629,091 km2", "311,630,000", "Washington, D.C.", Config.URL + "United_States", R.drawable.cm_flag_of_the_united_states));
			break;
		case 3: // Châu Âu
			listModel.add(new ItemList(1, "Antigua and Arbuda", "442 km2", "88,000", "St. John's", Config.URL + "Antigua_and_Barbuda", R.drawable.cm_flag_of_antigua_and_barbuda));
			listModel.add(new ItemList(2, "Argentina", "2,766,890 km2", "40,482,000", "Buenos Aires", Config.URL + "Argentina", R.drawable.cm_flag_of_argentina));
			listModel.add(new ItemList(3, "Barbados", "430 km2", "256,000", "Bridgetown", Config.URL + "Barbados", R.drawable.cm_flag_of_barbados));
			listModel.add(new ItemList(4, "Belize", "22,966 km2", "307,000", "Belmopan", Config.URL + "Belize", R.drawable.cm_flag_of_belize));
			listModel.add(new ItemList(5, "Bolivia", "1,098,580 km2", "9,863,000", "La Paz", Config.URL + "Bolivia", R.drawable.cm_flag_of_bolivia));
			listModel.add(new ItemList(6, "Brazil", "8,514,877 km2", "191,241,714", "Brasília", Config.URL + "Brazil", R.drawable.cm_flag_of_brazil));
			listModel.add(new ItemList(7, "Canada", "9,984,670 km2", "33,573,000", "Ottawa", Config.URL + "Canada", R.drawable.cm_flag_of_canada));
			listModel.add(new ItemList(8, "Colombia", "1,138,910 km2", "45,928,970", "Bogotá", Config.URL + "Colombia", R.drawable.cm_flag_of_colombia));
			listModel.add(new ItemList(9, "Costa Rica", "51,100 km2", "4,579,000", "San José", Config.URL + "Costa_Rica", R.drawable.cm_flag_of_costa_rica));
			listModel.add(new ItemList(10, "Cuba", "109,886 km2", "11,204,000", "Havana", Config.URL + "Cuba", R.drawable.cm_flag_of_cuba));
			listModel.add(new ItemList(11, "Chile", "756,950 km2", "16,928,873", "Santiago", Config.URL + "Chile", R.drawable.cm_flag_of_chile));
			listModel.add(new ItemList(12, "The Bahamas", "13,943 km2", "342,000", "Nassau", Config.URL + "The_Bahamas", R.drawable.cm_flag_of_the_bahamas));
			listModel.add(new ItemList(13, "United States", "9,629,091 km2", "311,630,000", "Washington, D.C.", Config.URL + "United_States", R.drawable.cm_flag_of_the_united_states));
			break;
		case 4: // Châu Á
			listModel.add(new ItemList(1, "Antigua and Arbuda", "442 km2", "88,000", "St. John's", Config.URL + "Antigua_and_Barbuda", R.drawable.cm_flag_of_antigua_and_barbuda));
			listModel.add(new ItemList(2, "Argentina", "2,766,890 km2", "40,482,000", "Buenos Aires", Config.URL + "Argentina", R.drawable.cm_flag_of_argentina));
			listModel.add(new ItemList(3, "Barbados", "430 km2", "256,000", "Bridgetown", Config.URL + "Barbados", R.drawable.cm_flag_of_barbados));
			listModel.add(new ItemList(4, "Belize", "22,966 km2", "307,000", "Belmopan", Config.URL + "Belize", R.drawable.cm_flag_of_belize));
			listModel.add(new ItemList(5, "Bolivia", "1,098,580 km2", "9,863,000", "La Paz", Config.URL + "Bolivia", R.drawable.cm_flag_of_bolivia));
			listModel.add(new ItemList(6, "Brazil", "8,514,877 km2", "191,241,714", "Brasília", Config.URL + "Brazil", R.drawable.cm_flag_of_brazil));
			listModel.add(new ItemList(7, "Canada", "9,984,670 km2", "33,573,000", "Ottawa", Config.URL + "Canada", R.drawable.cm_flag_of_canada));
			listModel.add(new ItemList(8, "Colombia", "1,138,910 km2", "45,928,970", "Bogotá", Config.URL + "Colombia", R.drawable.cm_flag_of_colombia));
			listModel.add(new ItemList(9, "Costa Rica", "51,100 km2", "4,579,000", "San José", Config.URL + "Costa_Rica", R.drawable.cm_flag_of_costa_rica));
			listModel.add(new ItemList(10, "Cuba", "109,886 km2", "11,204,000", "Havana", Config.URL + "Cuba", R.drawable.cm_flag_of_cuba));
			listModel.add(new ItemList(11, "Chile", "756,950 km2", "16,928,873", "Santiago", Config.URL + "Chile", R.drawable.cm_flag_of_chile));
			listModel.add(new ItemList(12, "The Bahamas", "13,943 km2", "342,000", "Nassau", Config.URL + "The_Bahamas", R.drawable.cm_flag_of_the_bahamas));
			listModel.add(new ItemList(13, "United States", "9,629,091 km2", "311,630,000", "Washington, D.C.", Config.URL + "United_States", R.drawable.cm_flag_of_the_united_states));
			break;
		case 5: // Châu Đại Dương
			listModel.add(new ItemList(1, "Antigua and Arbuda", "442 km2", "88,000", "St. John's", Config.URL + "Antigua_and_Barbuda", R.drawable.cm_flag_of_antigua_and_barbuda));
			listModel.add(new ItemList(2, "Argentina", "2,766,890 km2", "40,482,000", "Buenos Aires", Config.URL + "Argentina", R.drawable.cm_flag_of_argentina));
			listModel.add(new ItemList(3, "Barbados", "430 km2", "256,000", "Bridgetown", Config.URL + "Barbados", R.drawable.cm_flag_of_barbados));
			listModel.add(new ItemList(4, "Belize", "22,966 km2", "307,000", "Belmopan", Config.URL + "Belize", R.drawable.cm_flag_of_belize));
			listModel.add(new ItemList(5, "Bolivia", "1,098,580 km2", "9,863,000", "La Paz", Config.URL + "Bolivia", R.drawable.cm_flag_of_bolivia));
			listModel.add(new ItemList(6, "Brazil", "8,514,877 km2", "191,241,714", "Brasília", Config.URL + "Brazil", R.drawable.cm_flag_of_brazil));
			listModel.add(new ItemList(7, "Canada", "9,984,670 km2", "33,573,000", "Ottawa", Config.URL + "Canada", R.drawable.cm_flag_of_canada));
			listModel.add(new ItemList(8, "Colombia", "1,138,910 km2", "45,928,970", "Bogotá", Config.URL + "Colombia", R.drawable.cm_flag_of_colombia));
			listModel.add(new ItemList(9, "Costa Rica", "51,100 km2", "4,579,000", "San José", Config.URL + "Costa_Rica", R.drawable.cm_flag_of_costa_rica));
			listModel.add(new ItemList(10, "Cuba", "109,886 km2", "11,204,000", "Havana", Config.URL + "Cuba", R.drawable.cm_flag_of_cuba));
			listModel.add(new ItemList(11, "Chile", "756,950 km2", "16,928,873", "Santiago", Config.URL + "Chile", R.drawable.cm_flag_of_chile));
			listModel.add(new ItemList(12, "The Bahamas", "13,943 km2", "342,000", "Nassau", Config.URL + "The_Bahamas", R.drawable.cm_flag_of_the_bahamas));
			listModel.add(new ItemList(13, "United States", "9,629,091 km2", "311,630,000", "Washington, D.C.", Config.URL + "United_States", R.drawable.cm_flag_of_the_united_states));
			break;
		default:
			break;
		}

		return listModel;
	}
}
