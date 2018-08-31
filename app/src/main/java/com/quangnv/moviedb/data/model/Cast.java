package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by quangnv on 31/08/2018
 */

public class Cast implements Serializable {

    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("character")
    @Expose
    private String mCharacter;
    @SerializedName("profile_path")
    @Expose
    private String mProfilePath;
    @SerializedName("birthday")
    @Expose
    private String mBirthday;
    @SerializedName("deathday")
    @Expose
    private String mDeathday;
    @SerializedName("biography")
    @Expose
    private String mBiography;
    @SerializedName("place_of_birth")
    @Expose
    private String mPlaceOfBirth;

    private Cast(Builder builder) {
        mId = builder.mId;
        mName = builder.mName;
        mCharacter = builder.mCharacter;
        mProfilePath = builder.mProfilePath;
        mBirthday = builder.mBirthday;
        mDeathday = builder.mDeathday;
        mBiography = builder.mBiography;
        mPlaceOfBirth = builder.mPlaceOfBirth;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public String getDeathday() {
        return mDeathday;
    }

    public String getBiography() {
        return mBiography;
    }

    public String getPlaceOfBirth() {
        return mPlaceOfBirth;
    }

    public static class Builder {

        private int mId;
        private String mName;
        private String mCharacter;
        private String mProfilePath;
        private String mBirthday;
        private String mDeathday;
        private String mBiography;
        private String mPlaceOfBirth;

        public Builder() {

        }

        public Cast build() {
            return new Cast(this);
        }

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setCharacter(String character) {
            mCharacter = character;
            return this;
        }

        public Builder setProfilePath(String profilePath) {
            mProfilePath = profilePath;
            return this;
        }

        public Builder setBirthday(String birthday) {
            mBirthday = birthday;
            return this;
        }

        public Builder setDeathday(String deathday) {
            mDeathday = deathday;
            return this;
        }

        public Builder setBiography(String biography) {
            mBiography = biography;
            return this;
        }

        public Builder setPlaceOfBirth(String placeOfBirth) {
            mPlaceOfBirth = placeOfBirth;
            return this;
        }
    }
}
