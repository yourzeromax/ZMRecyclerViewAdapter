package com.yourzeromax.zmrecyclerviewadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this,NormalAdapterActivity.class));
                break;
            case R.id.btn_2:
                startActivity(new Intent(MainActivity.this,MultiAdapterActivity.class));
                break;
            case R.id.btn_3:
                startActivity(new Intent(MainActivity.this,HeaderFooterActivity.class));
                break;
        }
    }
}
