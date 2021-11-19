package com.example.yaojiankang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Me1Activity extends AppCompatActivity {

    private Button mFinish;
    private Button mBtnCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me1);
        mBtnCheckBox = findViewById(R.id.btn_checkbox);
        mFinish = findViewById(R.id.finish);
        Intent intent = this.getIntent();
        String name = intent.getStringExtra("UserName");
        Toast.makeText(Me1Activity.this, "你的名字是"+name+"，点击编辑姓名", Toast.LENGTH_SHORT).show();
        setListeners();
    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnCheckBox.setOnClickListener(onClick);

    }

    private class OnClick implements View.OnClickListener{

        public void onClick(View v){
            Intent intent = null;
            switch(v.getId()){
                case R.id.finish:
                    //跳转到个人演示界面
                    intent = new Intent(Me1Activity.this,MeActivity.class);
                    break;

                case R.id.btn_checkbox:
                    //跳转到CheckButton演示界面
                    break;
            }
        }

    }

    public void click1(View view) {
        //数据回传
        //创建回传用的意图
        Intent intent2 = new Intent();
        intent2.putExtra("name","修改成功");
        //将intent2作为回传数据的载体，将他设置为result,编码为888
        setResult(888,intent2);
        //关闭当前的activity
        finish();
    }
}
