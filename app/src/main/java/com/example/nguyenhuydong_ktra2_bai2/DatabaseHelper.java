package com.example.nguyenhuydong_ktra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Lichthi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE thi (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT, " +
                " day TEXT, " +
                " time TEXT, " +
                " isWrite INTEGER );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Test> getAllTest(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Test> list = new ArrayList<>();
        Cursor cursor = db.query("thi", null, null, null, null, null, null);
        while(cursor != null && cursor.moveToNext()){
            Test test = new Test(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1? true : false
            );
            list.add(test);
        }
        return list;
    }

    public void update(Test test){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", test.getName());
        contentValues.put("day", test.getDate());
        contentValues.put("time", test.getTime());
        contentValues.put("isWrite", test.isWrite() ? 1:0);
        String[] args = {test.getId() + ""};
        db.update("thi", contentValues, "id = ?", args);
    }

    public void insert(Test test){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", test.getName());
        contentValues.put("day", test.getDate());
        contentValues.put("time", test.getTime());
        contentValues.put("isWrite", test.isWrite() ? 1:0);

        db.insert("thi", null, contentValues);
    }

    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {id + ""};
        db.delete("thi", "id = ?", args);
    }

    public ArrayList<Test> search(String name){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Test> list = new ArrayList<>();
        String[] args = {"%" + name + "%"};
        Cursor cursor = db.query("thi", null, "name LIKE ?", args, null, null, null);
        while(cursor != null && cursor.moveToNext()){
            Test test = new Test(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4) == 1? true : false
            );
            list.add(test);
        }
        return list;
    }
}
