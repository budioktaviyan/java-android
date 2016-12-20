package com.baculsoft.java.android.views.main;

import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.views.base.IView;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface MainView extends IView {
    void onShowProgressDialog();

    void onDismissProgressDialog();

    void onShowError();

    void onConnectionError();

    void onNavigateView(TwitterSearch twitterSearch);
}