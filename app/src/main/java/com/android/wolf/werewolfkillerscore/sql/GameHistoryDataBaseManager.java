package com.android.wolf.werewolfkillerscore.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.wolf.werewolfkillerscore.base.WolfApplication;
import com.android.wolf.werewolfkillerscore.models.Game;
import com.android.wolf.werewolfkillerscore.models.GamerRecord;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/28.
 */

public class GameHistoryDataBaseManager {

    private static GameHistoryDataBaseManager mInstance;

    public static GameHistoryDataBaseManager getInstance() {
        if (mInstance == null) {
            mInstance = new GameHistoryDataBaseManager();
        }
        return mInstance;
    }

    private Context mContext;
    private DatabaseHelper mHelper;

    private GameHistoryDataBaseManager() {
        mContext = WolfApplication.getInstance();
        mHelper = DatabaseHelper.getInstance(mContext);
    }

    public long insert(Game game) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues cv = new ContentValues();
            ArrayList<GamerRecord> gamerRecords = game.getmGamerToRole();
            if (gamerRecords != null) {
                for (int i = 0; i < gamerRecords.size(); i++) {
                    cv.put("grid" + (i + 1), gamerRecords.get(i).getId());
                }
            }
            cv.put("win", game.getWin());
            cv.put("mvp", game.getMvp());
            cv.put("time", game.getTime());
            long index = db.insert(SqlFactory.HISTORY_TABLE, null, cv);
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

    public void update(Game game) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            ArrayList<GamerRecord> gamerRecords = game.getmGamerToRole();
            ContentValues cv = new ContentValues();
            if (gamerRecords != null) {
                for (int i = 0; i < gamerRecords.size(); i++) {
                    cv.put("grid" + (i + 1), gamerRecords.get(i).getId());
                }
            }
            cv.put("win", game.getWin());
            cv.put("mvp", game.getMvp());
            cv.put("time", game.getTime());
            db.update(SqlFactory.HISTORY_TABLE, cv, "id = ?", new String[]{String.valueOf(game.getId())});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }

    public void delete(String id) {
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            db.delete(SqlFactory.HISTORY_TABLE, "id = ?", new String[]{id});
            db.setTransactionSuccessful();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
    }


    public Game query(String id) {
        Game game = null;
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query(SqlFactory.HISTORY_TABLE, null, "id = ?", new String[]{id}, null, null, null);
            if (cursor.moveToNext()) {
                game = new Game();
                game.setId(cursor.getLong(cursor.getColumnIndex("id")));
                game.setWin(cursor.getInt(cursor.getColumnIndex("win")));
                game.setMvp(cursor.getLong(cursor.getColumnIndex("mvp")));
                game.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                for (int i = 1; i <= 20; i++) {
                    int grid = cursor.getInt(cursor.getColumnIndex("grid" + i));
                    game.addGameRecord(GamerRecordDataBaseManager.getInstance().query(String.valueOf(grid)));
                }
            }
            db.setTransactionSuccessful();
            cursor.close();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return game;
    }


    public ArrayList<Game> queryAll() {
        ArrayList<Game> games = new ArrayList<>();
        SQLiteDatabase db = null;
        try {
            db = mHelper.getWritableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query(SqlFactory.HISTORY_TABLE, null, null, null, null, null, "time desc");
            while (cursor.moveToNext()) {
                Game game = new Game();
                game.setId(cursor.getLong(cursor.getColumnIndex("id")));
                game.setWin(cursor.getInt(cursor.getColumnIndex("win")));
                game.setMvp(cursor.getLong(cursor.getColumnIndex("mvp")));
                game.setTime(cursor.getLong(cursor.getColumnIndex("time")));
                for (int i = 1; i <= 20; i++) {
                    int grid = cursor.getInt(cursor.getColumnIndex("grid" + i));
                    game.addGameRecord(GamerRecordDataBaseManager.getInstance().query(String.valueOf(grid)));
                }
                games.add(game);
            }
            db.setTransactionSuccessful();
            cursor.close();
        } catch (Exception e) {
        } finally {
            try {
                db.endTransaction();
            } catch (Exception e) {
            }
        }
        return games;
    }

}
