package com.quangnv.moviedb.screen.moviedetail;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.source.local.MovieLocalDataSource;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.databinding.ActivityMovieDetailBinding;
import com.quangnv.moviedb.screen.ActionReadMoreDesNavigator;
import com.quangnv.moviedb.screen.ActionReadMoreReviewNavigator;
import com.quangnv.moviedb.screen.descriptiondetail.DescriptionDetailFragment;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.screen.main.MainActivity;
import com.quangnv.moviedb.screen.reviewdetail.ReviewDetailFragment;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

public class MovieDetailActivity extends YouTubeBaseActivity implements ActionReadMoreDesNavigator,
        ActionReadMoreReviewNavigator, MovieAdapter.FavoriteListener {

    private static final String TAG_DIALOG_FRAGMENT_DES = "TAG_DIALOG_FRAGMENT_DES";
    private static final String TAG_DIALOG_FRAGMENT_REVIEW = "TAG_DIALOG_FRAGMENT_REVIEW";
    private MovieDetailViewModel mViewModel;
    private MovieRepository mRepository;
    private ActivityMovieDetailBinding mBinding;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        mRepository = MovieRepository.getInstance(
                MovieLocalDataSource.getInstance(this),
                MovieRemoteDataSource.getInstance(this));
        mMovie = getMovie();
        CastAdapter castAdapter = new CastAdapter(this);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this);
        SmallMovieAdapter movieAdapter = new SmallMovieAdapter(this);
        mViewModel = new MovieDetailViewModel(mRepository, mMovie, this, this, this);
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

    @Override
    public void onReadMoreDesClick(Movie movie) {
        showReadMoreDescription(movie);
    }

    @Override
    public void onReadMoreReviewClick(Movie movie) {
        showReadMoreReview(movie);
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
    private void showReadMoreDescription(Movie movie) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT_DES);
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        DialogFragment dialog = DescriptionDetailFragment.newInstance(movie);
        dialog.show(transaction, TAG_DIALOG_FRAGMENT_DES);
    }

    private void showReadMoreReview(Movie movie) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(TAG_DIALOG_FRAGMENT_REVIEW);
        if (prev != null) {
            transaction.remove(prev);
        }
        transaction.addToBackStack(null);
        DialogFragment dialog = ReviewDetailFragment.newInstance(movie);
        dialog.show(transaction, TAG_DIALOG_FRAGMENT_REVIEW);
    }
}
