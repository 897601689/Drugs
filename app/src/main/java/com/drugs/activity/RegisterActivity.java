package com.drugs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drugs.R;
import com.drugs.bean.UserInfo;
import com.drugs.dao.DBOperation;
import com.drugs.utils.EditTextClearTools;
import com.drugs.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends Activity {


    DBOperation db;//数据操作对象
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
    @BindView(R.id.et_password2)
    EditText etPassword2;
    @BindView(R.id.iv_pwdClear2)
    ImageView ivPwdClear2;
    @BindView(R.id.rl_userPassword2)
    RelativeLayout rlUserPassword2;
    @BindView(R.id.chk_agree)
    CheckBox chkAgree;
    @BindView(R.id.txt_register)
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_register);
        ButterKnife.bind(this);


        init();

    }

    private void init() {
        db = new DBOperation(RegisterActivity.this);

        EditTextClearTools.addClearListener(etUserName, ivUnameClear);
        EditTextClearTools.addClearListener(etPassword, ivPwdClear);
        EditTextClearTools.addClearListener(etPassword2, ivPwdClear2);
    }

    @OnClick(R.id.txt_register)
    public void onViewClicked() {
        if (Utils.isViewEmpty(etUserName)) {
            Toast.makeText(getApplicationContext(), "用户名不能为空", Toast.LENGTH_SHORT).show();
        } else if (Utils.isViewEmpty(etPassword)) {
            Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (Utils.isViewEmpty(etPassword2)) {
            Toast.makeText(getApplicationContext(), "确认密码不能为空", Toast.LENGTH_SHORT).show();
        } else if (!etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            Toast.makeText(getApplicationContext(), "两次密码不一致", Toast.LENGTH_SHORT).show();
        } else if (!Utils.isChinaPhoneLegal(etUserName.getText().toString())) {
            Toast.makeText(getApplicationContext(), "手机格式不正确，请重新输入", Toast.LENGTH_SHORT).show();
        } else {
            String id = etUserName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            if (!db.UserExist(id)) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                UserInfo userInfo = new UserInfo();
                userInfo.setMobile(id);
                userInfo.setPassword(password);
                userInfo.setStatus("1");
                userInfo.setCreatetime(format.format(new Date()));
                userInfo.setUpdatetime(format.format(new Date()));
                db.AddUserInfo(userInfo);
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "用户已注册，请登陆", Toast.LENGTH_SHORT).show();

            }

        }
    }
}
