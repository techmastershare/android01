/**
 * 
 */
package com.techmaster.todolist;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * @author duynt4
 * 
 */
public class EditorActivity extends Activity {
	protected class UIClass {
		public EditText nameEditText;
		public Editor contentEditText;
		public ImageButton cancelButton;
		public ImageButton okButton;

		public UIClass() {
			nameEditText = (EditText) findViewById(R.id.name_edit_text);
			contentEditText = (Editor) findViewById(R.id.content_edit_text);
			cancelButton = (ImageButton) findViewById(R.id.cancel_button);
			okButton = (ImageButton) findViewById(R.id.ok_button);
		}
	}

	protected UIClass ui = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editor);
		ui = new UIClass();
	}

	@Override
	public void onBackPressed() {
		// Return to main and close activity.
		setResult(Constant.CANCEL);
		finish();
	}

	/**
	 * Create my own edit text with lines
	 * 
	 * @author duynt4
	 * 
	 */
	public static class Editor extends EditText {
		private Rect mRect;
		private Paint mPaint;

		// This constructor is used by LayoutInflater
		public Editor(Context context, AttributeSet attrs) {
			super(context, attrs);
			mRect = new Rect();
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setColor(0x800000FF);
		}

		/**
		 * draw editor instance
		 * 
		 * @param canvas
		 *            : The canvas on which the background is drawn.
		 */
		@Override
		protected void onDraw(Canvas canvas) {
			int height = getHeight();
			int space = getLineHeight();
			int countH = height / space;
			int count = getLineCount();
			if (countH > count) {
				count = countH;
			}
			int baseLine = getLineBounds(0, mRect);
			Rect r = mRect;
			Paint paint = mPaint;

			/*
			 * Draws one line in the rectangle for every line of text in the
			 * EditText
			 */
			for (int i = 0; i < count; i++) {

				canvas.drawLine(r.left, baseLine + 1, r.right, baseLine + 1,
						paint);
				baseLine += getLineHeight();
			}
			super.onDraw(canvas);
		}
	}
}
