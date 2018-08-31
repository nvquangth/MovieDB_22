package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class Movie implements Serializable {

    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("vote_average")
    @Expose
    private float mVoteAverage;
    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;
    @SerializedName("overview")
    @Expose
    private String mOverview;
    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;
    @SerializedName("genres")
    @Expose
    private List<Gerne> mGernes;
    @SerializedName("production_companies")
    @Expose
    private List<Company> mCompanies;
    @SerializedName("production_countries")
    @Expose
    private List<Country> mCountries;
    @SerializedName("spoken_languages")
    @Expose
    private List<Language> mLanguages;
    @SerializedName("videos")
    @Expose
    private VideoResult mVideoResult;
    @SerializedName("credits")
    @Expose
    private CastResult mCastResult;
    @SerializedName("reviews")
    @Expose
    private ReviewResult mReviewResult;

    private Movie(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mVoteAverage = builder.mVoteAverage;
        mPosterPath = builder.mPosterPath;
        mBackdropPath = builder.mBackdropPath;
        mOverview = builder.mOverview;
        mReleaseDate = builder.mReleaseDate;
        mGernes = builder.mGernes;
        mCompanies = builder.mCompanies;
        mCountries = builder.mCountries;
        mLanguages = builder.mLanguages;
        mVideoResult = builder.mVideoResult;
        mCastResult = builder.mCastResult;
        mReviewResult = builder.mReviewResult;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public List<Gerne> getGernes() {
        return mGernes;
    }

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public List<Country> getCountries() {
        return mCountries;
    }

    public List<Language> getLanguages() {
        return mLanguages;
    }

    public VideoResult getVideoResult() {
        return mVideoResult;
    }

    public CastResult getCastResult() {
        return mCastResult;
    }

    public ReviewResult getReviewResult() {
        return mReviewResult;
    }

    public static class Builder {

        private int mId;
        private String mTitle;
        private float mVoteAverage;
        private String mPosterPath;
        private String mBackdropPath;
        private String mOverview;
        private String mReleaseDate;
        private List<Gerne> mGernes;
        private List<Company> mCompanies;
        private List<Country> mCountries;
        private List<Language> mLanguages;
        private VideoResult mVideoResult;
        private CastResult mCastResult;
        private ReviewResult mReviewResult;

        public Builder() {

        }

        public Movie build() {
            return new Movie(this);
        }

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setVoteAverage(float voteAverage) {
            mVoteAverage = voteAverage;
            return this;
        }

        public Builder setPosterPath(String posterPath) {
            mPosterPath = posterPath;
            return this;
        }

        public Builder setBackdropPath(String backdropPath) {
            mBackdropPath = backdropPath;
            return this;
        }

        public Builder setOverview(String overview) {
            mOverview = overview;
            return this;
        }

        public Builder setReleaseDate(String releaseDate) {
            mReleaseDate = releaseDate;
            return this;
        }

        public Builder setGernes(List<Gerne> gernes) {
            mGernes = gernes;
            return this;
        }

        public Builder setCompanies(List<Company> companies) {
            mCompanies = companies;
            return this;
        }

        public Builder setCountries(List<Country> countries) {
            mCountries = countries;
            return this;
        }

        public Builder setLanguages(List<Language> languages) {
            mLanguages = languages;
            return this;
        }

        public Builder setVideoResult(VideoResult videoResult) {
            mVideoResult = videoResult;
            return this;
        }

        public Builder setCastResult(CastResult castResult) {
            mCastResult = castResult;
            return this;
        }

        public Builder setReviewResult(ReviewResult reviewResult) {
            mReviewResult = reviewResult;
            return this;
        }
    }
}
