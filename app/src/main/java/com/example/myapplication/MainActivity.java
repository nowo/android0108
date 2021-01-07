package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void  buSave(View view){
        EditText etUserName = (EditText)findViewById(R.id.name);
        EditText etPassword = (EditText)findViewById(R.id.password);
        ContentValues values = new ContentValues();
        values.put(DBManager.ColUsername,etUserName.getText().toString() );
        values.put(DBManager.ColPassword,etPassword.getText().toString());
        long id = dbManager.Insert(values);
        if (id >0)
            Toast.makeText(getApplicationContext(),"Data is added and user id :  "+id,Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"cannot insert  ",Toast.LENGTH_LONG).show();
    }

    public void buLoad(View view){
        Cursor cursor = dbManager.query(null,null,null,DBManager.ColUsername);
        if(cursor.moveToFirst()){
            String tableData="";
            do{
                tableData+=cursor.getColumnIndex(DBManager.ColUsername)+","+cursor.getColumnIndex(DBManager.ColPassword)+"::";
            }
            while (cursor.moveToNext());
            Toast.makeText(getApplicationContext(),tableData,Toast.LENGTH_LONG).show();
        }
    }

}
