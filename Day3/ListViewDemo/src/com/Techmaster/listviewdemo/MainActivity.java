package com.Techmaster.listviewdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;
import com.Techmaster.listviewdemo.ListViewAdapter.Callback;

public class MainActivity extends Activity {
	private ListView mListView = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize GUI
        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    protected void initUI() {
    	// Get list view from layout
    	mListView = (ListView) findViewById(R.id.list_view);
    	
    	// Create an adapter for the list view
    	ArrayList<Item> itemList = new ArrayList<Item>();
    	for (int i = 0; i < 30; i++) {
    		Item item = new Item("Tile " + i, "Details " + i);
    		itemList.add(item);
    	}
    	
    	ListViewAdapter adapter = new ListViewAdapter(this, itemList);
    	adapter.setCallback(new Callback() {
			
			public void OnItemImage(Item item) {
				String title=item.getTile();
    			int duration = title.length();
    			Toast toast = Toast.makeText(getApplicationContext(), title, duration);
    			toast.show();
			}
		});
    	
    	mListView.setAdapter(adapter);
    }
}
