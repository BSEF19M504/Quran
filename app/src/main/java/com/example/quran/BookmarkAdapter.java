package com.example.quran;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookmarkAdapter extends ArrayAdapter {

    Intent intent;
    public BookmarkAdapter(@NonNull Context context, int resource, @NonNull List objects, Intent intent) {
        super(context, resource, objects);
        this.intent = intent;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ayat ayat = (Ayat) getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ayat_layout,null,true);

        TextView textView1 = convertView.findViewById(R.id.textView);
        textView1.setText(ayat.getTranslateUrdu());

        TextView textView2 = convertView.findViewById(R.id.textView2);
        textView2.setText(ayat.getArabic());

        TextView textView3 = convertView.findViewById(R.id.textView3);

        CheckBox checkBox = convertView.findViewById(R.id.star);
        checkBox.setChecked(ayat.isBookmark());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                QuranDAO quranDAO = new QuranDAO(getContext());
                if(b){
                    quranDAO.setBookmark(ayat.getSurahId(),ayat.getAyatNo());
                }
                else{
                    quranDAO.unsetBookmark(ayat.getSurahId(),ayat.getAyatNo());
                }
            }
        });

        String num;
        if(ayat.getSurahId() == 1)
            num = Integer.toString(ayat.getAyatNo()-1);
        else
            num = Integer.toString(ayat.getAyatNo());
        textView3.setText(num);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = ayat.getSurahId();
                QuranDAO quranDAO = new QuranDAO(getContext());
                SurahNames surah = quranDAO.getSurahById(id);

                intent.putExtra("SurahId",surah.getId());
                intent.putExtra("NameEng",surah.getEng());
                intent.putExtra("NameUrdu",surah.getUrdu());
                intent.putExtra("key","Surah");
                if(surah.getId() == 1)
                    intent.putExtra("StaringIndex", ayat.getAyatNo()-1);
                else
                    intent.putExtra("StaringIndex", ayat.getAyatNo());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
