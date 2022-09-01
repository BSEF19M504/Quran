package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BookmarkAdapter extends ArrayAdapter {
    public BookmarkAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
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

        return convertView;
    }
}
