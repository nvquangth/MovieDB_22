package com.quangnv.moviedb.data.source;

import com.quangnv.moviedb.data.model.Cast;

import io.reactivex.Observable;

/**
 * Created by quangnv on 09/09/2018
 */

public interface PersonDataSource {
    interface RemoteDataSource {
        Observable<Cast> getCast(int personId);
    }
}
