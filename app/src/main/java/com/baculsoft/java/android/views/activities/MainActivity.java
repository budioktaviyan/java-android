package com.baculsoft.java.android.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.views.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbarMain);
        setFragment();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    private void setFragment() {
        final String tag = MainFragment.class.getSimpleName();

        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            final Fragment fragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment, tag).commit();
        }
    }
}