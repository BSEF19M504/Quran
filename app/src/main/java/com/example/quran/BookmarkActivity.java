package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        QuranDAO quranDAO = new QuranDAO(this);
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
    }
}