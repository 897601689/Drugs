package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;

import com.drugs.R;

import butterknife.ButterKnife;

public class HomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();

        //http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0415/7831.html
        //https://github.com/huanghaibin-dev/CalendarView
        //ImageLoader
        //https://blog.csdn.net/qq_29269233/article/details/53352668
    }

    private void init() {

    }
}
