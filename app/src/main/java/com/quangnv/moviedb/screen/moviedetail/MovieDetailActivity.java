package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.ActivityMovieDetailBinding;
import com.quangnv.moviedb.screen.main.MainActivity;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

public class MovieDetailActivity extends YouTubeBaseActivity {

    private MovieDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        MovieRepository repository = MovieRepository.getInstance(null,
                MovieRemoteDataSource.getInstance(this));
        Movie movie = getMovie();
        CastAdapter castAdapter = new CastAdapter(this);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this);
        SmallMovieAdapter movieAdapter = new SmallMovieAdapter(this);
        mViewModel = new MovieDetailViewModel(repository, movie);
        mViewModel.setSchedulerProvider(SchedulerProvider.getInstance());
        mViewModel.setCastAdapter(castAdapter);
        mViewModel.setReviewAdapter(reviewAdapter);
        mViewModel.setMovieAdapter(movieAdapter);
        mViewModel.onStart();
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewModel.onStop();
    }

    private Movie getMovie() {
        return (Movie) getIntent().getSerializableExtra(MainActivity.EXTRA_MOVIE);
    }
}
