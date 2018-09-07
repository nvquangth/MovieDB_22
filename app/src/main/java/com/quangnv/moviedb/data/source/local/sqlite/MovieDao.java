package com.quangnv.moviedb.data.source.local.sqlite;

import com.quangnv.moviedb.data.model.Movie;

import java.util.List;

/**
 * Created by quangnv on 07/09/2018
 */

public interface MovieDao {

    Movie getMovie(int movieId);

    List<Movie> getMovies();

    void insertMovie(Movie movie);

    void deleteMovie(Movie movie);
}
