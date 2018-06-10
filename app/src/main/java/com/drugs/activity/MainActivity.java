package com.drugs.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.drugs.R;
import com.drugs.dao.DBOperation;
import com.drugs.utils.Global;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActivityGroup {

    @BindView(R.id.data_pager)
    ViewPager dataPager;
    @BindView(R.id.layout_home)
    LinearLayout layoutHome;
    @BindView(R.id.layout_plan)
    LinearLayout layoutPlan;
    @BindView(R.id.layout_me)
    LinearLayout layoutMe;


    @BindView(R.id.img_home)
    ImageView imgHome;
    @BindView(R.id.img_plan)
    ImageView imgPlan;
    @BindView(R.id.img_me)
    ImageView imgMe;

    ArrayList<View> list;
    private MyPagerView mAdapter = new MyPagerView();
    DBOperation db;//数据操作对象
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //初始化视图
        initView();

        init();

        Intent intent = getIntent();
        int page = intent.getIntExtra("page",0);//第一个参数是取值的key,第二个参数是默认值
        dataPager.setCurrentItem(page);
    }

    private void init() {
        db = new DBOperation(MainActivity.this);
        SharedPreferences config = getSharedPreferences("config", Activity.MODE_PRIVATE);
        String mobile = config.getString("loginMobile", "");
        //Log.e("TAG", "init: "+mobile );
        if (!"".equals(mobile)) {
            Global.login_state = true;
            Global.login_mobile = mobile;
            Global.login_id = String.valueOf(db.GetUserIdByMobile(Global.login_mobile));
        }
    }

    protected void resetTabBtn() {
        imgHome.setImageResource(R.mipmap.ic_navigation_normal);
        imgPlan.setImageResource(R.mipmap.ic_headlines_normal);
        imgMe.setImageResource(R.mipmap.ic_myinfo_normal);
    }


    private void initView() {
        list = new ArrayList<>();

        View home = getLocalActivityManager().startActivity("activity01",
                new Intent(this, HomeActivity.class)).getDecorView();
        list.add(home);

        View plan = getLocalActivityManager().startActivity("activity02",
                new Intent(this, PlanActivity.class)).getDecorView();
        list.add(plan);

        View me = getLocalActivityManager().startActivity("activity03",
                new Intent(this, MeActivity.class)).getDecorView();
        list.add(me);

        dataPager.setAdapter(mAdapter);
        dataPager.clearAnimation();
        dataPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTabBtn();
                switch (position) {
                    case 0:
                        imgHome.setImageResource(R.mipmap.ic_navigation_pressed);
                        break;
                    case 1:
                        imgPlan.setImageResource(R.mipmap.ic_headlines_pressed);
                        break;
                    case 2:
                        imgMe.setImageResource(R.mipmap.ic_myinfo_pressed);
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
