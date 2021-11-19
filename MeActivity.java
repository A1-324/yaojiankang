package com.example.yaojiankang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me);

        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this, Me1Activity.class);
                intent.putExtra("UserName","李由");
                startActivity(intent);
            }
        });

        Button bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this,Me2Activity.class);
                intent.putExtra("phoneNumber","11788563790");
                startActivity(intent);
            }
        });

        Button bt3 = (Button) findViewById(R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MeActivity.this,Me3Activity.class);
                intent.putExtra("password","123456");
                startActivity(intent);
            }
        });
    }

    public void click(View view) {
        //创建一个意图
        Intent intent1 = new Intent(MeActivity.this,Me1Activity.class);
        //此时intent1就变为寻求result的一个意图，同时这个意图的请求码是666
        startActivityForResult(intent1,666);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==666&&resultCode==888){
            String msg = data.getStringExtra("name");
            Toast.makeText(MeActivity.this,msg,Toast.LENGTH_LONG).show();
        }
        if(requestCode==111&&resultCode==222){
            String msg = data.getStringExtra("phone");
            Toast.makeText(MeActivity.this,msg,Toast.LENGTH_LONG).show();
        }
        if(requestCode==333&&resultCode==444){
            String msg = data.getStringExtra("pass");
            Toast.makeText(MeActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }

    public void click2(View view) {
        Intent intent1 = new Intent();
        startActivityForResult(intent1,111);
    }

    public void click4(View view) {
        Intent intent1 = new Intent();
        startActivityForResult(intent1,333);
    }
}
