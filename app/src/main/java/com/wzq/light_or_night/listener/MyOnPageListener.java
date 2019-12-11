package com.wzq.light_or_night.listener;

import android.support.v4.view.ViewPager;

public abstract class MyOnPageListener implements ViewPager.OnPageChangeListener {

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        onMyPageSelected(position);
    }

    public abstract void onMyPageSelected(int position);
}
