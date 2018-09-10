package com.quangnv.moviedb.screen.descriptiondetail;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.databinding.FragmentDescriptionBinding;
import com.quangnv.moviedb.screen.ActionCloseNavigator;

/**
 * Created by quangnv on 08/09/2018
 */

public class DescriptionDetailFragment extends DialogFragment implements ActionCloseNavigator {

    private static final String ARGUMENT_MOVIE = "ARGUMENT_MOVIE";
    private DescriptionDetailViewModel mViewModel;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getData();
        FragmentDescriptionBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_description, container, false);
        mViewModel = new DescriptionDetailViewModel(mMovie, this);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onCloseClick() {
        dismiss();
    }

    public static DescriptionDetailFragment newInstance(Movie movie) {
        DescriptionDetailFragment fragment = new DescriptionDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_MOVIE, movie);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getData() {
        Bundle bundle = getArguments();
        mMovie = (Movie) bundle.getSerializable(ARGUMENT_MOVIE);
    }
}
