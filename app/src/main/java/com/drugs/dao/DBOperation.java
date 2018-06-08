package com.drugs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.drugs.adapter.UserInfo;

public class DBOperation {
    private DatabaseHelper helper = null;
    private SQLiteDatabase db = null;
    String sql = null;
    Context context;

    public DBOperation(Context context) {
        helper = new DatabaseHelper(context);
        this.context = context;
    }


    /**
     * 判断用户是否存在
     *
     * @param mobile 用户手机号
     * @return true 为登录成功
     */
    public boolean UserExist(String mobile) {
        boolean flag = false;
        sql = "select * from tb_member where mobile = ? ";
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{mobile});
        if (cursor.getCount() > 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }


    /**
     * 判断用户登录信息
     *
     * @param mobile   用户手机号
     * @param password 登录密码
     * @return true 为登录成功
     */
    public boolean UserExist(String mobile, String password) {
        boolean flag = false;
        sql = "select * from tb_member where mobile = ? and password = ?";
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{mobile, password});
        if (cursor.getCount() > 0) {
            flag = true;
        }
        cursor.close();
        db.close();
        return flag;
    }

    /**
     * 根据用户mobile查询用户账号状态
     *
     * @param mobile 用户的mobile
     * @return 返回状态
     */
    public int GetUserStatusByMobile(String mobile) {
        int level = 0;
        sql = "select status from tb_member where mobile = ?";
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{mobile});
        if (cursor.moveToNext()) {
            level = cursor.getInt(0);
        }
        cursor.close();
        return level;
    }

    /**
     * 添加用户信息
     *
     * @param user 用户信息类
     */
    public void AddUserInfo(UserInfo user) {
        db = helper.getWritableDatabase();
        sql = "insert into tb_member(id, membername, password, mobile, address, sex, age, status, createtime, updatetime ) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        db.beginTransaction();
        try {
            db.execSQL(sql, new String[]{user.getID(), user.getMembername(), user.getPassword(),
                    user.getMobile(), user.getAddress(), user.getSex(), user.getAge(),
                    user.getStatus(), user.getCreatetime(), user.getUpdatetime()
            });
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 修改用户信息
     *
     * @param ID   用户ID
     * @param user 用户信息类
     * @return true为修改成功
     */
    public boolean UpdateUserInfo(String ID, UserInfo user) {
        boolean flag = false;
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put("id", user.getID());
            values.put("membername", user.getMembername());
            values.put("password", user.getPassword());
            values.put("mobile", user.getMobile());
            values.put("address", user.getAddress());
            values.put("sex", user.getSex());
            values.put("age", user.getAge());
            values.put("status", user.getStatus());
            values.put("createtime", user.getCreatetime());
            values.put("updatetime", user.getUpdatetime());
            int count = db.update("tb_member", values, "id=?", new String[]{ID});
            if (count > 0) {
                flag = true;
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return flag;
    }
}
