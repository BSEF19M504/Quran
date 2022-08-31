package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NewSurahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_surah);

        QuranDAO quranDAO = new QuranDAO(NewSurahActivity.this);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        List<SurahNames> array;
        if(key.equals("Surah")){
            array = quranDAO.getSurahNames();
        }
        else{
            array = quranDAO.getParahNames();
        }

        RecyclerView recyclerView = findViewById(R.id.surah_recycler_view);
        RecyclerAdapterSurah recyclerAdapter = new RecyclerAdapterSurah(NewSurahActivity.this, array,translateEng,translateUrdu,key);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewSurahActivity.this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}