package com.baculsoft.java.android.views.main;

import android.text.TextUtils;

import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.internal.data.local.TwitterSearchResult;
import com.baculsoft.java.android.internal.data.remote.IApi;
import com.baculsoft.java.android.internal.data.remote.TwitterSearchResponse;
import com.baculsoft.java.android.utils.IConstants;
import com.baculsoft.java.android.views.base.IPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public class MainPresenter implements IPresenter<MainView> {
    private MainView mView;
    private Subscription mSubscription;

    @Inject
    IApi mApi;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void onAttach(MainView view) {
        mView = view;
    }

    @Override
    public void onDetach() {
        mView = null;
        safeUnsubscribe(mSubscription);
    }

    public void validateSearch(final String query, final String page) {
        if (!TextUtils.isEmpty(query) && !TextUtils.isEmpty(page)) {
            mView.onValidate();
        }
    }

    public void getTwitterSearch(final String query,
                                 final String page,
                                 final String searchType,
                                 final String resultType) {
        final String key = IConstants.IKeys.API_KEY;

        int maxId = 0;
        if (!TextUtils.isEmpty(page)) {
            maxId = Integer.parseInt(page);
        }

        mView.onShowProgressDialog();
        safeUnsubscribe(mSubscription);
        mSubscription = mApi.getTwitterSearch(query, searchType, resultType, maxId, key).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<TwitterSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        getView().onDismissProgressDialog();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        getView().onDismissProgressDialog();
                        getView().onConnectionError();
                    }

                    @Override
                    public void onNext(TwitterSearchResponse twitterSearchResponse) {
                        final List<TwitterSearchResponse.Statuses> statuses = twitterSearchResponse.statuses;
                        if (statuses.size() != 0) {
                            final TwitterSearchResponse.SearchMetadata searchMetadata = twitterSearchResponse.searchMetadata;
                            final List<TwitterSearchResult> results = getTwitterSearchResult(statuses);
                            final int count = searchMetadata.count;

                            final TwitterSearch twitterSearch = new TwitterSearch();
                            twitterSearch.setTwitterSearchResults(results);
                            twitterSearch.setCount(count);

                            getView().onNavigateView(twitterSearch);
                        } else {
                            getView().onShowError();
                        }
                    }
                });
    }

    public MainView getView() {
        return mView;
    }

    public List<TwitterSearchResult> getTwitterSearchResult(final List<TwitterSearchResponse.Statuses> statuses) {
        final List<TwitterSearchResult> results = new ArrayList<>();

        for (TwitterSearchResponse.Statuses status : statuses) {
            TwitterSearchResult data = new TwitterSearchResult();
            data.setText(status.text);
            data.setCreatedAt(status.createdAt);
            results.add(data);
        }

        return results;
    }

    private void safeUnsubscribe(final Subscription subscription) {
        if (null != subscription && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}