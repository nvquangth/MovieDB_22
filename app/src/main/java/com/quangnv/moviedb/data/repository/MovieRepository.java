package com.quangnv.moviedb.data.repository;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.source.MovieDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by quangnv on 04/09/2018
 */

public class MovieRepository implements MovieDataSource.LocalDataSource,
        MovieDataSource.RemoteDataSource {

    private static MovieRepository sInstance;
    private MovieDataSource.LocalDataSource mLocalDataSource;
    private MovieDataSource.RemoteDataSource mRemoteDataSource;

    private MovieRepository(MovieDataSource.LocalDataSource localDataSource,
                            MovieDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static MovieRepository getInstance(MovieDataSource.LocalDataSource localDataSource,
                                              MovieDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new MovieRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void saveMovie(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    @Override
    public void deleteAllMovies() {

    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public Movie getMovie(int movieId) {
        return null;
    }

    @Override
    public Observable<MovieResult> getTopRated(int page) {
        return mRemoteDataSource.getTopRated(page);
    }

    @Override
    public Observable<MovieResult> getPopular(int page) {
        return mRemoteDataSource.getPopular(page);
    }

    @Override
    public Observable<MovieResult> getUpcoming(int page) {
        return mRemoteDataSource.getUpcoming(page);
    }

    @Override
    public Observable<MovieResult> getNowPlaying(int page) {
        return mRemoteDataSource.getNowPlaying(page);
    }

    @Override
    public Observable<Movie> getMovieDetail(int movieId) {
        return mRemoteDataSource.getMovieDetail(movieId);
    }

    @Override
    public Observable<MovieResult> searchMoviesByTitle(int page, String title) {
        return mRemoteDataSource.searchMoviesByTitle(page, title);
    }

    @Override
    public Observable<MovieResult> searchMoviesByCast(int page, int castId) {
        return mRemoteDataSource.searchMoviesByCast(page, castId);
    }

    @Override
    public Observable<MovieResult> searchMoviesByGenre(int page, int genreId) {
        return mRemoteDataSource.searchMoviesByGenre(page, genreId);
    }

    @Override
    public Observable<MovieResult> searchMoviesByGenres(int page, int... genreIds) {
        return mRemoteDataSource.searchMoviesByGenres(page, genreIds);
    }

    @Override
    public Observable<MovieResult> getRecommend(int page, int movieId) {
        return mRemoteDataSource.getRecommend(page, movieId);
    }
}
