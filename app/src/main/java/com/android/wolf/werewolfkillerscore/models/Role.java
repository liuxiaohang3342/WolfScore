package com.android.wolf.werewolfkillerscore.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.wolf.werewolfkillerscore.customview.WheelView;

/**
 * Created by lxh on 2017/5/28.
 */

public class Role implements Parcelable {

    private long id;

    private String name;

    private String rules;

    private String img;

    private int skills;

    private int camp;

    private int score;

    public Role() {
    }

    protected Role(Parcel in) {
        id = in.readLong();
        name = in.readString();
        rules = in.readString();
        img = in.readString();
        skills = in.readInt();
        camp = in.readInt();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(rules);
        dest.writeString(img);
        dest.writeInt(skills);
        dest.writeInt(camp);
        dest.writeInt(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSkills() {
        return skills;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public void setSkills(int skills) {

        this.skills = skills;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
