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
        super(context, "QuranDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}