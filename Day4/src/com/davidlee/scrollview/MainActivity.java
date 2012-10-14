package com.davidlee.scrollview;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

import com.davidlee.scrollview.customviews.HorizontalListView;
import com.davidlee.scrollview.models.CategoryEntity;
import com.davidlee.scrollview.models.CategoryListAdapter;
import com.davidlee.scrollview.models.CategoryModel;
import com.davidlee.scrollview.models.ItemEntity;
import com.davidlee.scrollview.models.ItemListAdapter;
import com.davidlee.scrollview.models.ItemModel;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initUI();
    }
    
    protected void initUI() {
		HorizontalListView hotcontentListView = (HorizontalListView) findViewById(R.id.list_hotcontent);
		HorizontalListView newcontentListView = (HorizontalListView) findViewById(R.id.list_newcontent);
		final ListView categoryListView = (ListView) findViewById(R.id.list_category);
		
		ItemModel itemModel = new ItemModel();
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.add(new CategoryEntity("Phim hành động"));
		categoryModel.add(new CategoryEntity("Phim kinh dị"));
		categoryModel.add(new CategoryEntity("Phim khoa học"));
		categoryModel.add(new CategoryEntity("Phim viễn tưởng"));
		categoryModel.add(new CategoryEntity("Phim võ thuật"));
		categoryModel.add(new CategoryEntity("Phim tâm lý"));
		
		int imageId = 0;
		
		for (int i=0; i< 20; i++) {
			imageId = getImageIdByNumber(i%3);
			ItemEntity item = new ItemEntity(i,imageId);
			itemModel.add(item);
		}
		
		ItemListAdapter adapter = new ItemListAdapter(getApplicationContext(), itemModel);
		CategoryListAdapter adapter_category = new CategoryListAdapter(getApplicationContext(), categoryModel);
		
		hotcontentListView.setAdapter(adapter);
		newcontentListView.setAdapter(adapter);
		categoryListView.setAdapter(adapter_category);
		
		hotcontentListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> adapter, View v,
					int position, long id) {
				Toast.makeText(getApplicationContext(), "Image "+ (int)(position+1) + " selected", Toast.LENGTH_SHORT).show();
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		categoryListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object obj = categoryListView.getItemAtPosition(position);
				CategoryEntity oCategory = (CategoryEntity) obj;
				Intent i = new Intent(getApplicationContext(), VodList.class);
				i.putExtra("theloai", oCategory.getName());
				startActivity(i);

			}

		});
	}
    
    protected int getImageIdByNumber(int number) {
    	switch (number) {
		case 0:
			return R.drawable.hotcontent1;
		case 1:
			return R.drawable.hotcontent2;
		case 2:
			return R.drawable.hotcontent3;
		default:
			return R.drawable.hotcontent1;
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
