package com.drugs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.drugs.bean.DrugsInfo;
import com.drugs.bean.DrugsUseInfo;
import com.drugs.bean.UserInfo;
import com.drugs.bean.VisitInfo;

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
     * 根据用户mobile查询用户ID
     *
     * @param mobile 用户的mobile
     * @return 返回状态
     */
    public int GetUserIdByMobile(String mobile) {
        int level = 0;
        sql = "select id from tb_member where mobile = ?";
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
     * @param mobile 用户 mobile
     * @param user   用户信息类
     * @return true为修改成功
     */
    public boolean UpdateUserInfo(String mobile, UserInfo user) {
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
            int count = db.update("tb_member", values, "mobile=?", new String[]{mobile});
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


    /**
     * 获取就诊记录
     *
     * @param mid 用户mid
     * @return
     */
    public Cursor GetVisitInfoByid(String mid) {
        db = helper.getReadableDatabase();
        String sqlId = "select * from tb_visit where mid = ?";
        Cursor cursor = db.rawQuery(sqlId, new String[]{mid});
        if (cursor.getCount() == 0) {
            cursor = null;
        }
        db.close();
        return cursor;
    }


    /**
     * 添加就诊记录
     *
     * @param visitInfo 问诊记录类
     */
    public void AddVisitInfo(VisitInfo visitInfo) {
        db = helper.getWritableDatabase();
        sql = "insert into tb_visit(id, mid, uid, visittime, remark) values(?,?,?,?,?)";
        db.beginTransaction();
        try {
            db.execSQL(sql, new String[]{visitInfo.getID(), visitInfo.getMid(), visitInfo.getUid(),
                    visitInfo.getVisittime(), visitInfo.getRemark()
            });
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    /**
     * 获取用药记录
     *
     * @param mid 用户 mid
     * @return
     */
    public Cursor GetPlanInfoByMid(String mid) {
        db = helper.getReadableDatabase();
        String sqlId = "select distinct title, useingtime from tb_drug_record where mid = ?";
        Cursor cursor = db.rawQuery(sqlId, new String[]{mid});
        if (cursor.getCount() == 0) {
            cursor = null;
        }
        db.close();
        return cursor;
    }

    /**
     * 获取用药详细记录
     *
     * @param mid 用户mobile
     * @return
     */
    public Cursor GetPlanDetailsInfoByMid(String mid, String title) {
        db = helper.getReadableDatabase();
        String sqlId = "select * from tb_drug_record where mid = ? and title = ?";
        Cursor cursor = db.rawQuery(sqlId, new String[]{mid, title});
        if (cursor.getCount() == 0) {
            cursor = null;
        }
        db.close();
        return cursor;
    }


    /**
     * 添加用药记录
     *
     * @param drugsUseInfo 药品使用记录类
     */
    public void AddDrugsUseInfo(DrugsUseInfo drugsUseInfo) {
        db = helper.getWritableDatabase();
        sql = "insert into tb_drug_record(id, title,mid, uid,drugname,counts, useingtime, createtime) values(?,?,?,?,?,?,?,?)";
        db.beginTransaction();
        try {
            db.execSQL(sql, new String[]{drugsUseInfo.getID(), drugsUseInfo.getTitle(), drugsUseInfo.getMid(),
                    drugsUseInfo.getUid(), drugsUseInfo.getDrugname(), drugsUseInfo.getCounts(),
                    drugsUseInfo.getUseingtime(), drugsUseInfo.getCreatetime()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 添加药品信息
     *
     * @param drugsInfo 药品信息类
     */
    public void AddDrugsInfo(DrugsInfo drugsInfo) {
        db = helper.getWritableDatabase();
        sql = "insert into tb_drugs(id,drugname,drugprice,description,actions,indications,contraindications," +
                "dosageandadministration,adversereactions,precautions,packages,storage,others) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        db.beginTransaction();
        try {
            db.execSQL(sql, new String[]{drugsInfo.getId(), drugsInfo.getDrugname(), drugsInfo.getDrugPrice(), drugsInfo.getDescription(),
                    drugsInfo.getActions(), drugsInfo.getIndications(), drugsInfo.getContraindications(),
                    drugsInfo.getDosageandadministration(), drugsInfo.getAdversereactions(), drugsInfo.getPrecautions(),
                    drugsInfo.getPackages(), drugsInfo.getStorage(), drugsInfo.getOthers()});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    /**
     * 获取药品信息
     *
     * @param name 用户 mid
     * @return
     */
    public Cursor GetDrugsInfoByName(String name) {
        db = helper.getReadableDatabase();
        String sqlId = "select * from tb_drugs where drugname like ?";
        Cursor cursor = db.rawQuery(sqlId, new String[]{"%" + name + "%"});
        if (cursor.getCount() == 0) {
            cursor = null;
        }
        db.close();
        return cursor;
    }

}
