package com.quysnhat.producthorizontallistview.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ProductModel {
	private ArrayList<Product> mProductList = new ArrayList<Product>();
	
	public void add(final Product product){
		mProductList.add(product);
	}
	
	public Product get(final int index){
		if (mProductList.size() < 0)
			return null;
		return mProductList.get(index);
	}
	
	public boolean remove(final Product product){
		return mProductList.remove(product);
	}
	
	public int count(){
		return mProductList.size();
	}
	
	public void clear(){
		mProductList.clear();
	}
	
	public void setByName(){
		Collections.sort(mProductList, new Comparator<Product>() {
			public int compare(Product p1, Product p2){
				String name1 = p1.getName();
				String name2 = p2.getName();
				return name1.compareToIgnoreCase(name2);
			}
		});
	}
	
	
}
