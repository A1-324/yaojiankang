package com.example.yaojiankang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FindActivity extends AppCompatActivity {

    private Button btnSubmit;
    private LinearLayout llSms;
    private LinearLayout llPassword;
    private TextView tvSms;
    private TextView tvPassword;
    private TextView Password;
    private TextView Password1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //设置布局
        setContentView( R.layout.findpwd );
        //获取控件 Ctrl+alt+F
        btnSubmit = findViewById( R.id.bt_find );
        llSms = findViewById( R.id.ll_login_sms);
        llPassword = findViewById( R.id.ll_login_password);
        tvSms = findViewById( R.id.phone);
        tvPassword = findViewById( R.id.yanzheng);
        Password = findViewById(R.id.user_pwd1);
        Password1 = findViewById(R.id.user_pwd2);

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String pwd= tvPassword.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String password1 = Password1.getText().toString().trim();
                String phone = tvSms.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(FindActivity.this, "各项均不能为空", Toast.LENGTH_SHORT).show();
                }else if (tvSms.getText().toString().trim().length() != 11) {
                    Toast.makeText(FindActivity.this, "您的电话号码位数不正确", Toast.LENGTH_LONG).show();
                    tvSms.requestFocus();
                } else {
                    if (TextUtils.equals(password, password1)) {
                        //执行找回密码操作
                        Toast.makeText(FindActivity.this, "已找回账号", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FindActivity.this, MainActivity.class);
                        startActivity(intent);
                        Intent data = new Intent();
                        setResult(RESULT_OK, data);
                        //RESULT_OK为Activity系统常量，状态码为-1，
                        // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    } else {
                        Toast.makeText(FindActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //设置控件的事件
        setViewListener();
    }

    /**
     * 设置控件的事件
     */

    private void setViewListener() {

        //文本—按钮 密码的登录 点击事件
        tvPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示密码登录输入框
                llPassword.setVisibility( View.VISIBLE );
                //显示文本—按钮 短信验证码登录
                tvSms.setVisibility( View.VISIBLE );
                //隐藏短信验证码输入框
                llSms.setVisibility( View.GONE );
                //隐藏文本—按钮 密码的登录
                tvPassword.setVisibility( View.GONE );
            }
        } );

        //文本—按钮 短信验证码登录 点击事件
        tvSms.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llPassword.setVisibility( View.GONE );
                tvSms.setVisibility( View.GONE );
                llSms.setVisibility( View.VISIBLE );
                tvPassword.setVisibility( View.VISIBLE );
            }
        } );
    }
}