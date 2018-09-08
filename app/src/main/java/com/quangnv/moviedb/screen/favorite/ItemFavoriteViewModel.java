package com.quangnv.moviedb.screen.favorite;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 08/09/2018
 */

public class ItemFavoriteViewModel extends BaseObservable {

    public ObservableField<Movie> movieObservableField;
    public ObservableInt mResId;
    private MovieAdapter.ItemMovieListener mItemMovieListener;
    private MovieAdapter.FavoriteListener mFavoriteListener;
    private MovieRepository mRepository;

    public ItemFavoriteViewModel(MovieRepository repository,
                                 MovieAdapter.ItemMovieListener itemMovieListener,
                                 MovieAdapter.FavoriteListener favoriteListener) {
        movieObservableField = new ObservableField<>();
        mResId = new ObservableInt();
        mRepository = repository;
        mItemMovieListener = itemMovieListener;
        mFavoriteListener = favoriteListener;
    }

    public void setMovie(Movie movie) {
        movieObservableField.set(movie);
    }

    public void onItemClick(View view) {
        if (mItemMovieListener == null || movieObservableField.get() == null) {
            return;
        }
        mItemMovieListener.onItemMovieClick(movieObservableField.get(), 0, false);
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

    public void removeFavorite(Movie movie) {
        mRepository.removeMovie(movie);
    }
}
