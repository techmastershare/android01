/**
 * 
 */
package com.techmaster.doreamonche.models;

import java.util.ArrayList;

import android.graphics.Bitmap;

/**
 * @author duynt4
 * 
 */
public class BookModel extends AbstractModel {
	private ArrayList<Bitmap> mListPage = null;

	public BookModel(final ArrayList<Bitmap> listPage) {
		mListPage = listPage;
	}

	public void setListPage(final ArrayList<Bitmap> listPage) {
		mListPage = listPage;
	}

	public ArrayList<Bitmap> getListPage() {
		return mListPage;
	}
}
