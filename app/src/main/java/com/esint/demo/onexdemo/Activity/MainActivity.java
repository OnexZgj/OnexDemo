package com.esint.demo.onexdemo.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.esint.demo.onexdemo.Common.Common;
import com.esint.demo.onexdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.et_user_name)
    EditText etUserName;
    @InjectView(R.id.et_user_psw)
    EditText etUserPsw;
    @InjectView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.INTERNET}, Common.REQUEST_CODE);


    }

    @OnClick({R.id.et_user_name, R.id.et_user_psw, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_user_name:
                break;
            case R.id.et_user_psw:
                break;
            case R.id.btn_login:
                startActivity(new Intent(MainActivity.this,DeviceManagerActivity.class));
                break;
        }
    }
}
