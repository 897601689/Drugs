package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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



    @OnClick({R.id.txt_login, R.id.txt_visit, R.id.txt_drug_record, R.id.txt_pay_record})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.txt_login:
                intent = new Intent(MeActivity.this, UserActivity.class);
                startActivity(intent);
                break;
            case R.id.txt_visit:
                if(Global.login_state) {
                    intent = new Intent(MeActivity.this, VisitActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(MeActivity.this, UserActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.txt_drug_record:
                if(Global.login_state) {
                    intent = new Intent(MeActivity.this, MainActivity.class);
                    intent.putExtra("page", 1);
                    startActivity(intent);
                    finish();
                }else{
                    intent = new Intent(MeActivity.this, UserActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.txt_pay_record:
                break;
        }
    }
}
