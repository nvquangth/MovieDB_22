package com.quangnv.moviedb.screen.genre;

import android.content.Context;

import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.listmovie.ListMovieViewModel;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.util.Constants;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 05/09/2018
 */

public class GenreMovieViewModel extends ListMovieViewModel {

    private List<Genre> mGenres;

    public GenreMovieViewModel(Context context, MovieRepository repository, List<Genre> genres,
                               MovieAdapter adapter) {
        super(context, repository, adapter);
        mGenres = genres;
    }

    @Override
    public String getTitleToolbar() {
        return genTitle();
    }

    @Override
    protected void callApiGetListMovies(int page) {

        Disposable disposable = mRepository.searchMoviesByGenres(page, genIds())
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

    private String genTitle() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mGenres.size() - 1; i++) {
            builder.append(mGenres.get(i).getName()).append(Constants.COMA_SEP);
        }
        builder.append(mGenres.get(mGenres.size() - 1).getName());
        return builder.toString();
    }

    private int[] genIds() {
        int genreIds[] = new int[mGenres.size()];
        for (int i = 0; i < mGenres.size(); i++) {
            genreIds[i] = mGenres.get(i).getId();
        }
        return genreIds;
    }
}
