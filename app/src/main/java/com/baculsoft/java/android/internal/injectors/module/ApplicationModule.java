package com.baculsoft.java.android.internal.injectors.module;

import android.app.Application;
import android.content.Context;

import com.baculsoft.java.android.internal.injectors.scope.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(final Application application) {
        mApplication = application;
    }

    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context provideApplicationContext() {
        return mApplication;
    }
}