package com.quangnv.moviedb.screen.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.databinding.FragmentMovieBinding;
import com.quangnv.moviedb.screen.BaseFragment;

/**
 * Created by quangnv on 30/08/2018
 */

public class MovieFragment extends BaseFragment {

    private MovieViewModel mViewModel;
    private MovieNavigator mNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MovieNavigator) {
            mNavigator = (MovieNavigator) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentMovieBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie,
                container, false);
        mViewModel = new MovieViewModel();
        mViewModel.setNavigator(mNavigator);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        mNavigator = null;
        super.onDestroy();
    }

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }
}
