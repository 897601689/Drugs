package com.drugs.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.dao.DBOperation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends ActivityGroup {
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.img_login)
    ImageView imgLogin;
    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.txt_register)
    TextView txtRegister;
    @BindView(R.id.img_register)
    ImageView imgRegister;
    @BindView(R.id.register)
    RelativeLayout register;
    @BindView(R.id.data_pager)
    ViewPager dataPager;

    ArrayList<View> list;
    private MyPagerView mAdapter = new MyPagerView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        list = new ArrayList<>();

        View login = getLocalActivityManager().startActivity("activity01",
                new Intent(this, LoginActivity2.class)).getDecorView();
        list.add(login);

        View register = getLocalActivityManager().startActivity("activity02",
                new Intent(this, RegisterActivity.class)).getDecorView();
        list.add(register);


        dataPager.setAdapter(mAdapter);
        dataPager.clearAnimation();
        dataPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        txtLogin.setTextColor(Color.rgb(48, 134, 242));
                        imgLogin.setImageResource(R.mipmap.tv_checked_topic_bg);
                        break;
                    case 1:
                        txtRegister.setTextColor(Color.rgb(48, 134, 242));
                        imgRegister.setImageResource(R.mipmap.tv_checked_topic_bg);
                        break;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    protected void resetTabBtn() {
        //txtLogin.setTextColor(Color.rgb(48,134,242));
        txtLogin.setTextColor(Color.GRAY);
        imgLogin.setImageDrawable(null);
        txtRegister.setTextColor(Color.GRAY);
        imgRegister.setImageDrawable(null);
    }

    private class MyPagerView extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(list.get(arg1));
        }


        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(list.get(arg1));
            return list.get(arg1);
        }
    }

    @OnClick({R.id.login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                dataPager.setCurrentItem(0);
                break;
            case R.id.register:
                dataPager.setCurrentItem(1);
                break;
        }
    }
}
