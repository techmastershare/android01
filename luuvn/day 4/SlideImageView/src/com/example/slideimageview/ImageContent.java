package com.example.slideimageview;

public class ImageContent {
	private String mImageView;
	private String mTitleView;
	private int mImageId;

	public int getmImageId() {
		return mImageId;
	}

	public void setmImageId(int mImageId) {
		this.mImageId = mImageId;
	}

	public String getmImageView() {
		return mImageView;
	}

	public void setmImageView(String mImageView) {
		this.mImageView = mImageView;
	}

	public String getmTitleView() {
		return mTitleView;
	}

	public void setmTitleView(String mTitleView) {
		this.mTitleView = mTitleView;
	}

	public ImageContent(String mTitleView, int mImageId) {
		super();
		this.mTitleView = mTitleView;
		this.mImageId = mImageId;
	}

}
