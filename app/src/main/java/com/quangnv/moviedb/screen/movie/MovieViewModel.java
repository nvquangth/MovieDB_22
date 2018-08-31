package com.quangnv.moviedb.screen.movie;

import android.view.View;

import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.listmovie.NowPlayingFragment;
import com.quangnv.moviedb.screen.listmovie.PopularFragment;
import com.quangnv.moviedb.screen.listmovie.TopRatedFragment;
import com.quangnv.moviedb.screen.listmovie.UpcomingFragment;

/**
 * Created by quangnv on 30/08/2018
 */

public class MovieViewModel extends BaseViewModel {

    private MovieNavigator mNavigator;

    public MovieViewModel() {

    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void setNavigator(MovieNavigator navigator) {
        mNavigator = navigator;
    }

    public void onTopRatedClick(View view) {
        mNavigator.openMoviesFragment(TopRatedFragment.newInstance());
    }

    public void onPopularClick(View view) {
        mNavigator.openMoviesFragment(PopularFragment.newInstance());
    }

    public void onUpcomingClick(View view) {
        mNavigator.openMoviesFragment(UpcomingFragment.newInstance());
    }

    public void onNowPlayingClick(View view) {
        mNavigator.openMoviesFragment(NowPlayingFragment.newInstance());
    }
}
