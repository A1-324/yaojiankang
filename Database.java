package com.example.yaojiankang.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yaojiankang.R;

public class Database extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        //创建数据库
        dbHelper = new MyDatabaseHelper(this,"User.db",null,1);
        Button createDatabase = findViewById(R.id.creat_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });


        //添加数据
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name", "dgg");
                values.put("password", "1234");
                values.put("phone", "18710447916");
                values.put("emergency_phone", "18710447916");
                db.insert("User", null, values);//插入第一条数据
                values.clear();
                //开始组装第二条数据
                values.put("name", "hry");
                values.put("password", "1234");
                values.put("phone", "15191502590");
                values.put("emergency_phone", "15191502590");
                db.insert("User", null, values);//插入第二条数据
                values.clear();
            }
        });

        //更新数据
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("emergency_phone", "15191502590");
                db.update("User",values,"name=?",new String[]{"dgg"});
            }
        });

        //删除数据
        Button deletedata = findViewById(R.id.delete_data);
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("User","password<?",new String[]{"3"});
            }
        });

        //查询数据
        Button querydata = findViewById(R.id.query_data);
        querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询User表中所有的数据
                Cursor cursor = db.query("User",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do {
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String password = cursor.getString(cursor.getColumnIndex("password"));
                        int phone = cursor.getInt(cursor.getColumnIndex("phne"));
                        int emergency_phone = cursor.getInt(cursor.getColumnIndex("emergency_phone"));
                        Log.d("MainActiity","User name is " + name);
                        Log.d("MainActiity","User password is " + password);
                        Log.d("MainActiity","User phone is " + phone);
                        Log.d("MainActiity","User emergency_phone is " + emergency_phone);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
