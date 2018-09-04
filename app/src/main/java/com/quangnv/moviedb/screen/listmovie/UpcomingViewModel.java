package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.repository.MovieRepository;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 04/09/2018
 */

public class UpcomingViewModel extends ListMovieViewModel {
    public UpcomingViewModel(Context context, MovieRepository repository, MovieAdapter adapter) {
        super(context, repository, adapter);
    }

    @Override
    public String getTitleToolbar() {
        return mContext.getString(R.string.title_upcoming);
    }

    @Override
    protected void callApiGetListMovies(int page) {
        Disposable disposable = mRepository.getUpcoming(page)
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
