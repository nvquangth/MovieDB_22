package com.quangnv.moviedb.data.source.remote.service;

import com.quangnv.moviedb.data.model.GenreResult;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by quangnv on 03/09/2018
 */

public interface NameApi {

    @GET("/3/movie/top_rated")
    Observable<MovieResult> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/popular")
    Observable<MovieResult> getPopular(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/upcoming")
    Observable<MovieResult> getUpcoming(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/now_playing")
    Observable<MovieResult> getNowPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/{movie_id}?append_to_response=videos,credits,reviews")
    Observable<Movie> getMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("/3/search/movie")
    Observable<MovieResult> searchMoviesByTitle(@Query("api_key") String apiKey,
                                                @Query("query") String title,
                                                @Query("page") int page);

    @GET("/3/discover/movie")
    Observable<MovieResult> searchMoviesByCast(@Query("api_key") String apiKey,
                                               @Query("with_cast") int castId,
                                               @Query("page") int page);

    @GET("/3/discover/movie")
    Observable<MovieResult> searchMoviesByGenre(@Query("api_key") String apiKey,
                                                @Query("with_genres") int genreId,
                                                @Query("page") int page);

    @GET("/3/discover/movie")
    Observable<MovieResult> searchMoviesByGenres(@Query("api_key") String apiKey,
                                                 @QueryMap Map<String, Integer> genres,
                                                 @Query("page") int page);

    @GET("/3/movie/{movie_id}/recommendations")
    Observable<MovieResult> getRecommend(@Path("movie_id") int movieId,
                                         @Query("api_key") String apiKey,
                                         @Query("page") int page);

    @GET("/3/genre/movie/list")
    Observable<GenreResult> getGenres(@Query("api_key") String apiKey);
}
