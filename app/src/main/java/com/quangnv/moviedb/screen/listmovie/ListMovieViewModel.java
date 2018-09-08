package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.support.v4.widget.SwipeRefreshLayout;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by quangnv on 04/09/2018
 */

public abstract class ListMovieViewModel extends BaseObservable
        implements MovieAdapter.ItemMovieListener, SwipeRefreshLayout.OnRefreshListener {

    private ItemMovieNavigator mItemMovieNavigator;
    protected Context mContext;
    protected MovieAdapter mMovieAdapter;
    protected MovieRepository mRepository;
    protected SchedulerProvider mSchedulerProvider;
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected int mPage = 1;
    public ObservableBoolean isRefresh;

    public ListMovieViewModel(Context context, MovieRepository repository, MovieAdapter adapter) {
        mContext = context;
        mRepository = repository;
        mMovieAdapter = adapter;
        adapter.setItemMovieListener(this);
        mMovieAdapter.setRepository(repository);
        isRefresh = new ObservableBoolean();
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        mItemMovieNavigator.onOpenMovieDetail(movie);
    }

    @Override
    public void onRefresh() {
        callApiGetListMovies(mPage);
        isRefresh.set(false);
        notifyChange();
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

    public int getColorRefreshLayout() {
        return R.color.color_accent;
    }

    public boolean getIsRefresh() {
        return isRefresh.get();
    }

    public abstract String getTitleToolbar();

    protected abstract void callApiGetListMovies(int page);


    protected void onStart() {
        callApiGetListMovies(mPage);
    }

    protected void onStop() {
        mCompositeDisposable.clear();
    }
}
