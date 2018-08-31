package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class ReviewResult implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Review> mReviews;

    public List<Review> getReviews() {
        return mReviews;
    }

    public void setReviews(List<Review> reviews) {
        mReviews = reviews;
    }
}
