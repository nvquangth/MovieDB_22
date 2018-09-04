package com.quangnv.moviedb.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quangnv.moviedb.BuildConfig;
import com.quangnv.moviedb.data.model.GenreResult;
import com.quangnv.moviedb.data.source.GenreDataSource;
import com.quangnv.moviedb.data.source.remote.service.AppServiceClient;
import com.quangnv.moviedb.data.source.remote.service.NameApi;

import io.reactivex.Observable;

/**
 * Created by quangnv on 04/09/2018
 */

public class GenreRemoteDataSource implements GenreDataSource.RemoteDataSource {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static GenreRemoteDataSource sInstance;
    private NameApi mApi;

    private GenreRemoteDataSource(NameApi api) {
        mApi = api;
    }

    public static synchronized GenreRemoteDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new GenreRemoteDataSource(AppServiceClient.getInstance(context));
        }
        return sInstance;
    }

    @Override
    public Observable<GenreResult> getGenres() {
        return mApi.getGenres(API_KEY);
    }
}
