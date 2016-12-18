package com.baculsoft.java.android.internal.injectors.module;

import com.baculsoft.java.android.internal.data.remote.IApi;
import com.baculsoft.java.android.utils.IConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Budi Oktaviyan Suryanto (budioktaviyans@gmail.com)
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public IApi provideApi(final Retrofit retrofit) {
        return retrofit.create(IApi.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        final String baseUrl = IConstants.IUrls.BASE_URL;

        return new Retrofit.Builder().client(okHttpClient)
                                     .baseUrl(baseUrl)
                                     .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                     .addConverterFactory(JacksonConverterFactory.create()).build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        final Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(final Chain chain) throws IOException {
                final Request request = chain.request();
                final Request.Builder builder = request.newBuilder().addHeader("Content-Type", "application/json");

                return chain.proceed(builder.build());
            }
        };

        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                                         .writeTimeout(15, TimeUnit.SECONDS)
                                         .readTimeout(15, TimeUnit.SECONDS)
                                         .retryOnConnectionFailure(true)
                                         .addInterceptor(interceptor)
                                         .addInterceptor(httpLoggingInterceptor).build();
    }
}