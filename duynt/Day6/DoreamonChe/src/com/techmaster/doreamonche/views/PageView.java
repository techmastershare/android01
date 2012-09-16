/**
 * 
 */
package com.techmaster.doreamonche.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import com.techmaster.doreamonche.R;
import com.techmaster.doreamonche.libs.PageCurlView;

/**
 * @author duynt4
 * 
 */
public class PageView extends AbstractView {
	private PageCurlView mPageView = null;
	private ArrayList<Bitmap> mList = null;

	public PageView(Context context, ArrayList<Bitmap> list) {
		super(context, null);
		// TODO Auto-generated constructor stub
		mList = list;
	}

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		if (mContext instanceof Activity) {
			Activity activity = (Activity) mContext;
			mPageView = (PageCurlView) activity.findViewById(R.id.page_view);
			mPageView.setPageList(mList);
		}
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub

	}
}
