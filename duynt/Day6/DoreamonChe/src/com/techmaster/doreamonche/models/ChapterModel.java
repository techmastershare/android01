package com.techmaster.doreamonche.models;

public class ChapterModel extends AbstractModel{
	private int mIChapterIndex;
	private String mSChapterDescition;

	public ChapterModel(int index, String descrition) {
		setChapterIndex(index);
		setChapterDescition(descrition);
	}

	/**
	 * @return the mSChapterDescition
	 */
	public String getChapterDescition() {
		return mSChapterDescition;
	}

	/**
	 * @param mSChapterDescition the mSChapterDescition to set
	 */
	public void setChapterDescition(String mSChapterDescition) {
		this.mSChapterDescition = mSChapterDescition;
	}

	/**
	 * @return the mIChapterIndex
	 */
	public int getChapterIndex() {
		return mIChapterIndex;
	}

	/**
	 * @param mIChapterIndex the mIChapterIndex to set
	 */
	public void setChapterIndex(int mIChapterIndex) {
		this.mIChapterIndex = mIChapterIndex;
	}
}
