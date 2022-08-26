package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class VerseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        Intent intent = getIntent();
        int id = intent.getIntExtra("SurahId",-1);

        QuranDAO quranDAO = new QuranDAO(this);

        ArrayList<String> verses = quranDAO.getAyatBySurah(id);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VerseActivity.this, android.R.layout.simple_list_item_1,verses);
        ListView listView = findViewById(R.id.listVerse);
        listView.setAdapter(arrayAdapter);
    }
}