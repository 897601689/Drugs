package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.drugs.R;
import com.drugs.utils.Global;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivity extends Activity {
    private static final String TAG = "MeActivity";
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.txt_visit)
    LinearLayout txtVisit;
    @BindView(R.id.txt_drug_record)
    LinearLayout txtDrugRecord;
    @BindView(R.id.txt_pay_record)
    LinearLayout txtPayRecord;
    @BindView(R.id.txt_login_name)
    TextView txtLoginName;
    @BindView(R.id.layout_login_info)
    LinearLayout layoutLoginInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
    }

    protected void onResume() {

        super.onResume();
        Log.e("TAG", "onResume: ");
        if (Global.login_state) {
            txtLogin.setVisibility(View.GONE);
            txtLoginName.setText(Global.login_mobile);
            layoutLoginInfo.setVisibility(View.VISIBLE);

        } else {
            txtLogin.setVisibility(View.VISIBLE);
            layoutLoginInfo.setVisibility(View.GONE);
        }
    }

    protected void onPause() {

        super.onPause();
        Log.e("TAG", "onPause: ");
    }

    protected void onStart() {

        super.onStart();
        Log.e("TAG", "onStart: ");
    }

    protected void onStop() {

        super.onStop();
        Log.e("TAG", "onStop: ");
    }

    @OnClick({R.id.txt_login, R.id.txt_visit, R.id.txt_drug_record, R.id.txt_pay_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_login:
                Intent intent = new Intent(MeActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_visit:
                break;
            case R.id.txt_drug_record:
                break;
            case R.id.txt_pay_record:
                break;
        }
    }
}
