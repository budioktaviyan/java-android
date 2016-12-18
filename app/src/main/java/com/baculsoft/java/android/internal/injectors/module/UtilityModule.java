package com.baculsoft.java.android.internal.injectors.module;

import com.baculsoft.java.android.utils.Commons;
import com.baculsoft.java.android.utils.Dates;
import com.baculsoft.java.android.utils.Keyboards;
import com.baculsoft.java.android.utils.Navigators;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
public class UtilityModule {

    @Provides
    @Singleton
    public Commons provideCommonUtils() {
        return new Commons();
    }

    @Provides
    @Singleton
    public Dates provideDates() {
        return new Dates();
    }

    @Provides
    @Singleton
    public Keyboards provideKeyboards() {
        return new Keyboards();
    }

    @Provides
    @Singleton
    public Navigators provideNavigators() {
        return new Navigators();
    }
}