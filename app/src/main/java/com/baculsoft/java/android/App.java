package com.baculsoft.java.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}