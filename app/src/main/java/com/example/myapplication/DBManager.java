package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBManager {
    private SQLiteDatabase sqlDB;
    static final String DBName = "students";
    static final String TableName = "Logins";
    static final String ColUsername = "username";
    static final String ColPassword = "Password";


    static  final String CreateTable = "create table " +TableName + "(ID integer PRIMARY KEY AUTOINCREMENT,"+ColUsername + " text,"+ ColPassword + "text)";

    static class DatabaseHelperUser extends SQLiteOpenHelper{

        Context context;

        public DatabaseHelperUser(@Nullable Context context) {
            super(context, DBName, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreateTable);
            Toast.makeText(context,"table created", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("Drop table IF EXISTS "+TableName);

        }
    }

    public DBManager (Context context){
        DatabaseHelperUser db = new DatabaseHelperUser(context);
        sqlDB=db.getWritableDatabase();
    }
    public long Insert(ContentValues values){
        long ID = sqlDB.insert(TableName,"",values);
        return ID;
    }

    public Cursor query(String[] Projection, String Selection, String[] SelectionArgs, String SortOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TableName);
        Cursor cursor = qb.query(sqlDB,Projection,Selection,SelectionArgs,null,null,SortOrder);
        return cursor;
    }

}



