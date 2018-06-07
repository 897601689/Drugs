package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.drugs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends Activity {
    @BindView(R.id.edit_search)
    TextView editSearch;
    @BindView(R.id.img_scan)
    ImageView imgScan;

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

    @OnClick({R.id.edit_search, R.id.img_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_search:
                Log.e("TAG", "onViewClicked: " );
                break;
            case R.id.img_scan:
                break;
        }
    }
}
