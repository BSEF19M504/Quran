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
        DataObject dataObject = new DataObject();
        List<SurahNames> array = new ArrayList<>();
        for(int i=0; i< dataObject.englishSurahNames.length; i++){

            SurahNames temp = new SurahNames();
            temp.eng = dataObject.englishSurahNames[i];
            temp.urdu = dataObject.urduSurahNames[i];
            array.add(temp);

        }
        MainAdapter arrayAdapter = new MainAdapter(MainActivity.this, android.R.layout.simple_list_item_1,array);
        ListView listView = findViewById(R.id.listSurah);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int start = dataObject.getSurahStart(i);
                int end;
                try{
                    end = dataObject.getSurahStart(i+1);
                }catch (Exception e){
                    end = DataObject.QuranArabicText.length+1;
                }
                Intent intent = new Intent(MainActivity.this, VerseActivity.class);
                intent.putExtra("Start", start);
                intent.putExtra("End",end);
                startActivity(intent);
            }
        });
    }
}