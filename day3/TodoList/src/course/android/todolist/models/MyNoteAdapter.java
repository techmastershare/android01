package course.android.todolist.models;

import java.util.List;

import org.w3c.dom.Text;

import course.android.todolist.common.MyConst;
import course.android.todolist.controllers.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyNoteAdapter extends BaseAdapter  {

	private Context mContext;
	private List<Note> mListNote;
	/**
	 * @param mContext
	 * @param mListNote
	 */
	public MyNoteAdapter(Context mContext, List<Note> mListNote) {
		super();
		this.mContext = mContext;
		this.mListNote = mListNote;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mListNote.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mListNote.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Note note = mListNote.get(position);
		if(convertView == null){
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_note, null);
		}
		
		TextView noteTitleTextView = (TextView) convertView.findViewById(R.id.noteTitleTextView);
		noteTitleTextView.setText(note.getTitle());
		
		TextView noteDetailTextView = (TextView) convertView.findViewById(R.id.noteDetailTextView);
		String shortNoteDetail = note.getDetail();
		if(note.getDetail().length() > MyConst.LIMIT_STRING){
			shortNoteDetail = note.getDetail().substring(MyConst.START_SUB_STRING, MyConst.LIMIT_STRING) + "...";
		}
		noteDetailTextView.setText(shortNoteDetail);
		
		TextView noteDeadlineTextView = (TextView) convertView.findViewById(R.id.noteDeadlineTextView);
		noteDeadlineTextView.setText(note.getDeadLine());
		
		if(note.getStatusCode() == Note.SUCCESS){
			noteTitleTextView.setTextColor(Color.BLUE);
			noteDetailTextView.setTextColor(Color.BLUE);
			noteDeadlineTextView.setTextColor(Color.BLUE);
		}else{
			noteTitleTextView.setTextColor(Color.RED);
			noteDetailTextView.setTextColor(Color.RED);
			noteDeadlineTextView.setTextColor(Color.RED);
		}
		return convertView;
	}

}
