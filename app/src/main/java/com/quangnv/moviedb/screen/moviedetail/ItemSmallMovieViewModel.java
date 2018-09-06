package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.screen.BaseViewModel;

/**
 * Created by quangnv on 06/09/2018
 */

public class ItemSmallMovieViewModel extends BaseViewModel {

    public ObservableField<Movie> movieObservableField;
    private SmallMovieAdapter.ItemMovieListener mItemMovieListener;

    public ItemSmallMovieViewModel(SmallMovieAdapter.ItemMovieListener listener) {
        mItemMovieListener = listener;
        movieObservableField = new ObservableField<>();
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void setMovie(Movie movie) {
        movieObservableField.set(movie);
    }

    public void onItemClick(View view) {
        if (mItemMovieListener == null || movieObservableField.get() == null) {
            return;
        }
        mItemMovieListener.onItemMovieClick(movieObservableField.get());
    }
}
