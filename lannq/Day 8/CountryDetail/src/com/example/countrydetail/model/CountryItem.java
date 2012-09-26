package com.example.countrydetail.model;

public class CountryItem {
	private String mName = null;
	private String mRegion = null;
	private int mFlagImageId = -1;
	
	

	public CountryItem(String name, String region, int flagImageId) {
		super();
		this.mName = name;
		this.mRegion = region;
		this.mFlagImageId = flagImageId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getRegion() {
		return mRegion;
	}

	public void setRegion(String region) {
		this.mRegion = region;
	}

	public int getFlagImageId() {
		return mFlagImageId;
	}

	public void setFlagImageId(int flagImageId) {
		this.mFlagImageId = flagImageId;
	}

}
