package com.quangnv.moviedb.screen.genre;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.screen.BaseViewModel;

/**
 * Created by quangnv on 04/09/2018
 */

public class ItemGenreViewModel extends BaseViewModel {

    public ObservableField<Genre> genreObservableField = new ObservableField<>();
    private GenreAdapter.ItemGenreListener mItemGenreListener;

    public ItemGenreViewModel(GenreAdapter.ItemGenreListener listener) {
        mItemGenreListener = listener;
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void setGenre(Genre genre) {
        genreObservableField.set(genre);
    }

    public void onItemClick(View view) {
        if (mItemGenreListener == null || genreObservableField.get() == null) {
            return;
        } else {
            mItemGenreListener.onItemGenreClick(genreObservableField.get());
        }
    }
}
