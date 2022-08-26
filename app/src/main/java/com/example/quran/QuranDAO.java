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

public class QuranDAO extends SQLiteOpenHelper {
    public static final String SURAH_TABLE = "tsurah";
    public static final String PARAH_TABLE = "tparah";
    public static final String AYAT_TABLE = "tayah";
    public static final String AYAT_ID = "AyaID";
    public static final String PARAH_ID = "ParaID";
    public static final String SURAH_ID = "SuraID";
    public static final String ARABIC = "ArabicText";
    public static final String [] TRANSLATE = {"FatehMuhammadJalandhri","MehmoodulHassan","DrMohsinKhan","MuftiTaqiUsmani"};

    //The Android's default system path of your application database.
    private static final String DB_PATH = "/data/data/com.example.quran/databases/";
    // Data Base Name.
    private static final String DATABASE_NAME = "Quran.db";
    // Data Base Version.
    private static final int DATABASE_VERSION = 1;
    // Table Names of Data Base.
    static final String TABLE_Name = "tableName";

    public Context context;
    static SQLiteDatabase sqliteDataBase;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     * Parameters of super() are    1. Context
     *                              2. Data Base Name.
     *                              3. Cursor Factory.
     *                              4. Data Base Version.
     */
    public QuranDAO(Context context) {
        super(context, DATABASE_NAME, null ,DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * By calling this method and empty database will be created into the default system path
     * of your application so we are gonna be able to overwrite that database with our database.
     * */
    public void createDataBase() throws IOException{
        //check if the database exists
        boolean databaseExist = checkDataBase();

        if(!databaseExist){
            this.getWritableDatabase();
            copyDataBase();
        }// end if else dbExist
    } // end createDataBase().

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    public boolean checkDataBase(){
        File databaseFile = new File(DB_PATH + DATABASE_NAME);
        return databaseFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring byte stream.
     * */
    private void copyDataBase() throws IOException{
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    /**
     * This method opens the data base connection.
     * First it create the path up till data base of the device.
     * Then create connection with data base.
     */
    public void openDataBase() throws SQLException{
        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        sqliteDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * This Method is used to close the data base connection.
     */
    @Override
    public synchronized void close() {
        if(sqliteDataBase != null)
            sqliteDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

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