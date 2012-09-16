package com.techmaster.doreamonche.views;

import com.techmaster.doreamonche.models.AbstractModel;

import android.content.Context;
import android.view.LayoutInflater;

public abstract class AbstractView {
	protected Context mContext;
	protected LayoutInflater mLayoutInflater;
	protected AbstractModel mModel;

	public AbstractView(Context context, AbstractModel model) {
		mContext = context;
		mModel = model;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	public abstract void initUI();

	public abstract void updateUI();

	public Context getContext() {
		return mContext;
	}

	public AbstractModel getModel() {
		return mModel;
	}
}
