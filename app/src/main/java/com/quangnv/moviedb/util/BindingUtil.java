package com.quangnv.moviedb.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.quangnv.moviedb.BuildConfig;
import com.quangnv.moviedb.R;
import com.quangnv.moviedb.util.common.StringUtil;

/**
 * Created by quangnv on 29/08/2018
 */

public final class BindingUtil {

    @BindingAdapter({"onBottomNavigationChanged"})
    public static void setBottomNavigationListener(
            BottomNavigationView bottomNavigationView,
            BottomNavigationView.OnNavigationItemSelectedListener listener) {
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter({"toolbarTitle"})
    public static void setTitleToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

    @BindingAdapter({"pagerAdapter"})
    public static void setAdapterForPager(ViewPager viewPager, FragmentPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"viewPager"})
    public static void setTabLayoutWithPager(TabLayout tabLayout, ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
    }

    @BindingAdapter({"recyclerAdapter"})
    public static void setRecyclerAdapter(RecyclerView recyclerView,
                                          RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"recyclerLayoutManager"})
    public static void setLayoutManager(RecyclerView recyclerView,
                                        RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(StringUtil.genUrlImage(url))
                .placeholder(R.drawable.image_default)
                .into(imageView);
    }

    @BindingAdapter({"circleImageUrl"})
    public static void setCircleImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(StringUtil.genUrlImage(url))
                .placeholder(R.drawable.image_default)
                .circleCrop()
                .into(imageView);
    }

    @BindingAdapter({"ratingBar"})
    public static void setRatingBar(RatingBar ratingBar, float rating) {
        ratingBar.setRating(rating);
    }

    @BindingAdapter({"titleToolbar"})
    public static void setTitle(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
    }

    @BindingAdapter({"iconToolbar"})
    public static void setIcon(Toolbar toolbar, int resId) {
        toolbar.setNavigationIcon(resId);
    }

    @BindingAdapter({"onYouTubeInitializedListener"})
    public static void setOnYouTubeInitializedListener(YouTubePlayerView youTubeView,
                                                       YouTubePlayer.OnInitializedListener listener) {
        if (listener != null) {
            youTubeView.initialize(BuildConfig.YOUTUBE_API_KEY, listener);
        }
    }

    @BindingAdapter({"onEditorActionListener"})
    public static void setOnEditorActionListener(EditText editText,
                                                 TextView.OnEditorActionListener listener) {
        editText.setOnEditorActionListener(listener);
    }

    @BindingAdapter({"iconFavorite"})
    public static void setIcon(ImageButton imageButton, int resId) {
        imageButton.setImageResource(resId);
    }

    @BindingAdapter({"onRefreshListener"})
    public static void setOnRefreshLayoutListener(SwipeRefreshLayout layout,
                                                  SwipeRefreshLayout.OnRefreshListener listener) {
        layout.setOnRefreshListener(listener);
    }

    @BindingAdapter({"refreshLayout"})
    public static void setRefreshLayout(SwipeRefreshLayout layout, boolean isRefresh) {
        layout.setRefreshing(isRefresh);
    }

    @BindingAdapter({"colorRefreshLayout"})
    public static void setColorRefreshLayout(SwipeRefreshLayout layout, int color) {
        layout.setColorSchemeResources(color);
    }
}
