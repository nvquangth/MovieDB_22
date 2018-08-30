package com.quangnv.moviedb.util;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;

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
}
