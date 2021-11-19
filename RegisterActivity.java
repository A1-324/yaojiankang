package com.example.yaojiankang;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yaojiankang.database.MyDatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText reg_username;
    private EditText reg_password;
    private EditText reg_password1;
    private EditText reg_phone;
    private EditText reg_phone1;
    private Button reg_btn_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        reg_username = findViewById(R.id.Edit_username);
        reg_password = findViewById(R.id.Edit_password);
        reg_password1 = findViewById(R.id.Edit_passward1);
        reg_phone = findViewById(R.id.Edit_phone);
        reg_phone1 = findViewById(R.id.Edit_phone1);
        reg_btn_sure = findViewById(R.id.btn_sure);

        reg_btn_sure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = reg_username.getText().toString().trim();
                String password = reg_password.getText().toString().trim();
                String password1 = reg_password1.getText().toString().trim();
                String phone = reg_phone.getText().toString().trim();
                String phone1 = reg_phone1.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(phone1)) {
                    Toast.makeText(RegisterActivity.this, "各项均不能为空", Toast.LENGTH_SHORT).show();
                } else if (reg_phone.getText().toString().trim().length() != 11 || reg_phone1.getText().toString().trim().length() != 11) {
                    Toast.makeText(RegisterActivity.this, "您的电话号码位数不正确", Toast.LENGTH_LONG).show();
                    reg_phone.requestFocus();
                    reg_phone1.requestFocus();
                } else {
                    MyDatabaseHelper helper = new MyDatabaseHelper(RegisterActivity.this, "User.db", null, 1);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    //根据画面上输入的账号去数据库中进行查询
                    Cursor c = db.query("User", null, "name=?", new String[]{username}, null, null, null);
                    //如果有查询到数据，则说明账号已存在
                    if (c != null && c.getCount() >= 1) {
                        Toast.makeText(RegisterActivity.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                        c.close();
                    }
                    //如果没有查询到数据，则往数据库中insert一笔数据
                    else {
                        if (TextUtils.equals(password, password1)) {
                            //insert data
                            ContentValues values = new ContentValues();
                            values.put("name", username);
                            values.put("password", password);
                            values.put("phone", phone);
                            values.put("emergency_phone", phone1);
                            long rowid = db.insert("User", null, values);
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();//提示信息
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    }
                }
            }
        });
    }
}
