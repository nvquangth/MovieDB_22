package com.quangnv.moviedb.screen.moviedetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.databinding.ItemCastBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 06/09/2018
 */

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ItemViewHolder> {

    private LayoutInflater mInflater;
    private List<Cast> mCasts;
    private ItemCastListener mItemCastListener;

    public CastAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mCasts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_cast, parent, false);
        return new ItemViewHolder(binding, mItemCastListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mCasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCasts != null ? mCasts.size() : 0;
    }

    public void setCasts(List<Cast> casts) {
        mCasts.clear();
        mCasts.addAll(casts);
        notifyDataSetChanged();
    }

    public void setItemCastListener(ItemCastListener listener) {
        mItemCastListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemCastBinding mBinding;
        private ItemCastViewModel mViewModel;

        public ItemViewHolder(ItemCastBinding binding, ItemCastListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemCastViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Cast cast) {
            mViewModel.setCast(cast);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemCastListener {
        void onItemCastClick(Cast cast);
    }
}
