package com.baculsoft.java.android.utils;

import com.baculsoft.java.android.internal.api.IApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Budi Oktaviyan Suryanto (budi@baculsoft.com)
 */
public final class Connections implements Interceptor {
    private static volatile Connections INSTANCE = null;

    public static synchronized Connections get() {
        if (INSTANCE == null) {
            INSTANCE = new Connections();
        }

        return INSTANCE;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        final Request.Builder requestBuilder = request.newBuilder().addHeader("Content-Type", "application/json");

        return chain.proceed(requestBuilder.build());
    }

    public IApi getApi() {
        return getRetrofit().create(IApi.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                           .client(getOkHttpClient())
                           .baseUrl(IConstants.IUrls.BASE_URL)
                           .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    private OkHttpClient getOkHttpClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                               .connectTimeout(30, TimeUnit.SECONDS)
                               .writeTimeout(15, TimeUnit.SECONDS)
                               .readTimeout(15, TimeUnit.SECONDS)
                               .addInterceptor(this)
                               .addInterceptor(loggingInterceptor).build();
    }
}