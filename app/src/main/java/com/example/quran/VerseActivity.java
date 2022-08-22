package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class VerseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse);
        Intent intent = getIntent();
        int start = intent.getIntExtra("Start",-1);
        int end = intent.getIntExtra("End",-1);

        DataObject dataObject = new DataObject();

        String [] verses = Arrays.copyOfRange(dataObject.QuranArabicText, start-1, end-1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VerseActivity.this, android.R.layout.simple_list_item_1,verses);
        ListView listView = findViewById(R.id.listVerse);
        listView.setAdapter(arrayAdapter);
    }
}