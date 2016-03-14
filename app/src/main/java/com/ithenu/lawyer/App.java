package com.ithenu.lawyer;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by Mars on 2016/3/10.
 */
public class App extends Application{
    public static App lawyerApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        lawyerApplication = this;
    }
}
