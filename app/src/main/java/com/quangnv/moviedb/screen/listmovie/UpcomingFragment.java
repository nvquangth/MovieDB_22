package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;

import com.quangnv.moviedb.data.repository.MovieRepository;

/**
 * Created by quangnv on 30/08/2018
 */

public class UpcomingFragment extends MoviesFragment {

    public static UpcomingFragment newInstance() {
        UpcomingFragment fragment = new UpcomingFragment();
        return fragment;
    }

    @Override
    protected ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                               MovieAdapter adapter) {
        return new UpcomingViewModel(context, repository, adapter);
    }
}
