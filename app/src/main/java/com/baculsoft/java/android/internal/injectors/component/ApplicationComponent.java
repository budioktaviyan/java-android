package com.baculsoft.java.android.internal.injectors.component;

import android.app.Application;
import android.content.Context;

import com.baculsoft.java.android.internal.data.remote.IApi;
import com.baculsoft.java.android.internal.injectors.module.ApplicationModule;
import com.baculsoft.java.android.internal.injectors.module.NetworkModule;
import com.baculsoft.java.android.internal.injectors.module.UtilityModule;
import com.baculsoft.java.android.internal.injectors.scope.ApplicationContext;
import com.baculsoft.java.android.utils.Commons;
import com.baculsoft.java.android.utils.Dates;
import com.baculsoft.java.android.utils.Keyboards;
import com.baculsoft.java.android.utils.Navigators;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        UtilityModule.class
})
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    void inject(Application application);

    // Network Module
    IApi getApi();

    // Utility Module
    Commons getCommonUtils();

    Dates getDates();

    Keyboards getKeyboards();

    Navigators getNavigators();
}