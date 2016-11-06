package com.baculsoft.java.android.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baculsoft.java.android.R;
import com.baculsoft.java.android.views.fragments.ResultFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_result)
    Toolbar toolbarResult;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mUnbinder = ButterKnife.bind(this);
        setToolbar();
        setFragment();
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        toolbarResult.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.ic_arrow_back));
        setSupportActionBar(toolbarResult);
    }

    private void setFragment() {
        final String tag = ResultFragment.class.getSimpleName();

        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            final Fragment fragment = ResultFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_result, fragment, tag).commit();
        }
    }
}