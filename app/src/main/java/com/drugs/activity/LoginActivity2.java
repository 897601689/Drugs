package com.drugs.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drugs.R;
import com.drugs.dao.DBOperation;
import com.drugs.utils.EditTextClearTools;
import com.drugs.utils.Global;
import com.drugs.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity2 extends Activity {


    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.iv_unameClear)
    ImageView ivUnameClear;
    @BindView(R.id.rl_userName)
    RelativeLayout rlUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_pwdClear)
    ImageView ivPwdClear;
    @BindView(R.id.rl_userPassword)
    RelativeLayout rlUserPassword;
    @BindView(R.id.btn_login)
    TextView btnLogin;

    DBOperation db;//数据操作对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        init();

    }


    private void init() {

        db = new DBOperation(LoginActivity2.this);

        EditTextClearTools.addClearListener(etUserName, ivUnameClear);
        EditTextClearTools.addClearListener(etPassword, ivPwdClear);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {

        if (Utils.isViewEmpty(etUserName)) {
            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (Utils.isViewEmpty(etPassword)) {
            Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            String id = etUserName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (db.UserExist(id)) {

                if (db.UserExist(id, password)) {
                    //saveUserInfo(id, db.GetDoctorNameByID(id), db.GetDoctorLevelByID(id));//保存医生登录状态
                    if (db.GetUserStatusByMobile(id) == 1) {
                        Global.login_state = true;//登陆成功

                        SharedPreferences config = getSharedPreferences("config", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor edit = config.edit();
                        edit.putString("loginMobile", id);
                        edit.apply();//提交保存

                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "您被禁止登陆", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "当前用户不可登陆", Toast.LENGTH_SHORT).show();
            }
        }

        //finish();
    }
}
