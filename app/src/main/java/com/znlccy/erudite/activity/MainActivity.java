package com.znlccy.erudite.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.znlccy.erudite.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //中间内容栏
    private FrameLayout mBodyLayout;

    //底部按钮栏
    private LinearLayout mBottomLayout;

    //底部按钮
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myInfo;

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //设置此界面为竖屏界面
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //初始化界面
        init();

        //初始化底部界面
        initBottomBar();

        //设置监听
        setListener();

        //设置初始化状态
        setInitStatus();
    }

    /**
     * 初始化界面
     */
    private void init() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
        initBodyLayout();
    }


    /**
     *
     */
    private void setListener() {

    }

    /**
     * 获取底部导航栏上的控件
     */
    private void initBottomBar() {


    }

    /**
     *
     */
    private void setInitStatus() {

    }

    /**
     *
     */
    private void initBodyLayout() {

    }

    @Override
    public void onClick(View view) {

    }
}
