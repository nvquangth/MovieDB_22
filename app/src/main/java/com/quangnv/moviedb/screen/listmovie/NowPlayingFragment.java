package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 30/08/2018
 */

public class NowPlayingFragment extends MoviesFragment {

    public static NowPlayingFragment newInstance() {
        NowPlayingFragment fragment = new NowPlayingFragment();
        return fragment;
    }

    @Override
    protected ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                               MovieAdapter adapter) {
        return new NowPlayingViewModel(context, repository, adapter);
    }
}
