package com.quangnv.moviedb.screen.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.ActivitySearchBinding;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.screen.main.MainActivity;
import com.quangnv.moviedb.screen.moviedetail.MovieDetailActivity;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

public class SearchActivity extends AppCompatActivity implements ItemMovieNavigator {

    private SearchMovieViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySearchBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_search);
        MovieRepository repository = MovieRepository.getInstance(null,
                MovieRemoteDataSource.getInstance(this));
        MovieAdapter adapter = new MovieAdapter(this);
        mViewModel = new SearchMovieViewModel(this, repository, adapter, this);
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        binding.setViewModel(mViewModel);
    }

    @Override
    public void onOpenMovieDetail(Movie movie) {
        startActivity(getMovieDetailIntent(this, movie));
    }

    public static Intent getMovieDetailIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MainActivity.EXTRA_MOVIE, movie);
        return intent;
    }
}
