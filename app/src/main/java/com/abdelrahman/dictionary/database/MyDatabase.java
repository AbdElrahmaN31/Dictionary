package com.abdelrahman.dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abdelrahman.dictionary.model.Category;
import com.abdelrahman.dictionary.model.Word;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String TAG = "Database";
    private static final String DATABASE_NAME = "data.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CATEGORIES = "categories";
    private static final String KEY_CATEGORY_CATEGORY_ID = "category_id";
    private static final String KEY_CATEGORY_CATEGORY_NAME = "category_name";
    private static final String TABLE_DICTIONARY = "dictionary";
    private static final String KEY_DICTIONARY_ID = "_id";
    private static final String KEY_DICTIONARY_ARABIC = "arabic";
    private static final String KEY_DICTIONARY_PRONUNCIATION = "pronunciation";
    private static final String KEY_DICTIONARY_CHINESE = "chinese";
    private static final String KEY_DICTIONARY_CATEGORY_ID = "category_id";
    private static final String KEY_DICTIONARY_FAVOUR = "favor";

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Category> getCategories(){
        List<Category> categoryList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Category category = new Category(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                // Adding category to list
                categoryList.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categoryList;
    }

    public List<Word> getCategory(int categoryID) {

        List<Word> wordList = new ArrayList<Word>();
        String selectQuery = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + KEY_DICTIONARY_CATEGORY_ID + "=" + categoryID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                );
                // Adding word to list
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wordList;
    }

    public List<Word> getFavouriteWords() {
        List<Word> wordList = new ArrayList<Word>();
        String selectQuery = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + KEY_DICTIONARY_FAVOUR + "=" + 1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                );
                // Adding word to list
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wordList;
    }

    public void favorWord(int id){
        ContentValues cv = new ContentValues();
        cv.put(KEY_DICTIONARY_FAVOUR,1);

        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("UPDATE" + TABLE_DICTIONARY + "SET" + KEY_DICTIONARY_FAVOUR + "=" + 1 + "WHERE" + KEY_DICTIONARY_ID + "=" + id,null);
        db.update(TABLE_DICTIONARY,cv,KEY_DICTIONARY_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void unFavorWord(int id){
        ContentValues cv = new ContentValues();
        cv.put(KEY_DICTIONARY_FAVOUR,0);

        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("UPDATE" + TABLE_DICTIONARY  + "SET" + KEY_DICTIONARY_FAVOUR + "=" + 0 + "WHERE" + KEY_DICTIONARY_ID + "=" + id,null);
        db.update(TABLE_DICTIONARY,cv,KEY_DICTIONARY_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Word> search(int searchTerm) {

        List<Word> wordList = new ArrayList<Word>();
        String selectQuery = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + KEY_DICTIONARY_ARABIC + "LIKE" + "%" +searchTerm + "%" +
                "OR" + KEY_DICTIONARY_CHINESE + "LIKE" + "%" +searchTerm + "%";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Word word = new Word(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
                );
                // Adding word to list
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wordList;
    }

    public Word getWord(int id) {
        String selectQuery = "SELECT * FROM " + TABLE_DICTIONARY + " WHERE " + KEY_DICTIONARY_ID + "=" + id;
        Word word = null;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
               word = new Word(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)
               );
            }
            cursor.close();
            db.close();
        }
        return word;
    }

}
