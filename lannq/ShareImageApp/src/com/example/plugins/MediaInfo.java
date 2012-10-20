package com.example.plugins;

import org.json.JSONException;
import org.json.JSONObject;

public class MediaInfo {
	private long mId = 0;
	private String mDisplayName = ""; // File name
	private String mFilePathName = ""; // Full file path name
	private long mSize = 0;
	private String mThumbnailPathName = ""; // Thumbnail file path name

	private static final String ID = "id";
	private static final String DISPLAY_NAME = "displayName";
	private static final String FILE_PATH_NAME = "filePathName";
	private static final String SIZE = "size";
	private static final String THUMBNAIL_PATH_NAME = "thumbnailPathName";

	public MediaInfo() {};
	
	public MediaInfo(long id, final String displayName, final String filePathName, 
			long size, final String thumbnailPathName) {
		mId = id;
		mDisplayName = displayName;
		mFilePathName = filePathName;
		mSize = size;
		mThumbnailPathName = thumbnailPathName;
	}

	public long getId() {
		return mId;
	}
	public void setId(long id) {
		this.mId = id;
	}
	public String getDisplayName() {
		return mDisplayName;
	}
	public void setDisplayName(String displayName) {
		this.mDisplayName = displayName;
	}
	public String getFilePathName() {
		return mFilePathName;
	}
	public void setFilePathName(String filePathName) {
		this.mFilePathName = filePathName;
	}
	public long getSize() {
		return mSize;
	}
	public void setSize(long size) {
		this.mSize = size;
	}
	public String getThumbnailPathName() {
		return mThumbnailPathName;
	}
	public void setThumbnailPathName(String thumbnailPathName) {
		this.mThumbnailPathName = thumbnailPathName;
	}

	public JSONObject toJSON() {
		JSONObject data = new JSONObject();
		try {
			data.put(ID, mId);
			data.put(DISPLAY_NAME, mDisplayName);
			data.put(FILE_PATH_NAME, mFilePathName);
			data.put(SIZE, mSize);
			data.put(THUMBNAIL_PATH_NAME, mThumbnailPathName);
			return data;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String toJSONString() {
		JSONObject data = toJSON();
		if (data == null)
			return "";

		return data.toString();
	}
}
