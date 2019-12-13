package com.wzq.light_or_night.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzq.light_or_night.R;
import com.wzq.light_or_night.skinTheme.ChangeModeController;
import com.wzq.light_or_night.utils.NewStatusBarUtil;


public abstract class BaseFragment extends Fragment {


    protected Activity mActivity;
    protected Context mContext;
    private View statusBar;
    private int hight;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(attachLayoutRes(), null);
//        ChangeModeController.getInstance().init(getActivity(),R.attr.class).setTheme(getActivity(), R.style.AppTheme, R.style.NightAppTheme);
//        unbinder = ButterKnife.bind(this, view);
//        mCompositeDisposable = new CompositeDisposable();
        //动态设置状态栏高度
        statusBar=view.findViewById(R.id.ve_hight);
        if (statusBar != null) {
            //取控件当前的布局参数
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) statusBar.getLayoutParams();
            //获取状态栏高度
            hight= NewStatusBarUtil.getStatusBarHeight(getActivity());
            //设置高度值
            params.height = hight;
            //使设置好的布局参数应用到控件
            statusBar.setLayoutParams(params);
        }

        initView(view);
        initListener();
        initData();
        return view;
    }

    /**
     * 初始化布局id
     */
    protected abstract int attachLayoutRes();

    /**
     * 初始化View
     */
    protected abstract void initView(View view);

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



    @Override
    public void onDestroy() {
        super.onDestroy();
        //第三步   在onDestroy调用
//        ChangeModeController.onDestory();

    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }
}
