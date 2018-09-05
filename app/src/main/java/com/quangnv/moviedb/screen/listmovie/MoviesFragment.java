package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.FragmentListMovieBinding;
import com.quangnv.moviedb.screen.BaseFragment;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.ToolbarNavigator;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

/**
 * Created by quangnv on 30/08/2018
 */

public abstract class MoviesFragment extends BaseFragment {

    private ListMovieViewModel mViewModel;
    private ItemMovieNavigator mNavigator;
    private ToolbarNavigator mToolbarNavigator;
    protected MovieRepository mRepository;
    protected MovieAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ItemMovieNavigator) {
            mNavigator = (ItemMovieNavigator) getActivity();
        }
        if (getActivity() instanceof ToolbarNavigator) {
            mToolbarNavigator = (ToolbarNavigator) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentListMovieBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_list_movie, container, false);
        mRepository = MovieRepository.getInstance(null,
                MovieRemoteDataSource.getInstance(getContext()));
        mAdapter = new MovieAdapter(getContext());
        mViewModel = initViewModel(getContext(), mRepository, mAdapter);
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        mViewModel.setItemMovieNavigator(mNavigator);
        mViewModel.onStart();
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        mNavigator = null;
        mToolbarNavigator = null;
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mToolbarNavigator.onNavigationClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                                        MovieAdapter adapter);
}
