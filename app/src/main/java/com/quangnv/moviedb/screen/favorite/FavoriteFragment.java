package com.quangnv.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.local.MovieLocalDataSource;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.FragmentFavoriteBinding;
import com.quangnv.moviedb.screen.ActionSearchNavigator;
import com.quangnv.moviedb.screen.BaseFragment;
import com.quangnv.moviedb.screen.ItemMovieNavigator;

/**
 * Created by quangnv on 29/08/2018
 */

public class FavoriteFragment extends BaseFragment {

    private FavoriteViewModel mViewModel;
    private ItemMovieNavigator mItemMovieNavigator;
    private ActionSearchNavigator mSearchNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ItemMovieNavigator) {
            mItemMovieNavigator = (ItemMovieNavigator) getActivity();
        }
        if (getActivity() instanceof ActionSearchNavigator) {
            mSearchNavigator = (ActionSearchNavigator) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentFavoriteBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false);
        MovieRepository repository = MovieRepository.getInstance(
                MovieLocalDataSource.getInstance(getContext()),
                MovieRemoteDataSource.getInstance(getContext()));
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setRepository(repository);
        mViewModel = new FavoriteViewModel(getContext(), repository, adapter, mItemMovieNavigator);
        mViewModel.onStart();
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        mItemMovieNavigator = null;
        mSearchNavigator = null;
        super.onDestroy();
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }
}
