package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class VerseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        Intent intent = getIntent();
        int id = intent.getIntExtra("SurahId",-1);
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        String nameEng = intent.getStringExtra("NameEng");
        String nameUrdu = intent.getStringExtra("NameUrdu");
        int index = intent.getIntExtra("StaringIndex",0);

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
        AyatAdapter arrayAdapter = new AyatAdapter(VerseActivity.this, android.R.layout.simple_list_item_1,verses);
        ListView listView = findViewById(R.id.listVerse);
        listView.setAdapter(arrayAdapter);
        listView.setSelection(index);
    }
}