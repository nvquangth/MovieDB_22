package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.ObservableField;
import android.view.View;

import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.screen.BaseViewModel;

/**
 * Created by quangnv on 06/09/2018
 */

public class ItemCastViewModel extends BaseViewModel {

    public ObservableField<Cast> castObservableField;
    private CastAdapter.ItemCastListener mItemCastListener;

    public ItemCastViewModel(CastAdapter.ItemCastListener listener) {
        castObservableField = new ObservableField<>();
        mItemCastListener = listener;
    }

    @Override
    protected void onStart() {

    }

    @Override
    protected void onStop() {

    }

    public void setCast(Cast cast) {
        castObservableField.set(cast);
    }

    public void onItemClick(View view) {
        if (mItemCastListener == null || castObservableField.get() == null) {
            return;
        }
        mItemCastListener.onItemCastClick(castObservableField.get());
    }
}
