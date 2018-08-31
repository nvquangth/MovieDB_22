package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by quangnv on 31/08/2018
 */

public class Company implements Serializable {

    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("logo_path")
    @Expose
    private String mLogoPath;
    @SerializedName("origin_country")
    @Expose
    private String mOriginCountry;
    @SerializedName("headquarters")
    @Expose
    private String mHeadquarters;
    @SerializedName("description")
    @Expose
    private String mDescription;

    private Company(Builder builder) {
        mId = builder.mId;
        mName = builder.mName;
        mLogoPath = builder.mLogoPath;
        mOriginCountry = builder.mOriginCountry;
        mHeadquarters = builder.mHeadquarters;
        mDescription = builder.mDescription;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getLogoPath() {
        return mLogoPath;
    }

    public String getOriginCountry() {
        return mOriginCountry;
    }

    public String getHeadquarters() {
        return mHeadquarters;
    }

    public String getDescription() {
        return mDescription;
    }

    public static class Builder {

        private int mId;
        private String mName;
        private String mLogoPath;
        private String mOriginCountry;
        private String mHeadquarters;
        private String mDescription;

        public Builder() {

        }

        public Company build() {
            return new Company(this);
        }

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setLogoPath(String logoPath) {
            mLogoPath = logoPath;
            return this;
        }

        public Builder setOriginCountry(String originCountry) {
            mOriginCountry = originCountry;
            return this;
        }

        public Builder setHeadquarters(String headquarters) {
            mHeadquarters = headquarters;
            return this;
        }

        public Builder setDescription(String description) {
            mDescription = description;
            return this;
        }
    }
}
