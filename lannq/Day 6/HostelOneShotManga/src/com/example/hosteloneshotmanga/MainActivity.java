package com.example.hosteloneshotmanga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {

	private List<String> mListImages = null;
	private String mFolder = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);

		mFolder = "Hotel";

		try {
			mListImages = getImage(mFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PageCurlView pageCurlView = new PageCurlView(getApplicationContext());
		this.setContentView(pageCurlView);
		pageCurlView.RenderPage(mFolder, mListImages, getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
	}

	private List<String> getImage(String folder) throws IOException {
		AssetManager assetManager = getAssets();
		String[] files = assetManager.list(folder);
		List<String> it = Arrays.asList(files);
		return it;

	}
}
