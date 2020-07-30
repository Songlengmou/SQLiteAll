package com.anningtex.sqliteall.three;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.anningtex.sqliteall.sql.ThreeDBHelper;

import java.util.HashMap;

/**
 * @author Adim
 */
public class SQLMager {
    private static Context context;
    private static SQLiteDatabase db;
    private static SQLMager sqlm;

    private HashMap<String, ThreeDataBean> mao = new HashMap<>();

    private SQLMager(Context context) {
        SQLMager.context = context;
    }

    public static synchronized SQLMager getInstance(Context context) {
        if (db == null || !db.isOpen()) {
            sqlm = new SQLMager(context);
            openSQL();
        }
        return sqlm;
    }

    public void initData() {
        Cursor c = db.query(ThreeDBHelper.NAME
                , null
                , null
                , null
                , null, null, "test_id DESC");
        while (c.moveToNext()) {
            String str = c.getString(c.getColumnIndex("test_name"));
            Log.e("getAll", " " + str);
            ThreeDataBean p = new ThreeDataBean();
            mao.put("", p);
        }
    }

    public static void openSQL() {
        ThreeDBHelper sqlu = new ThreeDBHelper(context);
        db = sqlu.getWritableDatabase();
    }

    /**
     * 增
     */
    public void add(ThreeDataBean a) {
        ContentValues cv = new ContentValues();
        cv.put("test_name", a.getName());
        long as = db.insert(ThreeDBHelper.NAME, null, cv);

        if (as > 0) {
            mao.put("", a);
        }

        db.execSQL("insert into testdb(test_name) values(?);", new Object[]{a.getName()});
    }

    /**
     * 删
     */
    public void del(String name) {
        db.delete(ThreeDBHelper.NAME, "test_name=?", new String[]{name});
    }

    /**
     * 改
     */
    public void up(ThreeDataBean a, String str) {
        ContentValues cv = new ContentValues();
        cv.put("test_name", a.getName());
        db.update(ThreeDBHelper.NAME, cv, "test_name=?", new String[]{str});
    }

    /**
     * 查询所有
     */
    public void getAll() {
        Cursor c = db.query(ThreeDBHelper.NAME
                , new String[]{"test_name"}
                , "test_id>?"
                , new String[]{"1"}
                , "test_name", null, "test_id DESC");
        while (c.moveToNext()) {
            String str = c.getString(c.getColumnIndex("test_name"));
            Log.e("getAll", " " + str);
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
