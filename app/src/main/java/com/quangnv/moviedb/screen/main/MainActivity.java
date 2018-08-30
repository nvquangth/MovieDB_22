package com.quangnv.moviedb.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.databinding.ActivityMainBinding;
import com.quangnv.moviedb.screen.movie.MovieNavigator;

public class MainActivity extends AppCompatActivity implements MovieNavigator {

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
}
