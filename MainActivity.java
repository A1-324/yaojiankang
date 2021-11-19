package com.example.yaojiankang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yaojiankang.database.Database;
import com.example.yaojiankang.database.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private ImageView mIv4;
    private EditText mEditText_username;
    private EditText mEditText_password;
    private Button mButton_login;
    private Button mButton_enroll;
    private Button mButton_find_psw;
    private String str_username;
    private CheckBox checkbox;
    private String str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mEditText_username = findViewById(R.id.Edit_username);
        mEditText_password = findViewById(R.id.Edit_password);
        mButton_login = findViewById(R.id.btn_login);
        mButton_enroll = findViewById(R.id.btn_enroll);
        mIv4 = findViewById(R.id.iv_4);
        mButton_find_psw = findViewById(R.id.creat_database);
        mButton_login.setOnClickListener(new MyButton());
        mButton_enroll.setOnClickListener(new MyButton());
        mButton_find_psw.setOnClickListener(new MyButton());
        checkbox = findViewById(R.id.checkBox);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"User.db",null,1);
        Button createDatabase = findViewById(R.id.creat_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
    }

    public class MyButton implements View.OnClickListener{
            public void onClick(View v) {
                str_username = mEditText_username.getText().toString().trim();
                str_password = mEditText_password.getText().toString();

                switch (v.getId()) {
                    //当点击登录按钮时
                    case R.id.btn_login:
                        if (TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_password)) {
                            Toast.makeText(MainActivity.this, "密码或账号不能为空", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //根据画面上输入的账号/密码去数据库中进行查询（User是表名）
                            SQLiteDatabase sdb=dbHelper.getReadableDatabase();
                            String sql="select * from User where name=? and password=?";
                            Cursor c=sdb.rawQuery(sql, new String[]{str_username,str_password});

                            //如果有查询到数据
                            if(c!=null && c.getCount() >= 1){
                                //可以把查询出来的值打印出来在后台显示/查看
                                /*String[] cols = c.getColumnNames();
                                while(c.moveToNext()){
                                   for(String ColumnName:cols){
                                        Log.i("info",ColumnName+":"+c.getString(c.getColumnIndex(ColumnName)));                                 }
                                   }*/
                                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, Calculator.class);
                                c.close();
                                sdb.close();
                            }
                            //如果没有查询到数据
                            else{
                                Toast.makeText(MainActivity.this, "账号或密码输入错误！", Toast.LENGTH_SHORT).show();
                                }
                        }
                        break;
                    //当点击注册按钮事件时
                    case R.id.btn_enroll:
                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        break;

                    //当点击数据库按钮事件时
                    case R.id.creat_database:
                        Intent intent1 = new Intent(MainActivity.this, Database.class);
                        startActivity(intent1);
                        break;
                }
            }
    }
}