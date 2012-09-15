package com.example.listviewimage;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends Activity {
	private ListView mListView = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		initUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

	protected void initUI() {
		mListView = (ListView) findViewById(R.id.list_view);

		final ArrayList<Item> itemList = new ArrayList<Item>();
		final ListViewAdapter adapter = new ListViewAdapter(this, R.layout.item_list_view, itemList);
		Button btnAddItem = (Button) findViewById(R.id.btnAddItem);
		btnAddItem.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int childrentCount = mListView.getChildCount();
				int imageId = 0;
				Item itemData = null;
				switch (childrentCount % 10) {
				case 0:
					imageId = R.drawable.diemvuong;
					itemData = new Item(imageId, "Sao ", "Diêm Vương ");
					break;
				case 1:
					imageId = R.drawable.haivuong;
					itemData = new Item(imageId, "Sao ", "Hải Vương");
					break;
				case 2:
					imageId = R.drawable.hoa;
					itemData = 	new Item(imageId, "Sao ", "Hỏa");
					break;
				case 3:
					imageId = R.drawable.kim;
					itemData = new Item(imageId, "Sao ", "Kim");
					break;
				case 4:
					imageId = R.drawable.mattrang;
					itemData = new Item(imageId, "Sao ", "Mặt Trăng");
					break;
				case 5:
					imageId = R.drawable.mattroi;
					itemData = new Item(imageId, "Sao ", "Mặt Trời");
					break;
				case 6:
					imageId = R.drawable.moc;
					itemData = new Item(imageId, "Sao ", "Mộc");
					break;
				case 7:
					imageId = R.drawable.thienvuong;
					itemData = new Item(imageId, "Sao ", "Thiên Vương");
					break;
				case 8:
					imageId = R.drawable.tho;
					new Item(imageId, "Sao ", "Thổ");
					break;
				case 9:
					imageId = R.drawable.thuy;
					itemData = new Item(imageId, "Sao ", "Thủy");
					break;
		
			
				default:
					break;
				}

				itemList.add(itemData);
				
				mListView.setAdapter(adapter);

			}
		});

	}
}
