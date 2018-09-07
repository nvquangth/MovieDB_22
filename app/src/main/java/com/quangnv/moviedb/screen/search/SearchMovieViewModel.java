package com.quangnv.moviedb.screen.search;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.util.common.StringUtil;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 06/09/2018
 */

public class SearchMovieViewModel extends BaseViewModel implements TextView.OnEditorActionListener,
        MovieAdapter.ItemMovieListener {

    public ObservableField<String> mQuery;
    public ObservableBoolean mIsStartedLoad;
    public ObservableBoolean mIsFailedLoad;
    private SearchActivity mActivity;
    private MovieRepository mRepository;
    private MovieAdapter mAdapter;
    private SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private ItemMovieNavigator mNavigator;
    private int mPage = 1;

    public SearchMovieViewModel(SearchActivity activity, MovieRepository repository,
                                MovieAdapter adapter, ItemMovieNavigator navigator) {
        mActivity = activity;
        mRepository = repository;
        mAdapter = adapter;
        mNavigator = navigator;
        mCompositeDisposable = new CompositeDisposable();
        mQuery = new ObservableField<>();
        mIsStartedLoad = new ObservableBoolean(false);
        mIsFailedLoad = new ObservableBoolean(false);
        adapter.setItemMovieListener(this);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            mQuery.set(textView.getText().toString());
            if (!StringUtil.isEmpty(mQuery.get())) {
                callApiSearchMovie(mPage, mQuery.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        mNavigator.onOpenMovieDetail(movie);
    }

    public void onReloadClick(View view) {
        if (!StringUtil.isEmpty(mQuery.get())) {
            callApiSearchMovie(mPage, mQuery.get());
        }
    }

    public void onBack(View view) {
        mActivity.onBackPressed();
    }

    public void onClearQuery(View view) {
        mQuery.set("");
    }

    public void setSchedulerProvider(SchedulerProvider provider) {
        mSchedulerProvider = provider;
    }

    public MovieAdapter getAdapter() {
        return mAdapter;
    }

    private void callApiSearchMovie(int page, String title) {
        Disposable disposable = mRepository.searchMoviesByTitle(page, title)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mIsStartedLoad.set(true);
                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mIsStartedLoad.set(false);
                    }
                })
                .subscribe(new Consumer<MovieResult>() {
                    @Override
                    public void accept(MovieResult movieResult) throws Exception {
                        mIsFailedLoad.set(false);
                        mAdapter.setMovies(movieResult.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mIsFailedLoad.set(true);
                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
