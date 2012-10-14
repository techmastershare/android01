package com.davidlee.country.common;

public class ItemList {
	private int mId = -1;
	private String mName = "";
	private String mArea = "";
	private String mPopulation = "";
	private String mCapital = "";
	private String mLinkContent = "";
	private int mImageId = -1;
	
	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

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
	
	public String getCapital() {
		return mCapital;
	}

	public void setCapital(String capital) {
		this.mCapital = capital;
	}
	
	public String getLinkContent() {
		return mLinkContent;
	}

	public void setLinkContent(String linkContent) {
		this.mLinkContent = linkContent;
	}

	public int getImageId() {
		return mImageId;
	}

	public void setImageId(int imageId) {
		mImageId = imageId;
	}

	public ItemList() {
	}
	
	public ItemList(int id, String name, String area, String population, String capital, String linkContent, int imageId) {
		this.mId = id;
		this.mName = name;
		this.mArea = area;
		this.mPopulation = population;
		this.mCapital = capital;
		this.mLinkContent = linkContent;
		this.mImageId = imageId;
	}
}
