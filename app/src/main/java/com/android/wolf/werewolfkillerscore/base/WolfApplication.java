package com.android.wolf.werewolfkillerscore.base;

import android.app.Application;
import android.util.Log;

/**
 * Created by lxh on 2017/5/29.
 */

public class WolfApplication extends Application {

    private static WolfApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("lxh","Application onCreate");
        mApplication = this;
    }

    public static WolfApplication getInstance() {
        return mApplication;
    }
}
