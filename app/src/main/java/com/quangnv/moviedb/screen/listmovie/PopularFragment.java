package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 30/08/2018
 */

public class PopularFragment extends MoviesFragment {

    public static PopularFragment newInstance() {
        PopularFragment fragment = new PopularFragment();
        return fragment;
    }

    @Override
    protected ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                               MovieAdapter adapter) {
        return new PopularViewModel(context, repository, adapter);
    }
}
