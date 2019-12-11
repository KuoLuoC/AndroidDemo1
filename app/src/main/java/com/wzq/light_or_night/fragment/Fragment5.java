package com.wzq.light_or_night.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzq.light_or_night.R;
import com.wzq.light_or_night.base.BaseFragment;
import com.wzq.light_or_night.ui.MainActivity;

/**
 * @author wzq
 * @data 2019/12/10
 * @email wang_love152@163.com
 * @for
 */
public class Fragment5 extends BaseFragment {

    private ImageView slide;
    private ImageView back;
    private TextView tbTitle;
    private ImageView QRCode;
    private ImageView select;
    private MainActivity ac;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_5;
    }

    @Override
    protected void initView(View view) {
        ac = (MainActivity) getActivity();
        slide = (ImageView) view.findViewById(R.id.slide);
        back = (ImageView) view.findViewById(R.id.back);
        tbTitle = (TextView) view.findViewById(R.id.tb_title);
        QRCode = (ImageView) view.findViewById(R.id.QR_code);
        select = (ImageView) view.findViewById(R.id.select);

        //抽屉效果监听
        if(ac!=null)
        slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.openDrawer();
            }
        });
        tbTitle.setText("模块五");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
