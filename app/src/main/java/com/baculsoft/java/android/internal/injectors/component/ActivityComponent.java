package com.baculsoft.java.android.internal.injectors.component;

import android.content.Context;

import com.baculsoft.java.android.internal.injectors.module.ActivityModule;
import com.baculsoft.java.android.internal.injectors.scope.ActivityContext;
import com.baculsoft.java.android.internal.injectors.scope.ActivityScope;
import com.baculsoft.java.android.views.main.MainActivity;
import com.baculsoft.java.android.views.result.ResultActivity;

import dagger.Component;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class }
)
public interface ActivityComponent {

    @ActivityContext
    Context getContext();

    void inject(MainActivity mainActivity);

    void inject(ResultActivity resultActivity);
}