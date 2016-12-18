package com.baculsoft.java.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.baculsoft.java.android.internal.data.local.TwitterSearch;
import com.baculsoft.java.android.views.result.ResultActivity;

import org.parceler.Parcels;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public final class Navigators {
    private static final String TAG = Navigators.class.getSimpleName();

    public void openResultActivity(final Activity activity, final TwitterSearch twitterSearch) {
        try {
            final Intent intent = new Intent(activity, ResultActivity.class);
            final Bundle bundle = new Bundle();
            bundle.putParcelable(IConstants.IBundles.TWITTER_SEARCH, Parcels.wrap(twitterSearch));
            intent.putExtras(bundle);
            activity.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}