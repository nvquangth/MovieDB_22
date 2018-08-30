package com.quangnv.moviedb.screen.home;

import android.support.v7.app.AppCompatActivity;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.genre.GenreFragment;
import com.quangnv.moviedb.screen.movie.MovieFragment;

/**
 * Created by quangnv on 29/08/2018
 */

public class HomeViewModel extends BaseViewModel {

    private HomePagerAdapter mAdapter;
    private String mToolbarTitle;

    public HomeViewModel(AppCompatActivity activity) {
        mAdapter = new HomePagerAdapter(activity.getSupportFragmentManager());
        mAdapter.addFragment(MovieFragment.newInstance(), activity.getString(R.string.title_movie));
        mAdapter.addFragment(GenreFragment.newInstance(), activity.getString(R.string.title_genre));
        mToolbarTitle = activity.getString(R.string.app_name);
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {
    }

    public HomePagerAdapter getAdapter() {
        return mAdapter;
    }

    public String getToolbarTitle() {
        return mToolbarTitle;
    }
}
