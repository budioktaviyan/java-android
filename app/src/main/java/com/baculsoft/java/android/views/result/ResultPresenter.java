package com.baculsoft.java.android.views.result;

import android.app.Activity;

import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.internal.data.local.TwitterSearchResult;
import com.baculsoft.java.android.utils.IConstants;
import com.baculsoft.java.android.views.base.IPresenter;

import org.parceler.Parcels;

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

    public List<TwitterSearchResult> getResults(final Activity activity) {
        return getTwitterSearch(activity).getTwitterSearchResults();
    }

    public int getCount(final Activity activity) {
        return getTwitterSearch(activity).getCount();
    }

    private TwitterSearch getTwitterSearch(final Activity activity) {
        return Parcels.unwrap(activity.getIntent().getParcelableExtra(IConstants.IBundles.TWITTER_SEARCH));
    }
}