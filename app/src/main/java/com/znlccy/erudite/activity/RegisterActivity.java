package com.znlccy.erudite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.znlccy.erudite.R;
import com.znlccy.erudite.utils.MD5Utils;

/**
  * Author: znlccy
  * Date: 2019-10-08 16:28
  * Version: 1.0.0
  * Comment: 注册界面
  */
public class RegisterActivity extends AppCompatActivity {

    //标题
    private TextView tv_main_title;

    //返回按钮
    private TextView tv_back;

    //注册按钮
    private Button btn_register;

    //用户名、密码、再次输入的密码的控件
    private EditText et_user_name, et_psw, et_psw_again;

    //用户名、密码、再次输入的密码的控件的获取值
    private String userName, psw, pswAgain;

    //标题布局
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局
        setContentView(R.layout.activity_register);

        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);

        //初始化页面
        init();
    }

    /**
     * 初始化界面
     */
    private void init() {
        //从main_title_bar.xml页面布局中获得对应的UI控件
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("注册");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.TRANSPARENT);
        //从activity_register.xml页面布局中获得对应的UI控件
        btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name = (EditText) findViewById(R.id.edit_user_name);
        et_psw = (EditText) findViewById(R.id.edit_psw);
        et_psw_again = (EditText) findViewById(R.id.edit_psw_again);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入在相应控件中的字符串
                getEditString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(RegisterActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                } else if (isExistUserName(userName)) {
                    Toast.makeText(RegisterActivity.this, "此账户名已存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                    //把用户名和密码保存到SharedPreferences中
                    saveRegisterInfo(userName, psw);
                    //注册成功之后把用户名传递到LoginActivity.java中
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    RegisterActivity.this.finish();
                }
            }
        });
    }


    /**
     * 获取控件的字符串
     */
    private void getEditString() {
        userName = et_user_name.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
    }

    /**
     * 本地数据库是否已经有了该用户名
     * @param userName
     * @return
     */
    private boolean isExistUserName(String userName) {

        boolean hasUserName = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        if (!TextUtils.isEmpty(spPsw)) {
            hasUserName = true;
        }

        return hasUserName;
    }

    /**
     * 保存用户名和密码到SharedPreferences中
     * @param userName
     * @param psw
     */
    private void saveRegisterInfo(String userName, String psw) {
        String md5Psw = MD5Utils.md5(psw);
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        //以用户名为key，密码为value保存到SharedPreferences中
        editor.putString(userName,md5Psw);
        editor.commit();
    }
}
