package com.android.wolf.werewolfkillerscore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.wolf.werewolfkillerscore.base.WolfApplication;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.Role;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class RoleDataBaseManager {


    private static RoleDataBaseManager mInstance;

    public static RoleDataBaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new RoleDataBaseManager();
        }
        return mInstance;
    }


    private Context mContext;
    private DatabaseHelper mHelper;

    private RoleDataBaseManager() {
        mContext = WolfApplication.getInstance();
        mHelper = DatabaseHelper.getInstance(mContext);
    }

    public long insert(Role role) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("name", role.getName());
            cv.put("rules", role.getRules());
            cv.put("img", role.getImg());
            cv.put("skills", role.getSkills());
            cv.put("score", role.getScore());
            cv.put("camp", role.getCamp());
            long index = db.insert(SqlFactory.ROLE_TABLE, null, cv);
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

    public void update(Role role) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("name", role.getName());
            cv.put("rules", role.getRules());
            cv.put("img", role.getImg());
            cv.put("skills", role.getSkills());
            cv.put("score", role.getScore());
            cv.put("camp", role.getCamp());
            db.update(SqlFactory.ROLE_TABLE, cv, "id = ?", new String[]{String.valueOf(role.getId())});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }


    public void delete(Role role) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(SqlFactory.ROLE_TABLE, "id = ?", new String[]{String.valueOf(role.getId())});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }


    public ArrayList<Role> queryAll() {
        ArrayList<Role> roles = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor c = db.query(SqlFactory.ROLE_TABLE, null, null, null, null, null, null, null);
            while (c.moveToNext()) {
                Role role = new Role();
                role.setId(c.getInt(c.getColumnIndex("id")));
                role.setName(c.getString(c.getColumnIndex("name")));
                role.setRules(c.getString(c.getColumnIndex("rules")));
                role.setImg(c.getString(c.getColumnIndex("img")));
                role.setSkills(c.getInt(c.getColumnIndex("skills")));
                role.setScore(c.getInt(c.getColumnIndex("score")));
                role.setCamp(c.getInt(c.getColumnIndex("camp")));
                roles.add(role);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return roles;
    }

    public Role query(String id) {
        Role role = null;
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor c = db.query(SqlFactory.ROLE_TABLE, null, "id = ?", new String[]{id}, null, null, null);
            if (c.moveToNext()) {
                role = new Role();
                role.setId(c.getInt(c.getColumnIndex("id")));
                role.setName(c.getString(c.getColumnIndex("name")));
                role.setRules(c.getString(c.getColumnIndex("rules")));
                role.setImg(c.getString(c.getColumnIndex("img")));
                role.setSkills(c.getInt(c.getColumnIndex("skills")));
                role.setScore(c.getInt(c.getColumnIndex("score")));
                role.setCamp(c.getInt(c.getColumnIndex("camp")));
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
        return role;
    }


}
