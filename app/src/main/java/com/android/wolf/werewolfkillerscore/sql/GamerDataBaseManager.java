package com.android.wolf.werewolfkillerscore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.wolf.werewolfkillerscore.base.WolfApplication;
import com.android.wolf.werewolfkillerscore.models.Gamer;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class GamerDataBaseManager {

    private static GamerDataBaseManager mInstance;

    public static GamerDataBaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new GamerDataBaseManager();
        }
        return mInstance;
    }

    private Context mContext;
    private DatabaseHelper mHelper;

    private GamerDataBaseManager() {
        mContext = WolfApplication.getInstance();
        mHelper = DatabaseHelper.getInstance(mContext);
    }

    public long insert(Gamer gamer) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("name", gamer.getName());
            cv.put("age", gamer.getAge());
            cv.put("sex", gamer.getSex());
            cv.put("desc", gamer.getDesc());
            cv.put("img", gamer.getImg());
            cv.put("score", gamer.getScore());
            cv.put("mvp", gamer.getMvp());
            long index = db.insert(SqlFactory.GAMER_TABLE, null, cv);
            db.setTransactionSuccessful();
            return index;
        } catch (Exception e) {
            return -1;
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }


    public void delete(Gamer gamer) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(SqlFactory.GAMER_TABLE, "id = ?", new String[]{String.valueOf(gamer.getId())});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }

    }


    public void update(Gamer gamer) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("name", gamer.getName());
            cv.put("age", gamer.getAge());
            cv.put("sex", gamer.getSex());
            cv.put("desc", gamer.getDesc());
            cv.put("img", gamer.getImg());
            cv.put("score", gamer.getScore());
            cv.put("mvp", gamer.getMvp());
            db.update(SqlFactory.GAMER_TABLE, cv, "id = ?", new String[]{String.valueOf(gamer.getId())});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }

    }

    public ArrayList<Gamer> queryAll() {
        return queryAll(null);
    }

    public ArrayList<Gamer> queryAll(String order) {
        ArrayList<Gamer> gamers = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor c = db.query(SqlFactory.GAMER_TABLE, null, null, null, null, null, order, null);
            while (c.moveToNext()) {
                Gamer gamer = new Gamer();
                gamer.setId(c.getInt(c.getColumnIndex("id")));
                gamer.setName(c.getString(c.getColumnIndex("name")));
                gamer.setDesc(c.getString(c.getColumnIndex("desc")));
                gamer.setScore(c.getInt(c.getColumnIndex("score")));
                gamer.setMvp(c.getInt(c.getColumnIndex("mvp")));
                gamers.add(gamer);
            }
            db.setTransactionSuccessful();
            c.close();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return gamers;
    }


    public Gamer query(String id) {
        Gamer gamer = null;
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor c = db.query(SqlFactory.GAMER_TABLE, null, "id = ?", new String[]{id}, null, null, null);
            if (c.moveToNext()) {
                gamer = new Gamer();
                gamer.setId(c.getInt(c.getColumnIndex("id")));
                gamer.setName(c.getString(c.getColumnIndex("name")));
                gamer.setDesc(c.getString(c.getColumnIndex("desc")));
                gamer.setScore(c.getInt(c.getColumnIndex("score")));
                gamer.setMvp(c.getInt(c.getColumnIndex("mvp")));
            }
            db.setTransactionSuccessful();
            c.close();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return gamer;
    }

    public int queryCount() {
        int count = 0;
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor c = db.query(SqlFactory.GAMER_TABLE, null, null, null, null, null, null);
            count = c.getCount();
            db.setTransactionSuccessful();
            c.close();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return count;
    }

}
