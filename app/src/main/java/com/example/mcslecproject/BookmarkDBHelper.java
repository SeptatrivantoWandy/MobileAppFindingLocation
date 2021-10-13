package com.example.mcslecproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookmarkDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BookmarkDataDB.db";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tblbookmarkdata";
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_USER_ID = "td_user_id";
    public static final String TABLE_COLUMN_ATTRACTION_ID = "td_attraction_id";

    List<BookmarkData> BD = new ArrayList<>();

    public BookmarkDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertBD(BookmarkData new_BD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put(TABLE_COLUMN_ID, new_BD.getBookmarkId());
        cv.put(TABLE_COLUMN_USER_ID, new_BD.getBookmarkUserId());
        cv.put(TABLE_COLUMN_ATTRACTION_ID, new_BD.getBookmarkAttractionId());



        db.insert(TABLE_NAME, null, cv);
    }

    Cursor readAllBookmarkData () {
        SQLiteDatabase db = this.getReadableDatabase();
        String BD_query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;

        if (db == null){
//            Toast.makeText((Context) cursor, "no data", Toast.LENGTH_SHORT).show();
        }

        if (db != null) {
            cursor = db.rawQuery(BD_query, null);
        }

        return cursor;
    }

    public boolean updateBD (BookmarkData update_BD) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TABLE_COLUMN_ID, update_BD.getBookmarkId());
        cv.put(TABLE_COLUMN_USER_ID, update_BD.getBookmarkUserId());
        cv.put(TABLE_COLUMN_ATTRACTION_ID, update_BD.getBookmarkAttractionId());


        db.update(
                TABLE_NAME,
                cv,
                "id = ?",
                new String [] { update_BD.getBookmarkId() } );

        return true;
    }

    public int deleteBD(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(
                TABLE_NAME,
                "id=?",
                new String[]{ String.valueOf(id) } );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_query = "CREATE TABLE " + TABLE_NAME + " (" + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_COLUMN_USER_ID + " TEXT NOT NULL, " + TABLE_COLUMN_ATTRACTION_ID + " TEXT NOT NULL)";

        db.execSQL(create_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
