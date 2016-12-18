package com.baculsoft.java.android.views.result;

import android.app.Activity;
import android.os.Parcelable;

import com.baculsoft.java.android.internal.data.local.TwitterSearchResult;
import com.baculsoft.java.android.utils.Dates;
import com.baculsoft.java.android.utils.IConstants;
import com.baculsoft.java.android.views.base.IPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class ResultPresenter implements IPresenter<ResultView> {
    private ResultView mView;

    @Inject
    public ResultPresenter() {
    }

    @Override
    public void onAttach(ResultView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
    }

    public Parcelable getTwitterSearch(final Activity activity) {
        return activity.getIntent().getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH);
    }

    public void setAdapter(final Dates dates, final List<TwitterSearchResult> results) {
        final ResultAdapter adapter = new ResultAdapter(dates, results);
        mView.onAdapterSetup(adapter);
    }
}