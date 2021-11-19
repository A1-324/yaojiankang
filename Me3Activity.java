package com.example.yaojiankang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Me3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me3);
        Intent intent = this.getIntent();
        String password = intent.getStringExtra("password");
        Toast.makeText(Me3Activity.this, "你的密码是"+password+",点击编辑密码", Toast.LENGTH_SHORT).show();
    }

    public void click5(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("pass","修改成功");
        setResult(444,intent2);
        finish();
    }
}
