package com.android.wolf.werewolfkillerscore.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lxh on 2017/5/28.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String name = "WolfKiller.db"; //数据库名称

    private static final int version = 1; //数据库版本

    private static DatabaseHelper mInstance;

    public static DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context);
        }
        return mInstance;
    }


    public DatabaseHelper(Context context) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SqlFactory.CREATE_HISTORY);
        sqLiteDatabase.execSQL(SqlFactory.CREATE_GAMER_HISTORY);
        sqLiteDatabase.execSQL(SqlFactory.CREATE_GAMER_LIST);
        sqLiteDatabase.execSQL(SqlFactory.CREATE_ROLE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
