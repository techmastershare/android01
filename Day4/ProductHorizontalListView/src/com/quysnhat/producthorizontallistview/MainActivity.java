package com.quysnhat.producthorizontallistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quysnhat.producthorizontallistview.customviews.HorizontalListView;
import com.quysnhat.producthorizontallistview.models.Product;
import com.quysnhat.producthorizontallistview.models.ProductListAdapter;
import com.quysnhat.producthorizontallistview.models.ProductModel;

public class MainActivity extends Activity {
	public int currentPosition = -1;
	public int count = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        
        initUI();
    }
    
    public void initUI() {
    	final HorizontalListView productListView = (HorizontalListView) findViewById(R.id.product_list_view);

    	ProductModel productModel = ProductList();
    	
    	final ProductListAdapter productListAdapter = new ProductListAdapter(getApplicationContext(), productModel);
    	productListView.setAdapter(productListAdapter);
    	
    	count = productListAdapter.getCount();
    	
    	final LinearLayout listItemLayout = (LinearLayout) findViewById(R.id.list_item_layout);
    	final LinearLayout detailLayout = (LinearLayout) findViewById(R.id.detail_layout);
    	final LinearLayout buttonLayout = (LinearLayout) findViewById(R.id.button_layout);
    	
    	final Button previousButton = (Button) findViewById(R.id.previous_button);
    	final Button nextButton = (Button) findViewById(R.id.next_button);
    	final Button listButton = (Button) findViewById(R.id.list_button);
    	
    	productListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				Product pro = (Product) productListView.getItemAtPosition(position);

				detailLayout.setVisibility(View.VISIBLE);
				buttonLayout.setVisibility(View.VISIBLE);
				listItemLayout.setVisibility(View.INVISIBLE);
				
				
				currentPosition = position;
				
				ViewDetail(pro);
				
			}
		});
    	
    	previousButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				currentPosition = currentPosition - 1;
				if (currentPosition < 0 ) {
					currentPosition = 0;
				}
				
				Product pro = (Product) productListView.getItemAtPosition(currentPosition);
				ViewDetail(pro);
			}
		});
    	
    	nextButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				currentPosition = currentPosition + 1;
				if (currentPosition > count - 1) {
					currentPosition = count - 1;
				}
				
				Product pro = (Product) productListView.getItemAtPosition(currentPosition);
				ViewDetail(pro);
			}
		});
    	
    	listButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				listItemLayout.setVisibility(View.VISIBLE);
				detailLayout.setVisibility(View.INVISIBLE);
				buttonLayout.setVisibility(View.INVISIBLE);
			}
		});
    	
    }
    
    protected void ViewDetail(Product product) {
    	ImageView imageView = (ImageView) findViewById(R.id.avatar_image_detail_view);
    	TextView nameTextView = (TextView) findViewById(R.id.name_detail_text_view);
    	TextView descriptionTextView = (TextView) findViewById(R.id.description_detail_text_view);
    	TextView priceTextView = (TextView) findViewById(R.id.price_detail_text_view);
    	TextView categoryTextView = (TextView) findViewById(R.id.category_detail_text_view);
    	
    	imageView.setImageResource(product.getImageId());
    	nameTextView.setText(product.getName());
    	descriptionTextView.setText(product.getDescription());
    	priceTextView.setText(product.getPriceString());
    	categoryTextView.setText(product.getCategoryString());
    }
    
    protected int getImageIdByNumber(int number) {
    	switch (number) {
		case 0:
			return R.drawable.htc_trophy;
		case 1:
			return R.drawable.htc_hd7;
		case 2:
			return R.drawable.lumia820;
		case 3:
			return R.drawable.lumia920;
		case 4:
			return R.drawable.iphone4;
		case 5:
			return R.drawable.iphone41;
		case 6:
			return R.drawable.galaxy_s2;
		case 7:
			return R.drawable.galaxy_3;
		case 8:
			return R.drawable.vaio;
		case 9:
			return R.drawable.ibm_thinkpad;
		default:
			return R.drawable.htc_trophy;
		}
    }
    
    protected ProductModel ProductList() {
    	ProductModel productModel = new ProductModel();
    	Product product = new Product("HTC 7 Trophy", 
					    			  "The HTC 7 Trophy takes gaming to a whole new level with the power of Xbox LIVE® and serious processor muscle. It even lets you fill the break between games with high fidelity, virtual surround sound music.", 
					    			   500, 
					    			   getImageIdByNumber(0), 
					    			   1);
    	productModel.add(product);
    	
    	product = new Product("HTC HD7", 
				  			  "The HTC 7 Trophy takes gaming to a whole new level with the power of Xbox LIVE® and serious processor muscle. It even lets you fill the break between games with high fidelity, virtual surround sound music.", 
				  			   500, 
				  			   getImageIdByNumber(1), 
				  			   1);
    	productModel.add(product);
    	
    	product = new Product("Nokia Lumia 820", 
	  			  "The first release of Java in 1996 generated an incredible amount of excitement, not just in the computer press, but in mainstream media such as The New York Times, The Washington Post, and Business Week. Java has the distinction of being the first and only programming language that had a 10-minute story on National Public Radio. A $100,000,000 venture capital fund was set up solely for products produced by use of a specific computer language. It is rather amusing to revisit those heady times, and we give you a brief history of Java in this chapter.", 
	  			   1500, 
	  			   getImageIdByNumber(2), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("Nokia Lumia 920", 
	  			  "The first release of Java in 1996 generated an incredible amount of excitement, not just in the computer press, but in mainstream media such as The New York Times, The Washington Post, and Business Week. Java has the distinction of being the first and only programming language that had a 10-minute story on National Public Radio. A $100,000,000 venture capital fund was set up solely for products produced by use of a specific computer language. It is rather amusing to revisit those heady times, and we give you a brief history of Java in this chapter.", 
	  			   2000, 
	  			   getImageIdByNumber(3), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("IPhone 4", 
	  			  "The first release of Java in 1996 generated an incredible amount of excitement, not just in the computer press, but in mainstream media such as The New York Times, The Washington Post, and Business Week. Java has the distinction of being the first and only programming language that had a 10-minute story on National Public Radio. A $100,000,000 venture capital fund was set up solely for products produced by use of a specific computer language. It is rather amusing to revisit those heady times, and we give you a brief history of Java in this chapter.", 
	  			   1000, 
	  			   getImageIdByNumber(4), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("IPhone 4S", 
	  			  "The first release of Java in 1996 generated an incredible amount of excitement, not just in the computer press, but in mainstream media such as The New York Times, The Washington Post, and Business Week. Java has the distinction of being the first and only programming language that had a 10-minute story on National Public Radio. A $100,000,000 venture capital fund was set up solely for products produced by use of a specific computer language. It is rather amusing to revisit those heady times, and we give you a brief history of Java in this chapter.", 
	  			   1000, 
	  			   getImageIdByNumber(5), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("Samsung Galaxy SII", 
	  			  "Description", 
	  			   1000, 
	  			   getImageIdByNumber(6), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("Samsung Galaxy SIII", 
	  			  "Description", 
	  			   1200, 
	  			   getImageIdByNumber(7), 
	  			   1);
    	productModel.add(product);
    	
    	product = new Product("Sony vaio", 
	  			  "Description", 
	  			   2000, 
	  			   getImageIdByNumber(8), 
	  			   0);
    	productModel.add(product);
    	
    	product = new Product("IBM Thinkpad", 
	  			  "Description", 
	  			   3000, 
	  			   getImageIdByNumber(9), 
	  			   0);
    	productModel.add(product);
    	
    	return productModel;
    }
}
