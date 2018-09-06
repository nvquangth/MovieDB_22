package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.ObservableField;

import com.quangnv.moviedb.data.model.Review;
import com.quangnv.moviedb.screen.BaseViewModel;

/**
 * Created by quangnv on 06/09/2018
 */

public class ItemReviewViewModel extends BaseViewModel {

    public ObservableField<Review> reviewObservableField;

    public ItemReviewViewModel() {
        reviewObservableField = new ObservableField<>();
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void setReview(Review review) {
        reviewObservableField.set(review);
    }
}
