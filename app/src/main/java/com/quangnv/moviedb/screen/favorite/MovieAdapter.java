package com.quangnv.moviedb.screen.favorite;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.databinding.ItemMovieFavoriteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 08/09/2018
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private List<Movie> mMovies;
    private LayoutInflater mInflater;
    private ItemMovieListener mItemMovieListener;
    private MovieRepository mRepository;

    public MovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieFavoriteBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_movie_favorite, parent, false);
        return new ItemViewHolder(binding, mItemMovieListener, mRepository);
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

    public void removeMovie(Movie movie, int position) {
        mMovies.remove(movie);
        notifyItemRemoved(position);
    }

    public void setItemMovieListener(ItemMovieListener listener) {
        mItemMovieListener = listener;
    }

    public void setRepository(MovieRepository repository) {
        mRepository = repository;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements FavoriteListener {

        private ItemMovieFavoriteBinding mBinding;
        private ItemFavoriteViewModel mViewModel;
        private FavoriteListener mFavoriteListener;
        private ItemMovieListener mItemMovieListener;

        public ItemViewHolder(ItemMovieFavoriteBinding binding, ItemMovieListener itemMovieListener,
                              MovieRepository repository) {
            super(binding.getRoot());
            mBinding = binding;
            mFavoriteListener = this;
            mItemMovieListener = itemMovieListener;
            mViewModel = new ItemFavoriteViewModel(repository, mItemMovieListener, mFavoriteListener);
            mBinding.setViewModel(mViewModel);
        }

        @Override
        public void onFavoriteClick(Movie movie) {
            mViewModel.removeFavorite(movie);
            mItemMovieListener.onItemMovieClick(movie, getAdapterPosition(), true);
        }

        private void bind(Movie movie) {
            mViewModel.setMovie(movie);
            mViewModel.setIconFavorite();
            mBinding.executePendingBindings();
        }
    }

    public interface ItemMovieListener {
        void onItemMovieClick(Movie movie, int position, boolean isFavoriteClick);
    }

    public interface FavoriteListener {
        void onFavoriteClick(Movie movie);
    }
}
