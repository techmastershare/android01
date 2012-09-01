/**
 * 
 */
package com.techmaster.todolist;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author duynt4
 * 
 */
public class CustomLinearLayout extends LinearLayout {
	// in multi - selected mode
	private boolean isSelected = false;
	private ArrayList<NoteInfalter> children = null;
	private ArrayList<NoteInfalter> seletedChildrens = null;

	public CustomLinearLayout(Context arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		children = new ArrayList<NoteInfalter>();
		seletedChildrens = new ArrayList<NoteInfalter>();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CustomLinearLayout(Context arg0, AttributeSet arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		children = new ArrayList<NoteInfalter>();
		seletedChildrens = new ArrayList<NoteInfalter>();
	}

	public void addView(View v, LayoutParams layoutParams,
			NoteInfalter noteInfalter) {
		super.addView(v, layoutParams);
		children.add(noteInfalter);
	}

	public void removeView(NoteInfalter noteInfalter) {
		RelativeLayout relativeLayout = noteInfalter.getMainLayout();
		relativeLayout.removeAllViews();
		super.removeView(relativeLayout);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		super.onTouchEvent(arg0);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		int action = arg0.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			float x = arg0.getX();
			float y = arg0.getY();
			int children = getChildCount();
			for (int i = 0; i < children; ++i) {
				View layout = getChildAt(i);
				if (isTouch(layout, x, y)) {
					if (this.children.size() > i) {
						NoteInfalter noteInfalter = this.children.get(i);
						if (!isSelected) {
							// if in single select mode
							// and then, remove all selected item
							for (int j = 0, n = this.seletedChildrens.size(); j < n; ++j) {
								this.seletedChildrens.get(j).setSelected(false);
							}
							this.seletedChildrens.clear();
						}
						// select new item
						if (!noteInfalter.isSelected()) {
							noteInfalter.setSelected(true);
							this.seletedChildrens.add(noteInfalter);
						} else {
							noteInfalter.setSelected(false);
							this.seletedChildrens.remove(noteInfalter);
						}
					}
				}
			}
		}
		return super.onInterceptTouchEvent(arg0);
	}

	public boolean isTouch(View v, float x, float y) {
		RelativeLayout layout = (RelativeLayout) v;
		int vx = layout.getLeft();
		int vy = layout.getTop();
		float w = layout.getWidth();
		float h = layout.getHeight();

		if (x > vx + w || x < vx || y < vy || y > vy + h) {
			return false;
		}
		return true;
	}

	/**
	 * @return the children
	 */
	public ArrayList<NoteInfalter> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(ArrayList<NoteInfalter> children) {
		this.children = children;
	}

	/**
	 * @return the seletedChildrens
	 */
	public ArrayList<NoteInfalter> getSeletedChildrens() {
		return seletedChildrens;
	}

	/**
	 * @param seletedChildrens
	 *            the seletedChildrens to set
	 */
	public void setSeletedChildrens(ArrayList<NoteInfalter> seletedChildrens) {
		this.seletedChildrens = seletedChildrens;
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 *            the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
