package com.drugs.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    //类没有实例化,是不能用作父类构造器的参数,必须声明为静态
    private static final String DATABASE_NAME = "drugs.db"; //数据库名称
    private static final int DATABASE_VERSION = 1; //数据库版本


    private String MEMBERSQl = "create table tb_member(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," + //ID
            "membername nvarchar(10)," +                  //用户姓名
            "password nvarchar(10)," +                    //登录密码
            "mobile nvarchar(11)," +                      //手机号码
            "address nvarchar(128)," +                    //居住地址
            "sex char(1)," +                              //性别
            "age INTEGER(3)," +                           //年龄
            "status INTEGER(1)," +                        //状态
            "createtime DATATIME," +                      //创建时间
            "updatetime DATATIME)";                       //更新时间


    private String DRUGRECORDSQL = "create table tb_drug_record(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +       //ID
            "title nvarchar(256)," +                        //用药记录
            "mid INTEGER(11) REFERENCES tb_member(id)," +   //用户ID
            "uid INTEGER(11)," +                            //医师ID
            "drugname INTEGER(3)," +                        //药品名称
            "counts INTEGER(3)," +                          //用量
            "useingtime DATATIME," +                        //服用时间
            "createtime DATATIME)";                         //日期

    private String DRUGSSQL = "create table tb_drugs(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +       // ID
            "drugname nvarchar(20)," +                      //药品名称
            "drugprice nvarchar(20)," +                      //药品价钱
            "description nvarchar(20) ," +                  //性状
            "actions nvarchar(11)," +                       //药理作用
            "indications nvarchar(3)," +                    //适应症
            "contraindications nvarchar(3)," +              //禁忌症
            "dosageandadministration nvarchar," +           //用量用法
            "adversereactions nvarchar," +                  //不良反应
            "precautions nvarchar," +                       //注意事项
            "packages nvarchar," +                           //包装
            "storage nvarchar," +                           //储藏
            "others nvarchar)";                             //其他项目

    private String VISITSQL = "create table tb_visit(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +       // ID
            "mid INTEGER(11) REFERENCES tb_member(id)," +   //用户ID
            "uid INTEGER(11)," +                            //医师ID
            "visittime DATATIME," +                         // 问诊时间
            "remark nvarchar(256))";                        // 备注


    /**
     * 第一个参数是应用的上下文
     * 第二个参数是应用的数据库名字
     * 第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
     * 第四个参数是数据库版本，必须是大于0的int（即非负数）
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //输出创建数据库的日志信息
        Log.i(TAG, "create Database------------->");
        //execSQL函数用于执行SQL语句
        sqLiteDatabase.execSQL(MEMBERSQl);     //用户表
        sqLiteDatabase.execSQL(DRUGSSQL);      //药品信息表
        sqLiteDatabase.execSQL(DRUGRECORDSQL); //用药记录表
        sqLiteDatabase.execSQL(VISITSQL);      //就诊记录表
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //输出更新数据库的日志信息
        Log.i(TAG, "update Database------------->");
    }
}
