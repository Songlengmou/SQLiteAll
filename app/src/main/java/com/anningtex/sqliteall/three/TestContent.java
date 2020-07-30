package com.anningtex.sqliteall.three;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.anningtex.sqliteall.sql.ThreeDBHelper;

/**
 * @author Adim
 */

public class TestContent extends ContentProvider {
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        ThreeDBHelper sqlu = new ThreeDBHelper(getContext());
        db = sqlu.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor c;
        c = db.query("testDb", new String[]{"test_name"}, selection, selectionArgs, null, null, null);
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
