package com.example.quran;

public class Ayat {
    private int ayatId;
    private int surahId;
    private String arabic;
    private String translate;
    private int parahId;
    private int ayatNo;

    public int getAyatId() {
        return ayatId;
    }

    public int getSurahId() {
        return surahId;
    }

    public String getArabic() {
        return arabic;
    }

    public String getTranslate() {
        return translate;
    }

    public int getParahId() {
        return parahId;
    }

    public int getAyatNo() {
        return ayatNo;
    }

    public Ayat(String arabic, String translate,int ayatNo, int surahId) {
        this.surahId = surahId;
        this.arabic = arabic;
        this.translate = translate;
        this.ayatNo = ayatNo;
    }
}
