package com.quangnv.moviedb.screen.reviewdetail;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.databinding.FragmentReviewBinding;
import com.quangnv.moviedb.screen.ActionCloseNavigator;
import com.quangnv.moviedb.screen.moviedetail.ReviewAdapter;

/**
 * Created by quangnv on 08/09/2018
 */

public class ReviewDetailFragment extends DialogFragment implements ActionCloseNavigator {

    private static final String ARGUMENT_MOVIE = "ARGUMENT_MOVIE";
    private ReviewDetailViewModel mViewModel;
    private Movie mMovie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        getData();
        FragmentReviewBinding binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false);
        ReviewAdapter adapter = new ReviewAdapter(getActivity());
        adapter.setReviews(mMovie.getReviewResult().getReviews());
        mViewModel = new ReviewDetailViewModel(mMovie, adapter, this);
        binding.setViewModel(mViewModel);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onCloseClick() {
        dismiss();
    }

    public static ReviewDetailFragment newInstance(Movie movie) {
        ReviewDetailFragment fragment = new ReviewDetailFragment();
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
