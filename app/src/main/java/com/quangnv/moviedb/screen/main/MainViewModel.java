package com.quangnv.moviedb.screen.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.favorite.FavoriteFragment;
import com.quangnv.moviedb.screen.home.HomeFragment;
import com.quangnv.moviedb.screen.more.MoreFragment;

/**
 * Created by quangnv on 29/08/2018
 */

public class MainViewModel extends BaseViewModel implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private FavoriteFragment mFavoriteFragment;
    private MoreFragment mMoreFragment;
    private Fragment mCurrentFragment;

    public MainViewModel(AppCompatActivity activity) {
        mHomeFragment = HomeFragment.newInstance();
        mFavoriteFragment = FavoriteFragment.newInstance();
        mMoreFragment = MoreFragment.newInstance();
        mFragmentManager = activity.getSupportFragmentManager();
        initFragment();
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showFragment(mHomeFragment, mCurrentFragment);
                mCurrentFragment = mHomeFragment;
                return true;
            case R.id.navigation_favorite:
                showFragment(mFavoriteFragment, mCurrentFragment);
                mCurrentFragment = mFavoriteFragment;
                return true;
            case R.id.navigation_more:
                showFragment(mMoreFragment, mCurrentFragment);
                mCurrentFragment = mMoreFragment;
                return true;
        }
        return false;
    }

    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        popBackStackImmediate();
        if (isAddToBackStack) {
            mFragmentManager.beginTransaction()
                    .add(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            mFragmentManager.beginTransaction()
                    .add(R.id.frame_container, fragment)
                    .commit();
        }
        mCurrentFragment = fragment;
    }

    private void popBackStackImmediate() {
        int c = mFragmentManager.getBackStackEntryCount();
        if (c > 0) {
            mFragmentManager.popBackStackImmediate();
        }
    }

    private void initFragment() {
        mFragmentManager.beginTransaction()
                .add(R.id.frame_container, mHomeFragment)
                .add(R.id.frame_container, mFavoriteFragment)
                .add(R.id.frame_container, mMoreFragment)
                .commit();
        mFragmentManager.beginTransaction()
                .hide(mFavoriteFragment)
                .hide(mMoreFragment)
                .commit();
        mCurrentFragment = mHomeFragment;
    }

    private void showFragment(Fragment fShow, Fragment fHide) {
        popBackStackImmediate();
        mFragmentManager.beginTransaction().hide(fHide).show(fShow).commit();
    }
}
