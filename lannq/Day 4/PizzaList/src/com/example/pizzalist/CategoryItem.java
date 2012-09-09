package com.example.pizzalist;

public class CategoryItem {
	private String mItemTitle = null;
	private String mItemDescription = null;
	private int mImageId = -1;

	
	
	public CategoryItem(String itemTitle, String itemDescription, int imageId) {
		super();
		this.mItemTitle = itemTitle;
		this.mItemDescription = itemDescription;
		this.mImageId = imageId;
	}

	public int getImageId() {
		return mImageId;
	}

	public void setImageId(int imageId) {
		this.mImageId = imageId;
	}

	public String getItemTitle() {
		return mItemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.mItemTitle = itemTitle;
	}

	public String getItemDescription() {
		return mItemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.mItemDescription = itemDescription;
	}

}
