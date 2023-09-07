package com.example.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calendar.domain.NotesAdapter;
import com.example.calendar.entity.Note;
import com.example.calendar.presentation.QuotesViewModel;
import com.example.calendar.presentation.RecyclerViewViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    TextView quoteTextView;
    TextView authorTextView;

    RecyclerView recyclerView;
    NotesAdapter adapter;

    CompositeDisposable compDisposable;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        authorTextView = findViewById(R.id.authorTextView);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new NotesAdapter(new ArrayList<>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        QuotesViewModel quotesViewModel = new ViewModelProvider(this).get(QuotesViewModel.class);
        try {
            quotesViewModel.getApiResult();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        quotesViewModel.getQuoteLiveData().observe(this, quote -> quoteTextView.setText(quote));
        quotesViewModel.getAuthorLiveData().observe(this, author -> authorTextView.setText(author));

        RecyclerViewViewModel recyclerViewViewModel = new ViewModelProvider(this).get(RecyclerViewViewModel.class);
        recyclerViewViewModel.getNoteLiveData().observe(this, notesFromDb -> adapter.setItems(notesFromDb));

        recyclerViewViewModel.getCompDisposableLiveData().observe(this, disposables -> compDisposable = disposables);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compDisposable.dispose();
    }

    public void onNoteClick(String header, String note) {
        Intent intent = new Intent(this, WatchNoteActivity.class);
        intent.putExtra("header", header);
        intent.putExtra("note", note);
        startActivity(intent);
    }
}