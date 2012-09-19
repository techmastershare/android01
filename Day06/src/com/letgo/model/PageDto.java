package com.letgo.model;

import com.everyleaf.animation.SampleAnimationActivity;

import android.graphics.Bitmap;
import android.util.Log;

public class PageDto {
	private String mUrl = null;
	private String mUrlNextPage;
	private String mUrlPrivousPage;
	private String mUrlImage;

	public PageDto() {
	}

	public PageDto(String url) {
		this.mUrl = url;
	}

	public String getUrlImage() {
		return mUrlImage;
	}

	public void setUrlImage(String mUrlImage) {
		this.mUrlImage = mUrlImage;
	}

	public String getUrlNextPage() {
		return mUrlNextPage;
	}

	public void setUrlNextPage(String mUrlNextPage) {
		this.mUrlNextPage = mUrlNextPage;
	}

	public String getUrlPrivousPage() {
		return mUrlPrivousPage;
	}

	public void setUrlPrivousPage(String mUrlPrivousPage) {
		this.mUrlPrivousPage = mUrlPrivousPage;
	}


	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

}
