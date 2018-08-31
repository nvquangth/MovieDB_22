package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class MovieResult implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Movie> mMovies;

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
