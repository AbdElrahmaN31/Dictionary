package com.abdelrahman.dictionary.model;

public class Word {
    private String arabicWord,pronunciation, chineseWord;
    private int wordId,wordCategoryId,favour;

    public Word(int wordId, String arabicWord, String pronunciation, String chineseWord, int category_id, int favour) {
        this.wordId = wordId;
        this.arabicWord = arabicWord;
        this.pronunciation = pronunciation;
        this.chineseWord = chineseWord;
        this.wordCategoryId = category_id;
        this.favour = favour;
    }

    public int getWordId() {
        return wordId;
    }

    public int getWordCategoryId() {
        return wordCategoryId;
    }

    public String getArabicWord() {
        return arabicWord;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public String getChineseWord() {
        return chineseWord;
    }

    public boolean isFavour() {
        return  (this.favour == 1);
    }

    public void setFavour() {
        this.favour = 1;
    }

    public void setUnFavour() {
        this.favour = 0;
    }
}
