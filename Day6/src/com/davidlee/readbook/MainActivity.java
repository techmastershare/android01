package com.davidlee.readbook;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private List<String> mListImage =  null;
	private String mFolder =  null;
	private PageCurlView mPageCurView  = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mFolder = "Conan";
    	mListImage = getImage(mFolder);
        initUI();
    }

    private List<String> getImage(String folder) {
    	List<String> list = null;
    	
    	try {
    		AssetManager assetManager = getAssets();
			String[] files = assetManager.list(folder);
			list = Arrays.asList(files);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
    	return list;
	}

	private void initUI() {
    	mPageCurView = new PageCurlView(getApplicationContext());
    	this.setContentView(mPageCurView);
    	mPageCurView.RenderPage(mFolder, mListImage, getApplicationContext());
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
