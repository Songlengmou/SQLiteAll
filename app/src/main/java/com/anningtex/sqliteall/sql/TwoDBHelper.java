package com.anningtex.sqliteall.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @Author Song
 * @Desc:
 */
public class TwoDBHelper extends SQLiteOpenHelper {

    public TwoDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS person ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name VARCHAR(20),"
                + "age SMALLINT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //删除数据表
        db.execSQL("DROP TABLE IF EXISTS person");
        //重新建表
        onCreate(db);
    }
}
