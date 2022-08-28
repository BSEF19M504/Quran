package com.example.quran;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.*;
import java.util.ArrayList;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class QuranDAO extends SQLiteAssetHelper {
    public static final String SURAH_TABLE = "tsurah";
    public static final String PARAH_TABLE = "tparah";
    public static final String AYAT_TABLE = "tayah";
    public static final String AYAT_ID = "AyaID";
    public static final String PARAH_ID = "ParaID";
    public static final String SURAH_ID = "SuraID";
    public static final String ARABIC = "ArabicText";
    public static final String [] TRANSLATE = {"FatehMuhammadJalandhri","MehmoodulHassan","DrMohsinKhan","MuftiTaqiUsmani"};

    //The Android's default system path of your application database.
    private static final String DATABASE_NAME = "Quran.db";
    private static final int DATABASE_VERSION = 1;

    public QuranDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<SurahNames> getSurahNames() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorSurah = db.rawQuery("SELECT * FROM " + SURAH_TABLE, null);

        ArrayList<SurahNames> surahArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorSurah.moveToFirst()) {
            do {
                int nameE, nameU, id;
                nameU = cursorSurah.getColumnIndex("SurahNameU");
                nameE = cursorSurah.getColumnIndex("SurahNameE");
                id = cursorSurah.getColumnIndex("SurahID");
                String nameEng = cursorSurah.getString(nameE);
                nameEng = nameEng.split("\\(")[0];
                surahArrayList.add(new SurahNames(cursorSurah.getString(nameU),
                        nameEng,
                        cursorSurah.getInt(id)));
            } while (cursorSurah.moveToNext());

        }

        cursorSurah.close();
        return surahArrayList;
    }

    public ArrayList<SurahNames> getParahNames() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorParah = db.rawQuery("SELECT * FROM " + PARAH_TABLE, null);

        ArrayList<SurahNames> parahArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorParah.moveToFirst()) {
            do {
                int nameE, nameU, id;
                nameU = cursorParah.getColumnIndex("Name");
                nameE = cursorParah.getColumnIndex("Transliteration");
                String IDNum = cursorParah.getString(0);
                IDNum = IDNum.split(" ")[1];
                parahArrayList.add(new SurahNames(cursorParah.getString(nameU),
                        cursorParah.getString(nameE),
                        Integer.parseInt(IDNum)));
            } while (cursorParah.moveToNext());

        }

        cursorParah.close();
        return parahArrayList;
    }

    public ArrayList<String> getAyatBySurah(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorAyat;
        if(id == 9){
            cursorAyat = db.rawQuery("SELECT * FROM " + AYAT_TABLE + " WHERE " + SURAH_ID + "=" + id,null);
        }
        else {
            cursorAyat = db.rawQuery("SELECT * FROM " + AYAT_TABLE + " WHERE " + SURAH_ID + "=" + id + " OR " + AYAT_ID + "=1",null);
        }

        ArrayList<String> ayatArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorAyat.moveToFirst()) {
            do {
                int arabic;
                arabic = cursorAyat.getColumnIndex(ARABIC);
                ayatArrayList.add(cursorAyat.getString(arabic));
            } while (cursorAyat.moveToNext());

        }

        cursorAyat.close();
        return ayatArrayList;
    }
}