package com.quangnv.moviedb.screen.cast;

import android.databinding.ObservableField;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.repository.PersonRepository;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.moviedetail.SmallMovieAdapter;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 09/09/2018
 */

public class CastViewModel extends BaseViewModel implements SmallMovieAdapter.ItemMovieListener {

    private Cast mCast;
    private SmallMovieAdapter mMovieAdapter;
    private PersonRepository mPersonRepository;
    private MovieRepository mMovieRepository;
    private ItemMovieNavigator mItemMovieNavigator;
    private SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private int mPage = 1;
    public ObservableField<Cast> castObservableField;

    public CastViewModel(Cast cast) {
        mCast = cast;
        mCompositeDisposable = new CompositeDisposable();
        castObservableField = new ObservableField<>(mCast);
    }

    @Override
    protected void onStart() {
        callApiGetCast(mCast.getId());
        callApiGetMovie(mCast.getId());
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        mItemMovieNavigator.onOpenMovieDetail(movie);
    }

    public void setPersonRepository(PersonRepository repository) {
        mPersonRepository = repository;
    }

    public void setMovieRepository(MovieRepository repository) {
        mMovieRepository = repository;
    }

    public void setMovieAdapter(SmallMovieAdapter adapter) {
        mMovieAdapter = adapter;
        mMovieAdapter.setItemMovieListener(this);
    }

    public void setItemMovieNavigator(ItemMovieNavigator navigator) {
        mItemMovieNavigator = navigator;
    }

    public void setSchedulerProvider(SchedulerProvider provider) {
        mSchedulerProvider = provider;
    }

    public SmallMovieAdapter getAdapter() {
        return mMovieAdapter;
    }

    public int getIconToolbar() {
        return R.drawable.ic_arrow_back_white_24dp;
    }

    public String getTitleToolbar() {
        return mCast.getName();
    }

    private void callApiGetCast(int personId) {
        Disposable disposable = mPersonRepository.getCast(personId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<Cast>() {
                    @Override
                    public void accept(Cast cast) throws Exception {
                        castObservableField.set(cast);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void callApiGetMovie(int personId) {
        Disposable disposable = mMovieRepository.searchMoviesByCast(mPage, personId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<MovieResult>() {
                    @Override
                    public void accept(MovieResult movieResult) throws Exception {
                        mMovieAdapter.setMovies(movieResult.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
