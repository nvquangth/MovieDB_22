package com.quangnv.moviedb.screen.listmovie;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 04/09/2018
 */

public class ItemMovieViewModel extends BaseObservable {

    public ObservableField<Movie> movieObservableField = new ObservableField<>();
    public ObservableInt mResId = new ObservableInt();
    private MovieAdapter.ItemMovieListener mItemMovieListener;
    private MovieAdapter.FavoriteListener mFavoriteListener;
    private MovieRepository mRepository;

    public ItemMovieViewModel(MovieRepository repository, MovieAdapter.ItemMovieListener listener,
                              MovieAdapter.FavoriteListener
            favoriteListener) {
        mRepository = repository;
        mItemMovieListener = listener;
        mFavoriteListener = favoriteListener;
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

    public void onFavoriteClick(View view) {
        mFavoriteListener.onFavoriteClick(movieObservableField.get());
    }

    public int getResId() {
        return mResId.get();
    }

    public void setIconFavorite() {
        mResId.set(R.drawable.ic_bookmark_blue_24dp);
        notifyChange();
    }

    public void removeIconFavorite() {
        mResId.set(R.drawable.ic_bookmark_border_blue_24dp);
        notifyChange();
    }

    public void addFavorite(Movie movie) {
        mRepository.addMovie(movie);
    }

    public void removeFavorite(Movie movie) {
        mRepository.removeMovie(movie);
    }

    public boolean checkFavorite(Movie movie) {
        return mRepository.isExistMovie(movie);
    }
}
