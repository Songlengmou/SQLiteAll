package com.anningtex.sqliteall.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @Author Song
 * @Desc:
 */
public class OneDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "one.db";
    public static final String TABLE_NAME = "t_person";
    public static final int DB_VERSION = 1;

    public static final String NAME = "name";
    public static final String AGE = "age";

    public OneDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
        String sql = "create table " +
                TABLE_NAME +
                "(_id integer primary key autoincrement, " +
                NAME + " varchar, " +
                AGE + " varchar"
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
