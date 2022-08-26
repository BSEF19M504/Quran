package com.example.quran;

public class Ayat {
    private int ayatId;
    private int surahId;
    private String arabic;
    private String translate;
    private int parahId;

    public int getAyatId() {
        return ayatId;
    }

    public void setAyatId(int ayatId) {
        this.ayatId = ayatId;
    }

    public int getSurahId() {
        return surahId;
    }

    public void setSurahId(int surahId) {
        this.surahId = surahId;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getParahId() {
        return parahId;
    }

    public void setParahId(int parahId) {
        this.parahId = parahId;
    }
}
