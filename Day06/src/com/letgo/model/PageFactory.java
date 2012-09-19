package com.letgo.model;

import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.R.string;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.everyleaf.animation.PageCurlView;
import com.everyleaf.animation.SampleAnimationActivity;
import com.letgo.io.HttpConnection;

public class PageFactory {

	private String mUrl = null;
	private PageDto mPage;
	private PageDto nextPage;
	private PageCurlView holer;
	
	public PageFactory(String url, PageDto page, PageCurlView holer) {
		this.mUrl = url;
		this.nextPage = page;
		this.holer = holer;
	}

	private void setContent(String contentJson) {

		mPage = genData(contentJson);
		// handle url Image -> download Image
		downloadTwitterIcon(mPage, mPage.getUrlImage());
//		if (mPage != null) {
//			Log.d("applicationHaivl", mPage.getUrlImage());
//			Log.d("applicationHaivl", mPage.getUrlNextPage());
//			Log.d("applicationHaivl", mPage.getUrlPrivousPage());
//		}

	}

	public PageDto genData(String data) {
		PageDto page = null;
		try {
			page = new PageDto(mUrl);
			data = data.substring(data.indexOf("photoImg"), data.length());
			String image = data.substring(data.indexOf("/data/photos/"), data.indexOf(".jpg")+4);
			page.setUrlImage("http://www.haivl.com"+image);
			// prev buttons
			data = data.substring(data.indexOf("prev buttons"), data.length());
			String pre = data.substring(data.indexOf("/photo"), data.indexOf(">")-1);
			page.setUrlPrivousPage("http://www.haivl.com"+pre);
			// next buttons
			data = data.substring(data.indexOf("next buttons"), data.length());
			String next = data.substring(data.indexOf("/photo"), data.indexOf(">")-1);
			page.setUrlNextPage("http://www.haivl.com"+next);

			

			
			if(nextPage != null)
				this.nextPage.setUrl(page.getUrlNextPage());
		} catch (Exception e) {
			Log.d("applicationHaivl", e.toString());
		}
		return page;
	}

	public void startDownload() {
		if (mUrl != null && !mUrl.equals(""))
			downloadTwitterStream(mUrl);
	}

	public void downloadTwitterStream(String url) {
		Handler handler = new Handler() {
			public void handleMessage(Message message) {
				switch (message.what) {
				case HttpConnection.DID_START: {
					System.out.println("Starting connection...");
					break;
				}
				case HttpConnection.DID_SUCCEED: {
					String response = (String) message.obj;
					setContent(response);
					Log.d("application", "DownloadText Complete"+response);
					break;
				}
				case HttpConnection.DID_ERROR: {
					Exception e = (Exception) message.obj;
					e.printStackTrace();
					System.out.println("Connection failed.....");
					break;
				}
				}
			}
		};
		new HttpConnection(handler).get(url);
	}

	public void downloadTwitterIcon(final PageDto dto, String urlImage) {
		Handler handler = new Handler() {
			public void handleMessage(Message message) {
				switch (message.what) {
				case HttpConnection.DID_START: {
					Log.d("Image", "Starting Connection");
					break;
				}
				case HttpConnection.DID_SUCCEED: {
					Bitmap image = (Bitmap) message.obj;
					if(image != null && holer != null){
						holer.changeImageForeground(image);
					}
					Log.d("application", "DownloadImage Complete");
					break;
				}
				case HttpConnection.DID_ERROR: {
					Exception e = (Exception) message.obj;
					e.printStackTrace();
					break;
				}
				}
			}
		};
		new HttpConnection(handler).bitmap(urlImage);
	}

}
