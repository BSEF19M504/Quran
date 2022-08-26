package com.example.quran;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuranDAO extends SQLiteOpenHelper {
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_ROLL = "StudentRollNumber";
    public static final String STUDENT_ENROLL = "IsEnrolled";
    public static final String STUDENT_TABLE = "StudentTable";

    public static final String SURAH_TABLE = "tsurah";
    public static final String AYAT_TABLE = "tayah";
    public static final String AYAT_ID = "AyaID";
    public static final String PARAH_ID = "ParaID";
    public static final String SURAH_ID = "SuraID";
    public static final String ARABIC = "ArabicText";
    public static final String [] TRANSLATE = {"FatehMuhammadJalandhri","MehmoodulHassan","DrMohsinKhan","MuftiTaqiUsmani"};


    public QuranDAO(@Nullable Context context) {
        super(context, "Quran.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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