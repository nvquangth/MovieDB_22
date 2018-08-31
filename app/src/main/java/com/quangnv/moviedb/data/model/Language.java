package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by quangnv on 31/08/2018
 */

public class Language implements Serializable {

    @SerializedName("iso_639_1")
    @Expose
    private String mIso;
    @SerializedName("name")
    @Expose
    private String mName;

    public String getIso() {
        return mIso;
    }

    public void setIso(String iso) {
        mIso = iso;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
