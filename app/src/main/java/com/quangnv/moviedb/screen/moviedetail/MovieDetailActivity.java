package com.quangnv.moviedb.screen.moviedetail;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.quangnv.moviedb.R;

public class MovieDetailActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}
