package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 30/08/2018
 */

public class TopRatedFragment extends MoviesFragment {

    public static TopRatedFragment newInstance() {
        TopRatedFragment fragment = new TopRatedFragment();
        return fragment;
    }

    @Override
    protected ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                               MovieAdapter adapter) {
        return new TopRateViewModel(context, repository, adapter);
    }
}
