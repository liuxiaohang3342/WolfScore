package com.android.wolf.werewolfkillerscore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.wolf.werewolfkillerscore.base.WolfApplication;
import com.android.wolf.werewolfkillerscore.models.Gamer;
import com.android.wolf.werewolfkillerscore.models.GamerRecord;
import com.android.wolf.werewolfkillerscore.models.Role;

/**
 * Created by lxh on 2017/5/28.
 */

public class GamerRecordDataBaseManager {


    private static GamerRecordDataBaseManager mInstance;

    public static GamerRecordDataBaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new GamerRecordDataBaseManager();
        }
        return mInstance;
    }

    private Context mContext;
    private DatabaseHelper mHelper;

    private GamerRecordDataBaseManager() {
        mContext = WolfApplication.getInstance();
        mHelper = DatabaseHelper.getInstance(mContext);
    }

    public long insert(GamerRecord record) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            cv.put("gid", record.getGid());
            Gamer gamer = record.getGamer();
            if (gamer != null) {
                cv.put("uid", gamer.getId());
                cv.put("uname", gamer.getName());
            }

            Role role = record.getRole();
            if (role != null) {
                cv.put("rid", role.getId());
                cv.put("role", role.getName());
            }
            cv.put("score", record.getScore());
            long index = db.insert(SqlFactory.GAMER_HISTORY_TABLE, null, cv);
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


    public GamerRecord query(String id) {
        GamerRecord gamerRecord = null;
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query(SqlFactory.GAMER_HISTORY_TABLE,  null, "id = ?", new String[]{id}, null, null, null);
            if (cursor.moveToNext()){
                gamerRecord = new GamerRecord();
                gamerRecord.setId(cursor.getLong(cursor.getColumnIndex("id")));
                gamerRecord.setGid(cursor.getLong(cursor.getColumnIndex("gid")));
                gamerRecord.setScore(cursor.getInt(cursor.getColumnIndex("score")));
                long uid = cursor.getLong(cursor.getColumnIndex("uid"));
                gamerRecord.setGamer(GamerDataBaseManager.getInstance().query(String.valueOf(uid)));
                long rid = cursor.getLong(cursor.getColumnIndex("rid"));
                gamerRecord.setRole(RoleDataBaseManager.getInstance().query(String.valueOf(rid)));
            }
            db.setTransactionSuccessful();
            return gamerRecord;
        } catch (Exception e) {
            return gamerRecord;
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }

}
