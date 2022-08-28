package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AyatAdapter extends ArrayAdapter {
    public AyatAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ayat ayat = (Ayat) getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ayat_layout,null,true);

        TextView textView1 = convertView.findViewById(R.id.textView);
        textView1.setText(ayat.getArabic());
        TextView textView2 = convertView.findViewById(R.id.textView2);
        textView2.setText(ayat.getTranslate());
        TextView textView3 = convertView.findViewById(R.id.textView3);
        String num;
        if(ayat.getSurahId() == 1)
            num = Integer.toString(ayat.getAyatNo()-1);
        else
            num = Integer.toString(ayat.getAyatNo());
        textView3.setText(num);

        if(ayat.getAyatNo() == 0)
            textView3.setVisibility(View.INVISIBLE);

        return convertView;
    }
}
