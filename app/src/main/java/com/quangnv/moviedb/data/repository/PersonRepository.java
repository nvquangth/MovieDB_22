package com.quangnv.moviedb.data.repository;

import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.data.source.PersonDataSource;

import io.reactivex.Observable;

/**
 * Created by quangnv on 09/09/2018
 */

public class PersonRepository implements PersonDataSource.RemoteDataSource {

    private static PersonRepository sInstance;
    private PersonDataSource.RemoteDataSource mRemoteDataSource;

    private PersonRepository(PersonDataSource.RemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized PersonRepository getInstance(
            PersonDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new PersonRepository(remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public Observable<Cast> getCast(int personId) {
        return mRemoteDataSource.getCast(personId);
    }
}
