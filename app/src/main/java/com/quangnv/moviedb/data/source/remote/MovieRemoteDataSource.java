package com.quangnv.moviedb.data.source.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.quangnv.moviedb.BuildConfig;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.source.MovieDataSource;
import com.quangnv.moviedb.data.source.remote.service.AppServiceClient;
import com.quangnv.moviedb.data.source.remote.service.NameApi;
import com.quangnv.moviedb.util.Constants;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by quangnv on 04/09/2018
 */

public class MovieRemoteDataSource implements MovieDataSource.RemoteDataSource {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static MovieRemoteDataSource sInstance;
    private NameApi mApi;

    private MovieRemoteDataSource(NameApi api) {
        mApi = api;
    }

    public static synchronized MovieRemoteDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new MovieRemoteDataSource(AppServiceClient.getInstance(context));
        }
        return sInstance;
    }

    @Override
    public Observable<MovieResult> getTopRated(int page) {
        return mApi.getTopRated(API_KEY, page);
    }

    @Override
    public Observable<MovieResult> getPopular(int page) {
        return mApi.getPopular(API_KEY, page);
    }

    @Override
    public Observable<MovieResult> getUpcoming(int page) {
        return mApi.getUpcoming(API_KEY, page);
    }

    @Override
    public Observable<MovieResult> getNowPlaying(int page) {
        return mApi.getNowPlaying(API_KEY, page);
    }

    @Override
    public Observable<Movie> getMovieDetail(int movieId) {
        return mApi.getMovie(movieId, API_KEY);
    }

    @Override
    public Observable<MovieResult> searchMoviesByTitle(int page, String title) {
        return mApi.searchMoviesByTitle(API_KEY, title, page);
    }

    @Override
    public Observable<MovieResult> searchMoviesByCast(int page, int castId) {
        return mApi.searchMoviesByCast(API_KEY, castId, page);
    }

    @Override
    public Observable<MovieResult> searchMoviesByGenre(int page, int genreId) {
        return mApi.searchMoviesByGenre(API_KEY, genreId, page);
    }

    @Override
    public Observable<MovieResult> searchMoviesByGenres(int page, int... genreIds) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < genreIds.length; i++) {
            map.put(Constants.PARAM_GENRE, genreIds[i]);
        }
        return mApi.searchMoviesByGenres(API_KEY, map, page);
    }

    @Override
    public Observable<MovieResult> getRecommend(int page, int movieId) {
        return mApi.getRecommend(movieId, API_KEY,  page);
    }
}
