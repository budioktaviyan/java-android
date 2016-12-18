package com.baculsoft.java.android.internal.data.remote;

import com.baculsoft.java.android.utils.IConstants;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
public interface IApi {

    @GET(IConstants.IUrls.IApis.TWITTER_SEARCH)
    Observable<TwitterSearchResponse> getTwitterSearch(@Query("q") final String query,
                                                       @Query("t") final String typeSearch,
                                                       @Query("r") final String resultType,
                                                       @Query("n") final int maxId,
                                                       @Query("k") final String key);
}