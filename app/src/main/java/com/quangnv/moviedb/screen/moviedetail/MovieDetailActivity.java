package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.local.MovieLocalDataSource;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.ActivityMovieDetailBinding;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.screen.main.MainActivity;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

public class MovieDetailActivity extends YouTubeBaseActivity implements MovieAdapter.FavoriteListener {

    private MovieDetailViewModel mViewModel;
    private MovieRepository mRepository;
    private ActivityMovieDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mRepository = MovieRepository.getInstance(
                MovieLocalDataSource.getInstance(this),
                MovieRemoteDataSource.getInstance(this));
        Movie movie = getMovie();
        CastAdapter castAdapter = new CastAdapter(this);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this);
        SmallMovieAdapter movieAdapter = new SmallMovieAdapter(this);
        mViewModel = new MovieDetailViewModel(mRepository, movie, this);
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        mViewModel.setCastAdapter(castAdapter);
        mViewModel.setReviewAdapter(reviewAdapter);
        mViewModel.setMovieAdapter(movieAdapter);
        mViewModel.onStart();
        mBinding.setViewModel(mViewModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.onStop();
    }

    private Movie getMovie() {
        return (Movie) getIntent().getSerializableExtra(MainActivity.EXTRA_MOVIE);
    }

    @Override
    public void onFavoriteClick(Movie movie) {
        if (!mViewModel.checkFavorite(movie)) {
            mViewModel.updateResId(true);
            mRepository.addMovie(movie);
        } else {
            mViewModel.updateResId(false);
            mRepository.removeMovie(movie);
        }
        mBinding.setViewModel(mViewModel);
    }
}
