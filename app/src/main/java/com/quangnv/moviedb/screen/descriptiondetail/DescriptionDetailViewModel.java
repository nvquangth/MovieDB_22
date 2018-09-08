package com.quangnv.moviedb.screen.descriptiondetail;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.screen.ActionCloseNavigator;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.util.Constants;

/**
 * Created by quangnv on 08/09/2018
 */

public class DescriptionDetailViewModel extends BaseViewModel {

    public ObservableField<Movie> movieObservableField;
    private Movie mMovie;
    private ActionCloseNavigator mCloseNavigator;

    public DescriptionDetailViewModel(Movie movie, ActionCloseNavigator navigator) {
        movieObservableField = new ObservableField<>();
        mMovie = movie;
        mCloseNavigator = navigator;
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

    public String getGenres() {
        StringBuilder builder = new StringBuilder();
        if (mMovie.getGenres().isEmpty()) {
            return builder.toString();
        }
        int i;
        for (i = 0; i < mMovie.getGenres().size() - 1; i++) {
            builder.append(mMovie.getGenres().get(i).getName()).append(Constants.COMA_SEP);
        }
        builder.append(mMovie.getGenres().get(i).getName());
        return builder.toString();
    }

    public String getCompanies() {
        StringBuilder builder = new StringBuilder();
        if (mMovie.getCompanies().isEmpty()) {
            return builder.toString();
        }
        int i;
        for (i = 0; i < mMovie.getCompanies().size() - 1; i++) {
            builder.append(mMovie.getCompanies().get(i).getName()).append(Constants.COMA_SEP);
        }
        builder.append(mMovie.getCompanies().get(i).getName());
        return builder.toString();
    }

    public String getCountries() {
        StringBuilder builder = new StringBuilder();
        if (mMovie.getCountries().isEmpty()) {
            return builder.toString();
        }
        int i;
        for (i = 0; i < mMovie.getCountries().size() - 1; i++) {
            builder.append(mMovie.getCountries().get(i).getName()).append(Constants.COMA_SEP);
        }
        builder.append(mMovie.getCountries().get(i).getName());
        return builder.toString();
    }

    public String getLanguages() {
        StringBuilder builder = new StringBuilder();
        if (mMovie.getLanguages().isEmpty()) {
            return builder.toString();
        }
        int i;
        for (i = 0; i < mMovie.getLanguages().size() - 1; i++) {
            builder.append(mMovie.getLanguages().get(i).getName()).append(Constants.COMA_SEP);
        }
        builder.append(mMovie.getLanguages().get(i).getName());
        return builder.toString();
    }
}
