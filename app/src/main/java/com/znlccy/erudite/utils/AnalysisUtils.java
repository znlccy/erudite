package com.znlccy.erudite.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
  * Author: znlccy
  * Date: 2019-10-09 10:55
  * Version: 1.0.0
  * Comment: 分析工具
  */
public class AnalysisUtils {


    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }
}
