package com.wzq.light_or_night.skinTheme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * 夜间模式辅助类
 */
public class ChangeModeHelper {
    public static final int MODE_DAY = 1;

    public static final int MODE_NIGHT = 2;

    public static final boolean isCheck = false;
    private static String Mode = "mode";
    public static void setChangeMode(Context ctx,int mode){
        SharedPreferences sp = ctx.getSharedPreferences("config_mode", Context.MODE_PRIVATE);
        sp.edit().putInt(Mode, mode).commit();
    }
    public static int getChangeMode(Context ctx){
        SharedPreferences sp = ctx.getSharedPreferences("config_mode", Context.MODE_PRIVATE);
        return sp.getInt(Mode, MODE_DAY);
    }

    public static void setStatus(Context context,boolean status){
        //第一个写的是保存的文件名
        SharedPreferences sharedPreferences = context.getSharedPreferences("themeapp", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putBoolean("isNight", status);
        editor.commit();//提交修改
    }

    public static boolean getStatus(Context context){
        SharedPreferences share=context.getSharedPreferences("themeapp", context.MODE_PRIVATE);
        Boolean i=share.getBoolean("isNight",false);
        return i;
    }
}
