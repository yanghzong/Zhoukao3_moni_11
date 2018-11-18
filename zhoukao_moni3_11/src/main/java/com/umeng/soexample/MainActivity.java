package com.umeng.soexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abner.ming.MingUtils;
import com.abner.ming.ResultListener;
import com.abner.ming.UmengBean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edPassword;
    private EditText edUsername;
    private Button btnLogin;
    private ImageView imgQQ;
    private ImageView imgWei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        edPassword = findViewById(R.id.ed_password);
        edUsername = findViewById(R.id.ed_username);
        btnLogin = findViewById(R.id.btn_login);
        imgQQ = findViewById(R.id.img_qq);
        imgWei = findViewById(R.id.img_wei);
        imgQQ.setOnClickListener(this);
        imgWei.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);


                break;
            case R.id.img_qq:
                MingUtils.login(MainActivity.this, 0, new ResultListener() {
                    @Override
                    public void success(UmengBean umengBean) {


                    }
                });

                break;


            case R.id.img_wei:
                MingUtils.login(MainActivity.this, 1, new ResultListener() {
                    @Override
                    public void success(UmengBean umengBean) {


                    }
                });
                break;


        }
    }
}




