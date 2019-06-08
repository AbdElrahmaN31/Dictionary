package com.abdelrahman.dictionary.model;

public class Word {
    private String arabicWord,pronunciation, chinese;
    private int wordId,wordCategoryId,favour;

    public Word(int wordId, String arabicWord, String pronunciation, String chinese,int category_id,int favour) {
        this.wordId = wordId;
        this.arabicWord = arabicWord;
        this.pronunciation = pronunciation;
        this.chinese = chinese;
        this.wordCategoryId = category_id;
    }

    public Word(String arabicWord, String chinese) {
        this.arabicWord = arabicWord;
        this.chinese = chinese;
    }



    public String getArabicWord() {
        return arabicWord;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getChinese() {
        return chinese;
    }

    public int isFavour() {
        return favour;
    }

    public void setFavour() {
        this.favour = 1;
    }

    public void setUnFavour() {
        this.favour = 0;
    }
}
