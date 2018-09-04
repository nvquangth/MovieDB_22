package com.quangnv.moviedb.screen.genre;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quangnv.moviedb.data.model.Genre;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.listmovie.ListMovieViewModel;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.screen.listmovie.MoviesFragment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by quangnv on 05/09/2018
 */

public class GenreMovieFragment extends MoviesFragment {

    private static final String ARGUMENT_GENRE = "ARGUMENT_GENRES";
    private List<Genre> mGenres;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mGenres = (List<Genre>) getArguments().getSerializable(ARGUMENT_GENRE);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected ListMovieViewModel initViewModel(Context context, MovieRepository repository,
                                               MovieAdapter adapter) {
        return new GenreMovieViewModel(context, repository, mGenres, adapter);
    }

    public static GenreMovieFragment newInstance(List<Genre> genres) {
        GenreMovieFragment fragment = new GenreMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_GENRE, (Serializable) genres);
        fragment.setArguments(bundle);
        return fragment;
    }
}
