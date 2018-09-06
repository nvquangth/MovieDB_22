package com.quangnv.moviedb.screen.moviedetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.databinding.ItemMovieSmallBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 06/09/2018
 */

public class SmallMovieAdapter extends RecyclerView.Adapter<SmallMovieAdapter.ItemViewHolder> {

    private LayoutInflater mInflater;
    private List<Movie> mMovies;
    private ItemMovieListener mItemMovieListener;

    public SmallMovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieSmallBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_movie_small, parent, false);
        return new ItemViewHolder(binding, mItemMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    public void setMovies(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setItemMovieListener(ItemMovieListener listener) {
        mItemMovieListener = listener;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieSmallBinding mBinding;
        private ItemSmallMovieViewModel mViewModel;

        public ItemViewHolder(ItemMovieSmallBinding binding, ItemMovieListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemSmallMovieViewModel(listener);
            mBinding.setViewModel(mViewModel);
        }

        private void bind(Movie movie) {
            mViewModel.setMovie(movie);
            mBinding.executePendingBindings();
        }
    }

    public interface ItemMovieListener {
        void onItemMovieClick(Movie movie);
    }
}
