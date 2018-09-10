package com.quangnv.moviedb.screen.reviewdetail;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.screen.ActionCloseNavigator;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.moviedetail.ReviewAdapter;

/**
 * Created by quangnv on 08/09/2018
 */

public class ReviewDetailViewModel extends BaseViewModel {

    private ReviewAdapter mAdapter;
    private ActionCloseNavigator mCloseNavigator;
    public ObservableField<Movie> movieObservableField;

    public ReviewDetailViewModel(Movie movie, ReviewAdapter adapter, ActionCloseNavigator navigator) {
        mAdapter = adapter;
        mCloseNavigator = navigator;
        movieObservableField = new ObservableField<>();
        movieObservableField.set(movie);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void onCloseClick(View view) {
        mCloseNavigator.onCloseClick();
    }

    public ReviewAdapter getAdapter() {
        return mAdapter;
    }
}
