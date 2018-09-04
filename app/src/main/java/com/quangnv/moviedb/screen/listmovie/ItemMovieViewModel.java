package com.quangnv.moviedb.screen.listmovie;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.screen.BaseViewModel;

/**
 * Created by quangnv on 04/09/2018
 */

public class ItemMovieViewModel extends BaseViewModel {

    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    private MovieAdapter.ItemMovieListener mItemMovieListener;

    public ItemMovieViewModel(MovieAdapter.ItemMovieListener listener) {
        mItemMovieListener = listener;
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
        } else {
            mItemMovieListener.onItemMovieClick(movieObservableField.get());
        }
    }
}
