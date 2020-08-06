package org.example.architecturecompkeepnotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Overview : methods' roles in chronological order
 * OnCreateViewHolder : creates a view
 * ViewHolder : above method uses my constructor to initialize fields of note_items
 * onBindViewHolder : fills viewHolder object with data from List<Note>
 */

//This class has a setNotes() method which is an essential part
//Main Activity's observer's onChanged() method will call setNotes()
//method to change the notesList.

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    //essential to initialize here, unless we need to make null checks everywhere
    private List<Note> notes = new ArrayList<>();

    /**
     * Create a view object and pass it to View holder to create a view holder object
     * @param parent Recycler view of main activity
     * @param viewType
     * @return NoteHolder (a view holder object)
     */
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //parent is recycler view of main activity, so it contains context
        View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.note_items, parent, false);

        return new NoteHolder(itemView);
    }

    /**
     * receive viewHolder object and put data in it from our List<Note>
     * @param holder a ViewHolder object
     * @param position index in List<Note> : the data to be bind to viewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    //very important
    public void setNotes(List<Note> notes) {
        this.notes=notes;
        notifyDataSetChanged();
    }

    //used in Main Activity's ItemTouchHelper's onSwipe() method
    //to retrieve note object at the position where user clicks
    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }

    }

}
