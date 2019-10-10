package com.znlccy.erudite.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.znlccy.erudite.R;
import com.znlccy.erudite.activity.LoginActivity;
import com.znlccy.erudite.utils.AnalysisUtils;

/**
  * Author: znlccy
  * Date: 2019-10-09 13:39
  * Version: 1.0.0
  * Comment: 我的视图
  */
public class MyInfoView {

    /**
     * 头部图片
     */
    public ImageView iv_head_icon;

    /**
     * 线性布局
     */
    private LinearLayout ll_head;

    /**
     * 相对布局
     */
    private RelativeLayout rl_course_history, rl_setting;

    /**
     * 用户名
     */
    private TextView tv_user_name;

    /**
     * 上下文
     */
    private Activity mContext;

    /**
     * 转换工具
     */
    private LayoutInflater mInflater;

    /**
     * 当前视图
     */
    private View mCurrentView;

    /**
     * 构造函数
     * @param context
     */
    public MyInfoView(Activity context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建视图
     */
    private void createView() {
        //获取界面控件
        initView();
    }

    /**
     * 获取界面控件
     */
    private void initView() {
        //设置布局文件
        mCurrentView = mInflater.inflate(R.layout.main_view_myinfo, null);
        ll_head = (LinearLayout) mCurrentView.findViewById(R.id.ll_head);
        iv_head_icon = (ImageView) mCurrentView.findViewById(R.id.iv_head_icon);
        rl_course_history = (RelativeLayout) mCurrentView.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) mCurrentView.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) mCurrentView.findViewById(R.id.tv_user_name);
        mCurrentView.setVisibility(View.VISIBLE);
        //设置登录时界面控件的状态
        setLoginParams(readLoginStatus());
        ll_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否已经登录
                if (readLoginStatus()) {
                    //已登录跳转到个人资料界面

                } else {
                    //未登录跳转到登录界面
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivityForResult(intent, 1);
                }
            }
        });
        rl_course_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStatus()) {
                    //跳转到播放记录界面
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readLoginStatus()) {
                    //跳转到设置界面
                } else {
                    Toast.makeText(mContext, "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 从SharedPreferences中读取登录状态
     * @return
     */
    private boolean readLoginStatus() {
        SharedPreferences sp = mContext.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    /**
     * 登录成功之后设置我的界面
     * @param isLogin
     */
    public void setLoginParams(boolean isLogin) {
        if (isLogin) {
            tv_user_name.setText(AnalysisUtils.readLoginUserName(mContext));
        } else {
            tv_user_name.setText("点击登录");
        }
    }

    /**
     * 获取当前在导航栏上方显示对应的View
     * @return
     */
    public View getView() {
        if (mCurrentView == null) {
            createView();
        }
        return mCurrentView;
    }

    /**
     * 显示当前导航栏上方对应的View界面
     */
    public void showView() {
        if (mCurrentView == null) {
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
