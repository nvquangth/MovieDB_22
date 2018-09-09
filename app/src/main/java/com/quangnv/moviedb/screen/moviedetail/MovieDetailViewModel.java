package com.quangnv.moviedb.screen.moviedetail;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.model.MovieResult;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.screen.ActionReadMoreDesNavigator;
import com.quangnv.moviedb.screen.ActionReadMoreReviewNavigator;
import com.quangnv.moviedb.screen.BaseViewModel;
import com.quangnv.moviedb.screen.listmovie.MovieAdapter;
import com.quangnv.moviedb.screen.ItemCastNavigator;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by quangnv on 05/09/2018
 */

public class MovieDetailViewModel extends BaseViewModel implements CastAdapter.ItemCastListener,
        SmallMovieAdapter.ItemMovieListener {

    private Movie mMovie;
    private MovieRepository mRepository;
    private SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;
    private CastAdapter mCastAdapter;
    private ReviewAdapter mReviewAdapter;
    private SmallMovieAdapter mMovieAdapter;
    private int mPage = 1;
    private MovieAdapter.FavoriteListener mFavoriteListener;
    private ActionReadMoreDesNavigator mReadMoreDesNavigator;
    private ActionReadMoreReviewNavigator mReadMoreReviewNavigator;
    private ItemCastNavigator mItemCastNavigator;
    public ObservableField<Movie> movieObservableField;
    public ObservableField<YouTubePlayer.OnInitializedListener> youTubeListener;
    public ObservableInt mResId = new ObservableInt();

    public MovieDetailViewModel(MovieRepository repository, Movie movie,
                                MovieAdapter.FavoriteListener listener,
                                ActionReadMoreDesNavigator readMoreDesNavigator,
                                ActionReadMoreReviewNavigator readMoreReviewNavigator) {
        mRepository = repository;
        mMovie = movie;
        mCompositeDisposable = new CompositeDisposable();
        movieObservableField = new ObservableField<>();
        movieObservableField.set(movie);
        youTubeListener = new ObservableField<>();
        mFavoriteListener = listener;
        initFavorite();
        mReadMoreDesNavigator = readMoreDesNavigator;
        mReadMoreReviewNavigator = readMoreReviewNavigator;
    }

    @Override
    protected void onStart() {
        callApiGetMovieRecommend(mMovie.getId());
        callApiGetMovieDetail(mMovie.getId());
    }

    @Override
    protected void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void onItemCastClick(Cast cast) {
        mItemCastNavigator.onOpenCastDetail(cast);
    }

    @Override
    public void onItemMovieClick(Movie movie) {
    }

    public void onReadMoreDesClick(View view) {
        mReadMoreDesNavigator.onReadMoreDesClick(mMovie);
    }

    public void onReadMoreReviewClick(View view) {
        mReadMoreReviewNavigator.onReadMoreReviewClick(mMovie);
    }

    public void setSchedulerProvider(SchedulerProvider provider) {
        mSchedulerProvider = provider;
    }

    public CastAdapter getCastAdapter() {
        return mCastAdapter;
    }

    public void setCastAdapter(CastAdapter castAdapter) {
        mCastAdapter = castAdapter;
        mCastAdapter.setItemCastListener(this);
    }

    public SmallMovieAdapter getMovieAdapter() {
        return mMovieAdapter;
    }

    public void setMovieAdapter(SmallMovieAdapter movieAdapter) {
        mMovieAdapter = movieAdapter;
        mMovieAdapter.setItemMovieListener(this);
    }

    public ReviewAdapter getReviewAdapter() {
        return mReviewAdapter;
    }

    public void setReviewAdapter(ReviewAdapter reviewAdapter) {
        mReviewAdapter = reviewAdapter;
    }

    public void onFavoriteClick(View view) {
        mFavoriteListener.onFavoriteClick(mMovie);
    }

    public int getResId() {
        return mResId.get();
    }

    private void initFavorite() {
        if (checkFavorite(mMovie)) {
            updateResId(true);
        } else {
            updateResId(false);
        }
    }

    public void updateResId(boolean isFavorite) {
        if (isFavorite) {
            mResId.set(R.drawable.ic_bookmark_blue_24dp);
        } else {
            mResId.set(R.drawable.ic_bookmark_border_blue_24dp);
        }
    }

    public boolean checkFavorite(Movie movie) {
        return mRepository.isExistMovie(movie);
    }

    public void setItemCastNavigator(ItemCastNavigator navigator) {
        mItemCastNavigator = navigator;
    }

    private void callApiGetMovieDetail(int movieId) {
        Disposable disposable = mRepository.getMovieDetail(movieId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(final Movie movie) throws Exception {
                        mMovie = movie;
                        mCastAdapter.setCasts(movie.getCastResult().getCasts());
                        mReviewAdapter.setReviews(movie.getReviewResult().getReviews());
                        movieObservableField.set(movie);
                        youTubeListener.set(new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer player, boolean b) {
                                if (!movie.getVideoResult().getVideos().isEmpty()) {
                                    player.cueVideo(movie.getVideoResult().getVideos().get(0).getKey());
                                }
                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult result) {

                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    private void callApiGetMovieRecommend(int movieId) {
        Disposable disposable = mRepository.getRecommend(mPage, movieId)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new Consumer<MovieResult>() {
                    @Override
                    public void accept(MovieResult movieResult) throws Exception {
                        mMovieAdapter.setMovies(movieResult.getMovies());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mCompositeDisposable.add(disposable);
    }
}
