package com.baculsoft.java.android.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.baculsoft.java.android.internal.pojo.TwitterSearch;
import com.baculsoft.java.android.views.activities.ResultActivity;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public final class Navigators {
    private static volatile Navigators INSTANCE = null;

    public static synchronized Navigators get() {
        if (INSTANCE == null) {
            INSTANCE = new Navigators();
        }

        return INSTANCE;
    }

    public void openResultActivity(final Context context, final TwitterSearch twitterSearch) {
        final Intent intent = new Intent(context, ResultActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(IConstants.IBundles.TWITTER_SEARCH, twitterSearch);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}