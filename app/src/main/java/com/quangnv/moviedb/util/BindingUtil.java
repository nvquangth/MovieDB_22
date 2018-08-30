package com.quangnv.moviedb.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

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
}
