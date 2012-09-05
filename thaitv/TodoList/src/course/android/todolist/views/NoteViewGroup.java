package course.android.todolist.views;


import course.android.todolist.controllers.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class NoteViewGroup extends LinearLayout {

	public NoteViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
		li.inflate(R.layout.item_note, this, true);
	}



}
