package com.znlccy.erudite.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.znlccy.erudite.R;

import java.util.Timer;
import java.util.TimerTask;

/**
  * Author: znlccy
  * Date: 2019-10-08 16:29
  * Version: 1.0.0
  * Comment: 欢迎界面
  */
public class SplashActivity extends AppCompatActivity {

    /**
     * 软件版本号
     */
    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        init();
    }

    /**
     * 初始化界面
     */
    private void init() {
        //获取文本内容
        tv_version = (TextView) findViewById(R.id.tv_version);

        try {
            //通过PackageManager获取版本信息
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
            //设置版本信息
            tv_version.setText("V" + info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("V");
        }

        //让此界面延迟3秒才跳转，timer中有一个线程，这个线程不断执行task
        Timer timer = new Timer();
        //TimerTask类表示一个在指定时间内执行的task
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        };

        //设置这个task在延迟3秒之后自动执行
        timer.schedule(task, 3000);
    }
}
