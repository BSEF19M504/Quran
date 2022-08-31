package com.example.quran;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterSurah extends RecyclerView.Adapter<RecyclerAdapterSurah.SurahViewHolder> {

    List<SurahNames> surahNamesList;
    Context context;
    int translateUrdu;
    int translateEng;
    String key;

    public RecyclerAdapterSurah(Context context, List<SurahNames> surahNamesList, int eng, int urdu, String key){
        this.surahNamesList = surahNamesList;
        this.context = context;
        translateEng = eng;
        translateUrdu = urdu;
        this.key = key;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.surah_layout, parent, false);
        return new SurahViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        holder.data = surahNamesList.get(position);

        holder.textView1.setText(holder.data.getEng());
        holder.textView2.setText(holder.data.getUrdu());
        String num = Integer.toString(holder.data.getId());
        holder.textView3.setText(num);

        holder.textView2.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/noorehuda.ttf"));
        holder.textView1.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Jameel Noori Nastaleeq.ttf"));
    }

    @Override
    public int getItemCount() {
        return surahNamesList.size();
    }

    public class SurahViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        SurahNames data;
        public SurahViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final SurahNames surah = surahNamesList.get(getAdapterPosition());
                    Toast.makeText(context, Integer.toString(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, VerseActivity.class);
                    intent.putExtra("SurahId",data.getId());
                    intent.putExtra("NameEng",data.getEng());
                    intent.putExtra("NameUrdu",data.getUrdu());
                    intent.putExtra("translateEng",translateEng);
                    intent.putExtra("translateUrdu",translateUrdu);
                    intent.putExtra("key",key);
                    context.startActivity(intent);
                }
            });
        }
    }
}
