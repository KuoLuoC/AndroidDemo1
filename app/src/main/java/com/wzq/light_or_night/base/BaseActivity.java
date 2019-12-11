package com.wzq.light_or_night.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.wzq.light_or_night.R;
import com.wzq.light_or_night.skinTheme.ChangeModeController;
import com.wzq.light_or_night.utils.NewStatusBarUtil;

import java.util.ArrayList;


public abstract class BaseActivity extends AppCompatActivity {


    private ArrayList<String> tagList;
    protected Bundle savedInstanceState;
    private View statusBar;
    private int hight;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ChangeModeController.getInstance().init(this,R.attr.class).setTheme(this, R.style.AppTheme, R.style.NightAppTheme);
        super.onCreate(savedInstanceState);

        // 设置主题色，，，一定要在setView之前
        //第一步 当前要立即变色的页面
        //其他页面
        //ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
//        StatusBarUtil.fullScreen(BaseActivity.this);
        this.savedInstanceState = savedInstanceState;
        setContentView(attachLayoutRes());
        //设置状态栏透明
        NewStatusBarUtil.setTranslucentStatus(BaseActivity.this);
        //动态设置状态栏高度
        statusBar=findViewById(R.id.ve_hight);
        if (statusBar != null) {
            //取控件当前的布局参数
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) statusBar.getLayoutParams();
            //获取状态栏高度
            hight= NewStatusBarUtil.getStatusBarHeight(BaseActivity.this);
            //设置高度值
            params.height =hight;
            //使设置好的布局参数应用到控件
            statusBar.setLayoutParams(params);
        }

        //黄油刀绑定
//        unbinder = ButterKnife.bind(this);
        //注册Evenbus
//        if (useEventBus()) {
//            Log.e("123", "111111111111111");
//            EventBus.getDefault().register(this);
//        }

        initData();
        initView();
        initListener();
    }

    /**
     * 初始化布局id
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化监听器
     */
    protected abstract void initListener();

    protected boolean useEventBus() {
        return false;
    }


    /**
     * eventbus回调方法
     *
     */
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(Intent intent) {
//        if (TextUtils.equals(intent.getStringExtra("tig"), "0"))
//        finish();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //第三步   在onDestroy调用
//        ChangeModeController.onDestory();
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
//        unDispose();
//        EventBus.getDefault().unregister(this);
    }

}
