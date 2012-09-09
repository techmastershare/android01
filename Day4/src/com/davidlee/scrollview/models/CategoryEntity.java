package com.davidlee.scrollview.models;

public class CategoryEntity {

	private String mName = "";
	
	public String getName() {
		return mName;
	}
	public void setName(String name) {
		this.mName = name;
	}
	
	public CategoryEntity(final String name) {
		this.mName =  name;
	}
	
	public CategoryEntity() {
	}
}
