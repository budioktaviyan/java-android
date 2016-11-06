package com.baculsoft.java.android.internal.api;

import com.baculsoft.java.android.internal.api.response.TwitterSearchResponse;
import com.baculsoft.java.android.utils.IConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public interface IApi {

    @GET(IConstants.IUrls.TWITTER_SEARCH)
    Call<TwitterSearchResponse> getTwitterSearch(@Query("q") final String query,
                                                 @Query("t") final String typeSearch,
                                                 @Query("r") final String resultType,
                                                 @Query("n") final int maxId,
                                                 @Query("k") final String key);
}