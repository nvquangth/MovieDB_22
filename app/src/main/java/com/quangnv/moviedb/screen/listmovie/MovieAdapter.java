package com.quangnv.moviedb.screen.listmovie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.databinding.ItemMovieBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangnv on 04/09/2018
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ItemViewHolder> {

    private static List<Movie> mFavoriteMovies;
    private List<Movie> mMovies;
    private LayoutInflater mInflater;
    private ItemMovieListener mItemMovieListener;
    private MovieRepository mRepository;

    public MovieAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mMovies = new ArrayList<>();
        mFavoriteMovies = new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding =
                DataBindingUtil.inflate(mInflater, R.layout.item_movie, parent, false);
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

    public void setItemMovieListener(ItemMovieListener listener) {
        mItemMovieListener = listener;
    }

    public void setRepository(MovieRepository repository) {
        mRepository = repository;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements FavoriteListener {

        private ItemMovieBinding mBinding;
        private ItemMovieViewModel mViewModel;
        private FavoriteListener mFavoriteListener;

        public ItemViewHolder(ItemMovieBinding binding, ItemMovieListener listener,
                              MovieRepository repository) {
            super(binding.getRoot());
            mBinding = binding;
            mFavoriteListener = this;
            mViewModel = new ItemMovieViewModel(repository, listener, mFavoriteListener);
            mBinding.setViewModel(mViewModel);
        }

        @Override
        public void onFavoriteClick(Movie movie) {
            if (!mViewModel.checkFavorite(movie)) {
                mViewModel.setIconFavorite();
                mViewModel.addFavorite(movie);
                mFavoriteMovies.add(movie);
            } else {
                mViewModel.removeIconFavorite();
                mViewModel.removeFavorite(movie);
                mFavoriteMovies.remove(movie);
            }
        }

        private void bind(Movie movie) {
            mViewModel.setMovie(movie);
            if (mFavoriteMovies.contains(movie) || mViewModel.checkFavorite(movie)) {
                mViewModel.setIconFavorite();
            } else {
                mViewModel.removeIconFavorite();
            }
            mBinding.executePendingBindings();
        }
    }

    public interface ItemMovieListener {
        void onItemMovieClick(Movie movie);
    }

    public interface FavoriteListener {
        void onFavoriteClick(Movie movie);
    }
}
