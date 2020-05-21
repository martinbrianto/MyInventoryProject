package com.example.myapplication.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.AddItemMenu;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "dbitems.db";
    public static final String TABLE_NAME = "items_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "QTY";
    public static final String COL4 = "DESCI";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " NAME TEXT NOT NULL, " + " QTY INTEGER NOT NULL, " + " DESCI TEXT NOT NULL) ";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String name, int qty, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, qty);
        contentValues.put(COL4, desc);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL1 + " FROM " + TABLE_NAME+
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateItem(int id, String newName, String newDesc, int newQty){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+ TABLE_NAME + " SET " +
                COL2 + " = " + "'" +newName + "'" + ","
                + COL3 + " = "  + newQty  +","
                + COL4 + " = " + "'" + newDesc +"'"
                + " WHERE " + COL1 + " = " + "'" + id + "'";
        Log.d("111", "update: "+query);
        Log.d("111", "new name: "+newName);
        db.execSQL(query);
    }
}
