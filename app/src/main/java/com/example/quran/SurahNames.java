package com.example.quran;

public class SurahNames {
    String urdu;
    String eng;
    int id;

    public SurahNames(String urdu, String eng, int id) {
        this.urdu = urdu;
        this.eng = eng;
        this.id = id;
    }

    public String getUrdu() {
        return urdu;
    }

    public String getEng() {
        return eng;
    }

    public int getId() {
        return id;
    }
}
