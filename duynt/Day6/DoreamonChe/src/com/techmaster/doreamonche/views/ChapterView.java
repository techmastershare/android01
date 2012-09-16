package com.techmaster.doreamonche.views;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.techmaster.doreamonche.Common;
import com.techmaster.doreamonche.PageActivity;
import com.techmaster.doreamonche.R;
import com.techmaster.doreamonche.models.ChapterModel;
import com.techmaster.doreamonche.views.ChapterAdapter.OnSelectChapter;

public class ChapterView extends AbstractView {
	private ViewFlow mViewFlow;
	private ArrayList<ChapterModel> mModelList = null;

	public ChapterView(Context context, final ArrayList<ChapterModel> modelList) {
		super(context, null);
		// TODO Auto-generated constructor stub
		this.mModelList = modelList;
	}

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		if (getContext() instanceof Activity) {
			Activity activity = (Activity) getContext();
			mViewFlow = (ViewFlow) activity
					.findViewById(R.id.descrition_view_flow);

			ChapterAdapter adapter = new ChapterAdapter(getContext(),
					mModelList);
			adapter.setCallback(new OnSelectChapter() {

				@Override
				public void onSelected(ChapterModel chapterModel) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(ChapterView.this.getContext(),
							PageActivity.class);
					intent.putExtra(Common.CHAPTER_ID,
							chapterModel.getChapterIndex());
					Activity activity = (Activity) ChapterView.this
							.getContext();
					activity.startActivityForResult(intent, 0);
				}
			});
			mViewFlow.setAdapter(adapter, 0);
			TitleFlowIndicator indicator = (TitleFlowIndicator) activity
					.findViewById(R.id.tile_flow_indicator);
			indicator.setTitleProvider(adapter);
			mViewFlow.setFlowIndicator(indicator);
		}

	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub

	}

	public void onConfigurationChanged(Configuration newConfig) {
		mViewFlow.onConfigurationChanged(newConfig);
	}
}
