package com.quangnv.moviedb.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quangnv.moviedb.BuildConfig;
import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.data.source.PersonDataSource;
import com.quangnv.moviedb.data.source.remote.service.AppServiceClient;
import com.quangnv.moviedb.data.source.remote.service.NameApi;

import io.reactivex.Observable;

/**
 * Created by quangnv on 09/09/2018
 */

public class PersonRemoteDataSource implements PersonDataSource.RemoteDataSource {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static PersonRemoteDataSource sInstance;
    private NameApi mApi;

    private PersonRemoteDataSource(NameApi api) {
        mApi = api;
    }

    public static synchronized PersonRemoteDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new PersonRemoteDataSource(AppServiceClient.getInstance(context));
        }
        return sInstance;
    }

    @Override
    public Observable<Cast> getCast(int personId) {
        return mApi.getCast(personId, API_KEY);
    }
}
