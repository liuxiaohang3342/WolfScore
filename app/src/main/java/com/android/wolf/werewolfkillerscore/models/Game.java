package com.android.wolf.werewolfkillerscore.models;

import java.util.ArrayList;

/**
 * Created by lxh on 2017/5/29.
 */

public class Game {

    private long id;
    private ArrayList<GamerRecord> mGamerToRole;
    private long mvp;
    private int win;
    private long time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public long getMvp() {
        return mvp;
    }

    public void setMvp(long mvp) {
        this.mvp = mvp;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ArrayList<GamerRecord> getmGamerToRole() {
        if(mGamerToRole == null){
            mGamerToRole = new ArrayList<>();
        }
        return mGamerToRole;
    }

    public void setmGamerToRole(ArrayList<GamerRecord> mGamerToRole) {
        this.mGamerToRole = mGamerToRole;
    }

    public void addGameRecord(GamerRecord record) {
        if (mGamerToRole == null) {
            mGamerToRole = new ArrayList<>();
        }
        mGamerToRole.add(record);
    }
}
