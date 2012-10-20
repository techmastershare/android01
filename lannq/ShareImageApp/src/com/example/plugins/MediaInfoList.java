package com.example.plugins;

import java.util.ArrayList;

import org.json.JSONArray;

public class MediaInfoList {
	private ArrayList<MediaInfo> mMediaInfoList = new ArrayList<MediaInfo>();

	public boolean add(final MediaInfo mediaInfo) {
		mMediaInfoList.add(mediaInfo);
		return true;
	}

	public MediaInfo get(int index) {
		return mMediaInfoList.get(index);
	}

	public boolean remove(final MediaInfo mediaInfo) {
		mMediaInfoList.remove(mediaInfo);
		return true;
	}

	public int size() {
		return mMediaInfoList.size();
	}

	public JSONArray toJSONArray() {
		int count = size();
		if (count == 0)
			return null;

		JSONArray ja = new JSONArray();
		for (int i = 0; i < size(); i++) {
			ja.put(get(i).toJSON());
		}
		return ja;
	}

	public String toJSONString() {
		JSONArray ja = toJSONArray();
		if (ja == null)
			return "";

		return ja.toString();
	}
}
