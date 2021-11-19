package com.example.yaojiankang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Me2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me2);
        Intent intent = this.getIntent();
        String phoneNumber = intent.getStringExtra("phoneNumber");
        Toast.makeText(Me2Activity.this, "你的电话是"+phoneNumber+",点击编辑电话", Toast.LENGTH_SHORT).show();
    }

    public void click3(View view) {
        Intent intent2 = new Intent();
        intent2.putExtra("phone","修改成功");
        setResult(222,intent2);
        finish();

    }
}
