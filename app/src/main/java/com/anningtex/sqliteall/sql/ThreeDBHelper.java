package com.anningtex.sqliteall.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Adim
 */

public class ThreeDBHelper extends SQLiteOpenHelper {
    public static final String NAME = "testDb";
    private static final int VERSION = 1;

    public ThreeDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table testdb (" +
                "test_id INTEGER primary key autoincrement" +
                ",test_name TEXT" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
