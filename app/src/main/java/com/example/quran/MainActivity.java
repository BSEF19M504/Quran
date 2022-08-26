package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuranDAO quranDAO = new QuranDAO(this);
        List<SurahNames> array = quranDAO.getSurahNames();
        MainAdapter arrayAdapter = new MainAdapter(MainActivity.this, android.R.layout.simple_list_item_1,array);
        ListView listView = findViewById(R.id.listSurah);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SurahNames surah = (SurahNames) arrayAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this, VerseActivity.class);
                intent.putExtra("SurahId",surah.getId());
                startActivity(intent);
            }
        });
    }
}