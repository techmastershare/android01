package com.davidlee.country.common;

public class ItemList {
	private String mName = "";
	private String mArea = "";
	private String mPopulation = "";
	private int mImageId = -1;

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getArea() {
		return mArea;
	}

	public void setArea(String area) {
		mArea = area;
	}

	public String getPopulation() {
		return mPopulation;
	}

	public void setPopulation(String population) {
		mPopulation = population;
	}

	public int getImageId() {
		return mImageId;
	}

	public void setImageId(int imageId) {
		mImageId = imageId;
	}

	public ItemList() {
	}
	
	public ItemList(String name, String area, String population, int imageId) {
		this.mName = name;
		this.mArea = area;
		this.mPopulation = population;
		this.mImageId = imageId;
	}
}
