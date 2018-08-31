package com.quangnv.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by quangnv on 31/08/2018
 */

public class Review implements Serializable {

    @SerializedName("author")
    @Expose
    private String mAuthor;
    @SerializedName("content")
    @Expose
    private String mContent;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
