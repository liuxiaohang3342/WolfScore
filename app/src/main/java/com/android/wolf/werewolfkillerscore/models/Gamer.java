package com.android.wolf.werewolfkillerscore.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lxh on 2017/5/28.
 */

public class Gamer implements Parcelable {

    private long id;
    private String name;
    private int age;
    private int sex;
    private String desc;
    private String img;
    private int score;
    private boolean isChecked;
    private int mvp;
    private Role role;

    public Gamer() {

    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    public void addMvp() {
        this.mvp++;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void reduceScore(int score) {
        this.score -= score;
        if (this.score < 0) {
            this.score = 0;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public Gamer(Parcel in) {
        id = in.readLong();
        name = in.readString();
        age = in.readInt();
        sex = in.readInt();
        desc = in.readString();
        img = in.readString();
        score = in.readInt();
        mvp = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeInt(sex);
        parcel.writeString(desc);
        parcel.writeString(img);
        parcel.writeInt(score);
        parcel.writeInt(mvp);
    }


    public static final Creator<Gamer> CREATOR = new Creator<Gamer>() {
        @Override
        public Gamer createFromParcel(Parcel in) {
            return new Gamer(in);
        }

        @Override
        public Gamer[] newArray(int size) {
            return new Gamer[size];
        }
    };
}
