package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;

import com.drugs.R;

import butterknife.ButterKnife;

public class MeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
    }
}
