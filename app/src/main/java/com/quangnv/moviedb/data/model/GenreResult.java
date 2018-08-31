package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class GenreResult implements Serializable {

    @SerializedName("genres")
    @Expose
    private List<Gerne> mGernes;

    public List<Gerne> getGernes() {
        return mGernes;
    }

    public void setGernes(List<Gerne> gernes) {
        mGernes = gernes;
    }
}
