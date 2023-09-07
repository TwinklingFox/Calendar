package com.example.calendar.domain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.MainActivity;
import com.example.calendar.R;
import com.example.calendar.WatchNoteActivity;
import com.example.calendar.entity.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private List<Note> items;

    private final MainActivity mainActivity;

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Note> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public NotesAdapter(List<Note> items, MainActivity mainActivity) {
        this.items = items;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent, false);
        return new NoteViewHolder(itemView, mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.noteHeader.setText(items.get(position).getNoteHeader());
        holder.noteText.setText(items.get(position).getNoteText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        MainActivity mainActivity;

        View view;
        TextView noteHeader;
        TextView noteText;


        public NoteViewHolder(@NonNull View itemView, MainActivity mainActivity) {
            super(itemView);
            view = itemView;
            noteHeader = itemView.findViewById(R.id.headerOfNoteTextView);
            noteText = itemView.findViewById(R.id.textOfNoteTextView);
            this.mainActivity = mainActivity;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mainActivity.onNoteClick((String) noteHeader.getText(), (String) noteText.getText());
        }
    }
}
