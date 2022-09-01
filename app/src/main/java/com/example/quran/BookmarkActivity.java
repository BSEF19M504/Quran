package com.example.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    ListView listView;
    BookmarkAdapter bookmarkAdapter;
    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Ayat> ayatArrayList = new QuranDAO(getApplicationContext()).getAyatByBookmark();
        if(ayatArrayList.isEmpty()){
            listView.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,new String[] {"No bookmarks saved!"}));
        }
        else{
            listView.setAdapter(new BookmarkAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,ayatArrayList));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        QuranDAO quranDAO = new QuranDAO(this);
        Intent intent = getIntent();
        int translateEng = intent.getIntExtra("translateEng",0);
        int translateUrdu = intent.getIntExtra("translateUrdu",0);
        List<Ayat> array;
        array = quranDAO.getAyatByBookmark();

        listView = findViewById(R.id.listBookmarks);
        bookmarkAdapter = new BookmarkAdapter(BookmarkActivity.this, android.R.layout.simple_list_item_1,array);
        listView.setAdapter(bookmarkAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ayat ayat = (Ayat) adapterView.getItemAtPosition(i);
                int id = ayat.getSurahId();
                SurahNames surah = quranDAO.getSurahById(id);

                Intent intent = new Intent(BookmarkActivity.this, VerseActivity.class);
                intent.putExtra("SurahId",surah.getId());
                intent.putExtra("NameEng",surah.getEng());
                intent.putExtra("NameUrdu",surah.getUrdu());
                intent.putExtra("translateEng",translateEng);
                intent.putExtra("translateUrdu",translateUrdu);
                intent.putExtra("key","Surah");
                if(surah.getId() == 1)
                    intent.putExtra("StaringIndex", ayat.getAyatNo()-1);
                else
                    intent.putExtra("StaringIndex", ayat.getAyatNo());
                startActivity(intent);
            }
        });

    }
}