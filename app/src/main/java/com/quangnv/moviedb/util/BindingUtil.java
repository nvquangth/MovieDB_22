package com.quangnv.moviedb.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;

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

    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(StringUtil.genUrlImage(url))
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
}
