package com.wzq.light_or_night.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.wzq.light_or_night.base.BaseFragment;

import java.util.List;

public class MyPageFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;

    public MyPageFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    //只会在第一次初始化的时候地调用，每次都会查找一下是否有当前Fragment生成过。生成了就不调用getItem的方法。
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
