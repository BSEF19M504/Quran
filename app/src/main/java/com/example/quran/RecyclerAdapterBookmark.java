package com.example.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterBookmark extends RecyclerView.Adapter<RecyclerAdapterBookmark.BookmarkViewHolder> {
    List<Ayat> ayatList;
    Context context;

    public RecyclerAdapterBookmark(Context context, List<Ayat> ayatList){
        this.context = context;
        this.ayatList = ayatList;
    }

    @NonNull
    @Override
    public RecyclerAdapterBookmark.BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ayat_layout, parent, false);
        return new RecyclerAdapterBookmark.BookmarkViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterBookmark.BookmarkViewHolder holder, int position) {
        holder.data = ayatList.get(position);

        holder.textView1.setText(holder.data.getTranslateUrdu());

        holder.textView2.setText(holder.data.getArabic());

        holder.checkBox.setChecked(holder.data.isBookmark());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                QuranDAO quranDAO = new QuranDAO(context);
                if(b){
                    quranDAO.setBookmark(holder.data.getSurahId(),holder.data.getAyatNo());
                }
                else{
                    quranDAO.unsetBookmark(holder.data.getSurahId(),holder.data.getAyatNo());
                }
            }
        });

        String num;
        if(holder.data.getSurahId() == 1)
            num = Integer.toString(holder.data.getAyatNo()-1);
        else
            num = Integer.toString(holder.data.getAyatNo());
        holder.textView3.setText(num);
    }

    @Override
    public int getItemCount() {
        return ayatList.size();
    }

    public class BookmarkViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        CheckBox checkBox;
        Ayat data;

        public BookmarkViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            checkBox = itemView.findViewById(R.id.star);
        }
    }
}
