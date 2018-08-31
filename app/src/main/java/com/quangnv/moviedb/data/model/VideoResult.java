package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 31/08/2018
 */

public class VideoResult implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Video> mVideos;

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }
}
