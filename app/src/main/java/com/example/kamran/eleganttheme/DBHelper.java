package com.example.kamran.eleganttheme;

/**
 * Created by samcholo on 4/10/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

//extends SQLiteOpenHelper

public class DBHelper extends SQLiteOpenHelper{

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "farm.db";
    public static String TABLE_BOOK = "Users";
    public static String COL_NAME = "username";
    public static String COL_NAME_2 = "password";
    public static String COL_NAME_3 = "email";
    public static String COL_NAME_4 = "phone";
    public static String COL_ID = "_id";
    public static String TABLE_IMAGES = "Book_Images";
    public static String COL_IMAGE = "image_name";
    public static String COL_IMAGE_ID = "_id";
    public static String COL_BOOK_ID = "book_id";

    private SQLiteDatabase sqliteDatabase;
    private SQLiteOpenHelper sqliteHelper;
    private Context mContext;

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public void close() {
        sqliteHelper.close();
    }

    public int deleteAll() {
        return sqliteDatabase.delete(TABLE_BOOK, null, null);
    }

    public void insertData(String tblname, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblname, null, contentValues );

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS" + TABLE_BOOK + " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT, " + COL_NAME_2 + " TEXT, " + COL_NAME_3 + " TEXT, " + COL_NAME_4 + " TEXT )");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_IMAGES + " ( " + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_BOOK_ID + " INTEGER, " +  COL_IMAGE + " TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE if exists " + TABLE_BOOK );
//        db.execSQL("DROP TABLE if exists " + TABLE_IMAGES );
//        onCreate(db);
    }

//    public DBHelper openToRead() throws SQLException {
//        try {
//            sqliteHelper = new SQLiteOpenHelper(mContext, DATABASE_NAME, null, 1);
//
//        }
//    }

    public void deleteData(String tblname, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblname, clause, new String[]{Integer.toString(_id)});
        db.close();
    }


    public Cursor getAllEntries(String tblname, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int checkUP(String tblname, String Username, String Password) {
        int i = 0;
        SQLiteDatabase db = getReadableDatabase();
        String[] columnNames = {"username", "password"};
        Cursor cursor = db.query(tblname, columnNames, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while(cursor.moveToNext()) {
            String c1 = cursor.getString(0);
            String c2 = cursor.getString(1);
            Log.d("HELP", cursor.getString(0));
            if(c1 == Username) {
                if(c2 == Password) {
                    i = 1;
                }
                else {
                    Toast.makeText(mContext, "Incorrect Password", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(mContext, "Incorrect Password", Toast.LENGTH_LONG).show();
            }
        }
        return i;
    }

    public Cursor getSelectEntries(String tblname, String[] columns, String where, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, where, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblname, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }

}
