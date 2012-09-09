package com.davidlee.scrollview;

import java.util.ArrayList;
import com.davidlee.scrollview.models.ItemListVodAdapter;
import com.davidlee.scrollview.models.VodEntity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class VodList extends Activity {

	private ListView vodListView;
	private TextView categoryName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_vod_list);

		vodListView = (ListView) findViewById(R.id.list_vod);
		categoryName = (TextView) findViewById(R.id.tvCatelogyName);

		Intent i = getIntent();
		String theloai = i.getStringExtra("theloai");
		categoryName.setText(theloai);

		ArrayList<VodEntity> lstVod = getVodList();
		vodListView.setAdapter(new ItemListVodAdapter(getApplicationContext(),
				lstVod));

	}

	private ArrayList<VodEntity> getVodList() {

		ArrayList<VodEntity> lstVods = new ArrayList<VodEntity>();

		for (int i = 0; i < 20; i++) {
			VodEntity oVod = new VodEntity();
			oVod.setId("" + i);
			oVod.setName("Phim " + i);
			oVod.setTime("" + (i + 30));
			oVod.setAddedDate("" + i);
			oVod.setImageUrl(R.id.poster);

			lstVods.add(oVod);
		}

		return lstVods;
	}
}
