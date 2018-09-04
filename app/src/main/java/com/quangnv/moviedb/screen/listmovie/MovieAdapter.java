package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.databinding.ItemMovieBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 04/09/2018
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies;
    private LayoutInflater mInflater;
    private ItemMovieListener mItemMovieListener;

    public MovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_movie, parent, false);
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

        private ItemMovieBinding mBinding;
        private ItemMovieViewModel mViewModel;

        public ItemViewHolder(ItemMovieBinding binding, ItemMovieListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemMovieViewModel(listener);
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
