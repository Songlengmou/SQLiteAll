package com.anningtex.sqliteall.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Administrator
 */
public class FourDBHelper extends SQLiteOpenHelper {

    public FourDBHelper(Context context) {
        super(context, "Student", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Student(id integer primary key autoincrement," +
                "name varchar(20)," +
                "age integer(3)," +
                "phone long(11)," +
                "address varchar(50)," +
                "classes varchar(10))");
        Log.d("TAG", "创建了");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
