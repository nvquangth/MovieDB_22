package com.quangnv.moviedb.screen.genre;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.databinding.ItemGenreBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 04/09/2018
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ItemViewHolder> {

    private List<Genre> mGenres;
    private LayoutInflater mInflater;
    private ItemGenreListener mItemGenreListener;

    public GenreAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mGenres = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_genre, parent, false);
        return new ItemViewHolder(binding, mItemGenreListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres != null ? mGenres.size() : 0;
    }

    public void setGenres(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    public void setItemGenreListener(ItemGenreListener listener) {
        mItemGenreListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemGenreBinding mBinding;
        private ItemGenreViewModel mViewModel;

        public ItemViewHolder(ItemGenreBinding binding, ItemGenreListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemGenreViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Genre genre) {
            mViewModel.setGenre(genre);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemGenreListener {
        void onItemGenreClick(Genre genre);
    }
}
