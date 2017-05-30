package com.android.wolf.werewolfkillerscore.sql;

/**
 * Created by lxh on 2017/5/28.
 */

public class SqlFactory {

    //玩家列表
    public static final String GAMER_TABLE = "gamer";
    public static final String CREATE_GAMER_LIST = "CREATE TABLE IF NOT EXISTS [gamer] (" +
            "[id] INTEGER   PRIMARY KEY AUTOINCREMENT, " +
            "[name] TEXT   UNIQUE, " +
            "[age] INTEGER, " +
            "[sex] INTEGER, " +
            "[score] INTEGER, " +
            "[desc] TEXT, " +
            "[mvp] INTEGER, " +
            "[img] TEXT) ";

    //角色列表
    public static final String ROLE_TABLE = "role";
    public static final String CREATE_ROLE_LIST = "CREATE TABLE IF NOT EXISTS [role] (" +
            "[id] INTEGER   PRIMARY KEY AUTOINCREMENT, " +
            "[name] TEXT  , " +
            "[rules] TEXT, " +
            "[skills] INTEGER, " +
            "[camp] INTEGER, " +
            "[score] INTEGER, " +
            "[img] TEXT) ";


    //玩家比赛记录
    public static final String GAMER_HISTORY_TABLE = "gamer_history";
    public static final String CREATE_GAMER_HISTORY = "CREATE TABLE IF NOT EXISTS [gamer_history] (" +
            "[id] INTEGER   PRIMARY KEY AUTOINCREMENT, " +
            "[gid] INTEGER  , " +
            "[uid] INTEGER  , " +
            "[rid] INTEGER  , " +
            "[uname] TEXT, " +
            "[role] TEXT, " +
            "[score] INTEGER) ";


    //比赛记录
    public static final String HISTORY_TABLE = "history";
    public static final String CREATE_HISTORY = "CREATE TABLE IF NOT EXISTS [history] (" +
            "[id] INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "[grid1] INTEGER, " +
            "[grid2] INTEGER  , " +
            "[grid3] INTEGER  , " +
            "[grid4] INTEGER  , " +
            "[grid5] INTEGER  , " +
            "[grid6] INTEGER  , " +
            "[grid7] INTEGER  , " +
            "[grid8] INTEGER  , " +
            "[grid9] INTEGER  , " +
            "[grid10] INTEGER  , " +
            "[grid11] INTEGER  , " +
            "[grid12] INTEGER  , " +
            "[grid13] INTEGER  , " +
            "[grid14] INTEGER  , " +
            "[grid15] INTEGER  , " +
            "[grid16] INTEGER  , " +
            "[grid17] INTEGER  , " +
            "[grid18] INTEGER  , " +
            "[grid19] INTEGER  , " +
            "[grid20] INTEGER  ," +
            "[win] INTEGER  ," +
            "[mvp] INTEGER  ," +
            "[time] INTEGER  ) ";

}
