package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class CastResult implements Serializable {

    @SerializedName("cast")
    @Expose
    private List<Cast> mCasts;

    public List<Cast> getCasts() {
        return mCasts;
    }

    public void setCasts(List<Cast> casts) {
        mCasts = casts;
    }
}
