package com.quangnv.moviedb.data.repository;

import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.data.model.GenreResult;
import com.quangnv.moviedb.data.source.GenreDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by quangnv on 04/09/2018
 */

public class GenreRepository implements GenreDataSource.LocalDataSource,
        GenreDataSource.RemoteDataSource {

    private static GenreRepository sInstance;
    private GenreDataSource.LocalDataSource mLocalDataSource;
    private GenreDataSource.RemoteDataSource mRemoteDataSource;

    private GenreRepository(GenreDataSource.LocalDataSource localDataSource,
                            GenreDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized GenreRepository getInstance(GenreDataSource.LocalDataSource localDataSource,
                                                           GenreDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new GenreRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void saveGenre(Genre genre) {

    }

    @Override
    public void deleteGenre(Genre genre) {

    }

    @Override
    public void deleteAllGenres() {

    }

    @Override
    public List<Genre> getAllGenres() {
        return null;
    }

    @Override
    public Genre getGenre(int genreId) {
        return null;
    }

    @Override
    public Observable<GenreResult> getGenres() {
        return mRemoteDataSource.getGenres();
    }
}
