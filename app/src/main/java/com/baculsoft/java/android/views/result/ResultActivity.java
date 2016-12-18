package com.baculsoft.java.android.views.result;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.baculsoft.java.android.App;
import com.baculsoft.java.android.R;
import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.internal.data.local.TwitterSearchResult;
import com.baculsoft.java.android.internal.injectors.component.ActivityComponent;
import com.baculsoft.java.android.internal.injectors.component.DaggerActivityComponent;
import com.baculsoft.java.android.internal.injectors.module.ActivityModule;
import com.baculsoft.java.android.utils.Dates;
import com.baculsoft.java.android.views.base.BaseActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ResultActivity extends BaseActivity implements ResultView {
    private ActivityComponent mComponent;

    @Inject
    ResultPresenter mPresenter;

    @Inject
    Dates mDates;

    @BindView(R.id.toolbar_result)
    Toolbar toolbarResult;

    @BindView(R.id.rv_result)
    RecyclerView rvResult;

    @BindString(R.string.text_search_result)
    String textSearchResult;

    @BindString(R.string.text_search_count)
    String textSearchCount;

    @Override
    protected int getContentView() {
        return R.layout.activity_result;
    }

    @Override
    protected void initComponents(Bundle savedInstanceState) {
        inject();
        onAttach();
    }

    @Override
    public void inject() {
        mComponent = DaggerActivityComponent.builder().applicationComponent(App.getComponent())
                                                      .activityModule(new ActivityModule(this))
                                                      .build();
        getComponent().inject(this);
    }

    @Override
    public void onAttach() {
        mPresenter.onAttach(this);

        final TwitterSearch twitterSearch = Parcels.unwrap(mPresenter.getTwitterSearch(this));
        final List<TwitterSearchResult> results = twitterSearch.getTwitterSearchResults();
        final int count = twitterSearch.getCount();

        toolbarResult.setPadding(0, getStatusBarHeight(), 0, 0);
        toolbarResult.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.ic_arrow_back));
        toolbarResult.setTitle(textSearchResult);
        toolbarResult.setSubtitle(String.format(textSearchCount, count));
        setSupportActionBar(toolbarResult);

        mPresenter.setAdapter(mDates, results);
    }

    @Override
    public void onDetach() {
        mPresenter.onDetach();
    }

    @Override
    public void onAdapterSetup(ResultAdapter adapter) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvResult.setLayoutManager(linearLayoutManager);
        rvResult.smoothScrollToPosition(rvResult.getBottom());
        rvResult.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        onDetach();
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

    public ActivityComponent getComponent() {
        return mComponent;
    }
}