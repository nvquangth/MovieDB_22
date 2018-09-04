package com.quangnv.moviedb.data.source;

import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.data.model.GenreResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by quangnv on 31/08/2018
 */

public interface GenreDataSource {

    interface LocalDataSource {

        void saveGenre(Genre genre);

        void deleteGenre(Genre genre);

        void deleteAllGenres();

        List<Genre> getAllGenres();

        Genre getGenre(int genreId);
    }

    interface RemoteDataSource {

        Observable<GenreResult> getGenres();
    }
}
