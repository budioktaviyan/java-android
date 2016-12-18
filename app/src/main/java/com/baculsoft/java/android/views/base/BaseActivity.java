package com.baculsoft.java.android.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;

    protected abstract int getContentView();

    protected abstract void initComponents(final Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        initComponents(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected int getStatusBarHeight() {
        final int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");

        int result = 0;
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }
}