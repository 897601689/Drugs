package com.drugs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.app.Activity;

import com.drugs.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @BindView(R.id.data_pager)
    ViewPager dataPager;
    @BindView(R.id.layout_home)
    LinearLayout layoutHome;
    @BindView(R.id.layout_plan)
    LinearLayout layoutPlan;
    @BindView(R.id.layout_me)
    LinearLayout layoutMe;

    ArrayList<View> list;
    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.img_plan)
    ImageView imgPlan;
    @BindView(R.id.img_me)
    ImageView imgMe;

    private PagerAdapter mAdapter;

    /**
     * ViewPager的适配器
     */
    private LayoutInflater mInflater;

    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mInflater = LayoutInflater.from(this);
        //初始化视图
        initView();
        dataPager.setAdapter(mAdapter);

        dataPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                         imgHome.setImageResource(R.mipmap.axh);
                        break;
                    case 1:
                         imgPlan.setImageResource(R.mipmap.axd);
                        break;
                    case 2:
                        imgMe.setImageResource(R.mipmap.axj);
                        break;
                }

                currentIndex = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    protected void resetTabBtn()  {
        imgHome.setImageResource(R.mipmap.axg);
        imgPlan.setImageResource(R.mipmap.axc);
        imgMe.setImageResource(R.mipmap.axi);
    }


    private void initView() {
        list = new ArrayList<>();
        View home = mInflater.inflate(R.layout.activity_home, null);
        View plan = mInflater.inflate(R.layout.activity_plan, null);
        View me = mInflater.inflate(R.layout.activity_me, null);
        list.add(home);
        list.add(plan);
        list.add(me);

        mAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = list.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
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

        /***
         * 获取每一个item， 类于listview中的getview
         */
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(list.get(arg1));
            return list.get(arg1);
        }
    }


    @OnClick({R.id.layout_home, R.id.layout_plan, R.id.layout_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_home:
                dataPager.setCurrentItem(0);
                break;
            case R.id.layout_plan:
                 dataPager.setCurrentItem(1);
                break;
            case R.id.layout_me:
                 dataPager.setCurrentItem(2);
                break;
        }
    }
}
