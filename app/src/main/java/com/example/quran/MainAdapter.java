package com.example.quran;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MainAdapter extends ArrayAdapter {
        public MainAdapter(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            SurahNames chapter = (SurahNames) getItem(position);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.surah_layout,null,true);

            TextView textView1 = convertView.findViewById(R.id.textView);
            textView1.setText(chapter.getEng());
            TextView textView2 = convertView.findViewById(R.id.textView2);
            textView2.setText(chapter.getUrdu());
            TextView textView3 = convertView.findViewById(R.id.textView3);
            String num = Integer.toString(chapter.getId());
            textView3.setText(num);

            textView2.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/noorehuda.ttf"));
            textView1.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Jameel Noori Nastaleeq.ttf"));

            return convertView;
        }

}
