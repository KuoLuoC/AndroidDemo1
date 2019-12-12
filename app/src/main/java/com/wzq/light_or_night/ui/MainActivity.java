package com.wzq.light_or_night.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.wzq.light_or_night.adapter.MyPageFragmentAdapter;
import com.wzq.light_or_night.base.BaseFragment;
import com.wzq.light_or_night.fragment.Fragment1;
import com.wzq.light_or_night.fragment.Fragment2;
import com.wzq.light_or_night.fragment.Fragment3;
import com.wzq.light_or_night.fragment.Fragment4;
import com.wzq.light_or_night.fragment.Fragment5;
import com.wzq.light_or_night.skinTheme.ChangeModeController;
import com.wzq.light_or_night.skinTheme.ChangeModeHelper;
import com.wzq.light_or_night.widget.NoScrollViewPager;
import com.wzq.light_or_night.R;
import com.wzq.light_or_night.base.BaseActivity;
import com.wzq.light_or_night.listener.MyOnPageListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private NoScrollViewPager vpMain;
    private RadioButton rbTodo;
    private RadioButton rbModel;
    private RadioButton rbElectrification;
    private RadioButton rbConsulting;
    private RadioButton rbMine;
    private CheckBox mineLight;
    private DrawerLayout drawerLayout1;
    private int index = -1;
    private List<BaseFragment> fragments;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private int theme;
    private boolean isNight;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        vpMain = (NoScrollViewPager) findViewById(R.id.vp_Main);
        rbTodo = (RadioButton) findViewById(R.id.rb_todo);
        rbModel = (RadioButton) findViewById(R.id.rb_model);
        rbElectrification = (RadioButton) findViewById(R.id.rb_electrification);
        rbConsulting = (RadioButton) findViewById(R.id.rb_consulting);
        rbMine = (RadioButton) findViewById(R.id.rb_mine);
        mineLight = (CheckBox) findViewById(R.id.mine_light);
        drawerLayout1 = (DrawerLayout) findViewById(R.id.id_drawer_layout);
       //保存夜间模式状态
        if(isNight){
            mineLight.setChecked(true);
        }else {
            mineLight.setChecked(false);
        }
        //保存抽屉效果
        if(theme==1){
            openDrawer();
        }
        //初始化fragment模块
        initFragments();
        MyPageFragmentAdapter pageFragmentAdapter = new MyPageFragmentAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(pageFragmentAdapter);
        vpMain.setOffscreenPageLimit(5);
        vpMain.setScroll(false);//手势滑动切换功能模块
        vpMain.addOnPageChangeListener(new MyOnPageListener() {
            @Override
            public void onMyPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbTodo.setChecked(true);
                        break;
                    case 1:
                        rbModel.setChecked(true);
                        break;
                    case 2:
                        rbElectrification.setChecked(true);
                        break;
                    case 3:
                        rbConsulting.setChecked(true);
                        break;
                    case 4:
                        rbMine.setChecked(true);
                        break;
                }
            }
        });
        changeCurrentViewPage(rbTodo, 0);
        changeCurrentViewPage(rbModel, 1);
        changeCurrentViewPage(rbElectrification, 2);
        changeCurrentViewPage(rbConsulting, 3);
        changeCurrentViewPage(rbMine, 4);

    }
    private void initFragments() {
        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }
        if (fragment2 == null) {
            fragment2 = new Fragment2();
        }
        if (fragment3 == null) {
            fragment3 = new Fragment3();
        }
        if (fragment4 == null) {
            fragment4 = new Fragment4();
        }
        if (fragment5 == null) {
            fragment5 = new Fragment5();
        }

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
    }


    private void changeCurrentViewPage(RadioButton rb, final int pos) {
        rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vpMain.setCurrentItem(pos);
                    index = pos;
                }
            }
        });
    }

    //显示抽屉效果
    public void openDrawer() {
        drawerLayout1.openDrawer(GravityCompat.START);
    }

    public void mineChangeFragment(int index) {
        switch (index) {
            case 0:
                rbTodo.setChecked(true);
                break;
            case 1:
                rbModel.setChecked(true);
                break;
            case 3:
                rbConsulting.setChecked(true);
                break;
        }
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        Intent intent = getIntent();
        index = intent.getIntExtra("index", -1);
        theme = intent.getIntExtra("theme", 0);
        isNight =ChangeModeHelper.getStatus(MainActivity.this);
    }

    @Override
    protected void initListener() {
        mineLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String themes;
                switch (buttonView.getId()) {
                    case R.id.mine_light:
                        if (isChecked) {
                            ChangeModeController.changeNight(MainActivity.this, R.style.NightAppTheme);
                            ChangeModeHelper.setStatus(MainActivity.this,true);
                        } else {
                            ChangeModeController.changeDay(MainActivity.this, R.style.AppTheme);
                            ChangeModeHelper.setStatus(MainActivity.this,false);
                        }
                        break;
                }
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
