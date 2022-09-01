package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class NewVerseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_verse);

        Intent intent = getIntent();
        int id = intent.getIntExtra("SurahId",-1);
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        String nameEng = intent.getStringExtra("NameEng");
        String nameUrdu = intent.getStringExtra("NameUrdu");

        String key = intent.getStringExtra("key");
        QuranDAO quranDAO = new QuranDAO(this);
        quranDAO.setTranslation(translateEng,translateUrdu);

        ArrayList<Ayat> verses;
        if(key.equals("Surah")){
            verses = quranDAO.getAyatBySurah(id);
        }
        else{
            verses = quranDAO.getAyatByParah(id);
        }
        TextView textView = findViewById(R.id.verseTitleEng);
        textView.setText(nameEng);
        TextView textView1 = findViewById(R.id.verseTitleUrdu);
        textView1.setText(nameUrdu);

        RecyclerView recyclerView = findViewById(R.id.ayat_recycler_view);
        RecyclerAdapterVerse recyclerAdapter = new RecyclerAdapterVerse(NewVerseActivity.this, verses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewVerseActivity.this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}