package com.quangnv.moviedb.screen.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.databinding.ActivityMainBinding;
import com.quangnv.moviedb.screen.ActionSearchNavigator;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.ToolbarNavigator;
import com.quangnv.moviedb.screen.genre.GenreNavigator;
import com.quangnv.moviedb.screen.movie.MovieNavigator;
import com.quangnv.moviedb.screen.moviedetail.MovieDetailActivity;
import com.quangnv.moviedb.screen.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements MovieNavigator, ItemMovieNavigator,
        ToolbarNavigator, GenreNavigator, ActionSearchNavigator {

    public static final String EXTRA_MOVIE = "com.quangnv.moviedb.extras.EXTRA_MOVIE";
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new MainViewModel(this);
        binding.setViewModel(mViewModel);
    }

    @Override
    public void openMoviesFragment(Fragment fragment) {
        mViewModel.addFragment(fragment, true);
    }

    @Override
    public void onOpenMovieDetail(Movie movie) {
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    @Override
    public void onNavigationClick() {
        onBackPressed();
    }

    @Override
    public void onSearchClick() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
