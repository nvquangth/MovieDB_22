package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by quangnv on 31/08/2018
 */

public class Video implements Serializable {

    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("key")
    @Expose
    private String mKey;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("type")
    private String mType;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
