package com.quangnv.moviedb.screen.genre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.repository.GenreRepository;
import com.quangnv.moviedb.data.source.remote.GenreRemoteDataSource;
import com.quangnv.moviedb.databinding.FragmentGenreBinding;
import com.quangnv.moviedb.screen.BaseFragment;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

/**
 * Created by quangnv on 30/08/2018
 */

public class GenreFragment extends BaseFragment {

    private GenreViewModel mViewModel;
    private GenreNavigator mGenreNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof GenreNavigator) {
            mGenreNavigator = (GenreNavigator) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentGenreBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_genre, container, false);
        GenreRepository repository = GenreRepository.getInstance(null,
                GenreRemoteDataSource.getInstance(getContext()));
        GenreAdapter adapter = new GenreAdapter(getContext());
        mViewModel = new GenreViewModel(getContext(), repository, adapter);
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        mViewModel.setGenreNavigator(mGenreNavigator);
        mViewModel.onStart();
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroy() {
        mGenreNavigator = null;
        super.onDestroy();
    }

    public static GenreFragment newInstance() {
        GenreFragment fragment = new GenreFragment();
        return fragment;
    }
}
