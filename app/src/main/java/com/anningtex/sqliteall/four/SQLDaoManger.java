package com.anningtex.sqliteall.four;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anningtex.sqliteall.sql.FourDBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class SQLDaoManger {
    private FourDBHelper my;

    public SQLDaoManger(Context context) {
        my = new FourDBHelper(context);
    }

    public long add(String name, Integer age, long phone, String address, String classes) {
        SQLiteDatabase SQdb = my.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("phone", phone);
        values.put("address", address);
        values.put("classes", classes);
        long insert = SQdb.insert("Student", null, values);
        return insert;
    }

    public int delete(int id) {
        SQLiteDatabase SQdb = my.getWritableDatabase();
        int delete = SQdb.delete("Student", "id=?", new String[]{String.valueOf(id)});
        return delete;
    }

    public int update(int id, String name, Integer age, long phone, String address, String classes) {
        SQLiteDatabase SQdb = my.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("phone", phone);
        values.put("address", address);
        values.put("classes", classes);
        int update = SQdb.update("Student", values, "id=?", new String[]{String.valueOf(id)});
        return update;
    }

    public String[] cha1(int id) {
        SQLiteDatabase SQdb = my.getWritableDatabase();
        Cursor cursor = SQdb.query("Student", null, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        String name = null;
        int age = 0;
        long phone = 0;
        String address = null;
        String classes = null;
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            age = Integer.valueOf(cursor.getString(cursor.getColumnIndex("age")));
            phone = Long.valueOf(cursor.getString(cursor.getColumnIndex("phone")));
            address = cursor.getString(cursor.getColumnIndex("address"));
            classes = cursor.getString(cursor.getColumnIndex("classes"));
        }
        String[] str = {name, String.valueOf(age), String.valueOf(phone), address, classes};
        return str;
    }

    public List<StudentBean> cha(String name) {
        SQLiteDatabase SQdb = my.getWritableDatabase();
        Cursor cursor = SQdb.query("Student", null, "name=?", new String[]{name}, null, null, null, null);
        List<StudentBean> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String classes = cursor.getString(cursor.getColumnIndex("classes"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            long phone = cursor.getLong(cursor.getColumnIndex("phone"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            StudentBean stu = new StudentBean(id, name1, age, phone, address, classes);
            list.add(stu);
        }
        return list;
    }

    public List<StudentBean> show() {
        SQLiteDatabase db = my.getReadableDatabase();
        List<StudentBean> list = new ArrayList<StudentBean>();
        Cursor cursor = db.query("Student", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String classes = cursor.getString(cursor.getColumnIndex("classes"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            long phone = cursor.getLong(cursor.getColumnIndex("phone"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            StudentBean stu = new StudentBean(id, name, age, phone, address, classes);
            list.add(stu);
        }
        return list;
    }
}
