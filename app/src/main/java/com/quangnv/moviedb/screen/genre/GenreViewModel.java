package com.quangnv.moviedb.screen.genre;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.data.model.GenreResult;
import com.quangnv.moviedb.data.repository.GenreRepository;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 04/09/2018
 */

public class GenreViewModel extends BaseViewModel implements GenreAdapter.ItemGenreListener {

    private static final int SPAN_COUNT_LAYOUT = 2;
    private Context mContext;
    private GenreAdapter mAdapter;
    private List<Genre> mGenres;
    private GenreRepository mGenreRepository;
    private SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private GenreNavigator mGenreNavigator;

    public GenreViewModel(Context context, GenreRepository repository, GenreAdapter adapter) {
        mContext = context;
        mGenreRepository = repository;
        mAdapter = adapter;
        mAdapter.setItemGenreListener(this);
        mGenres = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        callApiGetListGenres();
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onItemGenreClick(Genre genre) {
        addOrRemoveGenre(genre);
    }

    public void setGenreNavigator(GenreNavigator navigator) {
        mGenreNavigator = navigator;
    }

    public void onGotoGenresClick(View view) {
        if (mGenres.isEmpty()) {
            Snackbar.make(view, R.string.noti_choose_genres, Snackbar.LENGTH_SHORT).show();
        } else {
            mGenreNavigator.openMoviesFragment(GenreMovieFragment.newInstance(mGenres));
        }
    }

    public void setSchedulerProvider(SchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    public GenreAdapter getAdapter() {
        return mAdapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(mContext, SPAN_COUNT_LAYOUT);
        return layoutManager;
    }

    private void callApiGetListGenres() {
        Disposable disposable = mGenreRepository.getGenres()
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<GenreResult>() {
                    @Override
                    public void accept(GenreResult genreResult) throws Exception {
                        mAdapter.setGenres(genreResult.getGenres());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void addOrRemoveGenre(Genre genre) {
        if (mGenres.contains(genre)) {
            mGenres.remove(genre);
        } else {
            mGenres.add(genre);
        }
    }
}
