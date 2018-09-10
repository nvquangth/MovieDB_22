package com.quangnv.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.ItemMovieNavigator;

import java.util.List;

/**
 * Created by quangnv on 08/09/2018
 */

public class FavoriteViewModel extends BaseObservable implements MovieAdapter.ItemMovieListener,
        SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private MovieRepository mRepository;
    private MovieAdapter mAdapter;
    private ItemMovieNavigator mNavigator;
    public ObservableBoolean isRefresh;

    public FavoriteViewModel(Context context, MovieRepository repository, MovieAdapter adapter,
                             ItemMovieNavigator navigator) {
        mContext = context;
        mRepository = repository;
        mAdapter = adapter;
        mNavigator = navigator;
        isRefresh = new ObservableBoolean();
        mAdapter.setItemMovieListener(this);
    }

    @Override
    public void onItemMovieClick(Movie movie, int position, boolean isFavoriteClick) {
        if (isFavoriteClick) {
            mAdapter.removeMovie(movie, position);
        } else {
            mNavigator.onOpenMovieDetail(movie);
        }
    }

    @Override
    public void onRefresh() {
        getMovies();
        isRefresh.set(false);
        notifyChange();
    }

    public void onStart() {
        getMovies();
    }

    public MovieAdapter getAdapter() {
        return mAdapter;
    }

    public String getTitleToolbar() {
        return mContext.getString(R.string.app_name);
    }

    public int getColorRefreshLayout() {
        return R.color.color_accent;
    }

    public boolean getIsRefresh() {
        return isRefresh.get();
    }

    private void getMovies() {
        List<Movie> movies = mRepository.getMovies();
        mAdapter.setMovies(movies);
    }
}
