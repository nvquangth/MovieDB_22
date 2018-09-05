package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by quangnv on 04/09/2018
 */

public abstract class ListMovieViewModel extends BaseViewModel
        implements MovieAdapter.ItemMovieListener {

    private ItemMovieNavigator mItemMovieNavigator;
    protected Context mContext;
    protected MovieAdapter mMovieAdapter;
    protected MovieRepository mRepository;
    protected SchedulerProvider mSchedulerProvider;
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected int mPage = 1;

    public ListMovieViewModel(Context context, MovieRepository repository, MovieAdapter adapter) {
        mContext = context;
        mRepository = repository;
        mMovieAdapter = adapter;
        adapter.setItemMovieListener(this);
    }

    @Override
    protected void onStart() {
        callApiGetListMovies(mPage);
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        mItemMovieNavigator.onOpenMovieDetail(movie);
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    public void setItemMovieNavigator(ItemMovieNavigator itemMovieNavigator) {
        mItemMovieNavigator = itemMovieNavigator;
    }

    public MovieAdapter getAdapter() {
        return mMovieAdapter;
    }

    public int getIconToolbar() {
        return R.drawable.ic_arrow_back_white_24dp;
    }

    public abstract String getTitleToolbar();

    protected abstract void callApiGetListMovies(int page);
}
