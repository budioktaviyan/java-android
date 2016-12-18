package com.baculsoft.java.android;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baculsoft.java.android.internal.injectors.component.ApplicationComponent;
import com.baculsoft.java.android.internal.injectors.component.DaggerApplicationComponent;
import com.baculsoft.java.android.internal.injectors.module.ApplicationModule;
import com.baculsoft.java.android.internal.injectors.module.NetworkModule;
import com.baculsoft.java.android.internal.injectors.module.UtilityModule;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class App extends Application {
    private static volatile ApplicationComponent component;

    public static synchronized ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                                                        .networkModule(new NetworkModule())
                                                        .utilityModule(new UtilityModule())
                                                        .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}