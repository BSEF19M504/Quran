package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class NewBookmarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bookmark);

        QuranDAO quranDAO = new QuranDAO(this);
        Intent intent = getIntent();
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        List<Ayat> array = quranDAO.getAyatByBookmark();

        RecyclerView recyclerView = findViewById(R.id.bookmark_recycler_view);
        RecyclerAdapterBookmark recyclerAdapter = new RecyclerAdapterBookmark(NewBookmarkActivity.this, array);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewBookmarkActivity.this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}