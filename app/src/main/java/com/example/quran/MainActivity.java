package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataObject dataObject = new DataObject();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,dataObject.GetSurahNames());
        ListView listView = findViewById(R.id.listSurah);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int start = dataObject.getSurahStart(i);
                int end = dataObject.getSurahStart(i+1);
                Intent intent = new Intent(MainActivity.this, VerseActivity.class);
                intent.putExtra("Start", start);
                intent.putExtra("End",end);
                startActivity(intent);
            }
        });
    }
}