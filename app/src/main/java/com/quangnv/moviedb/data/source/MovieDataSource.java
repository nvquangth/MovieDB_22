package com.quangnv.moviedb.data.source;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by quangnv on 31/08/2018
 */

public interface MovieDataSource {

    interface LocalDataSource {

        void addMovie(Movie movie);

        void removeMovie(Movie movie);

        boolean isExistMovie(Movie movie);

        List<Movie> getMovies();

        Movie getMovie(int movieId);
    }

    interface RemoteDataSource {

        Observable<MovieResult> getTopRated(int page);

        Observable<MovieResult> getPopular(int page);

        Observable<MovieResult> getUpcoming(int page);

        Observable<MovieResult> getNowPlaying(int page);

        Observable<Movie> getMovieDetail(int movieId);

        Observable<MovieResult> searchMoviesByTitle(int page, String title);

        Observable<MovieResult> searchMoviesByCast(int page, int castId);

        Observable<MovieResult> searchMoviesByGenre(int page, int genreId);

        Observable<MovieResult> searchMoviesByGenres(int page, int... genreIds);

        Observable<MovieResult> getRecommend(int page, int movieId);
    }
}
