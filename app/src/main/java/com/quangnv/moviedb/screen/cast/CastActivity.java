package com.quangnv.moviedb.screen.cast;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.quangnv.moviedb.R;
import com.quangnv.moviedb.data.model.Cast;
import com.quangnv.moviedb.data.model.Movie;
import com.quangnv.moviedb.data.repository.MovieRepository;
import com.quangnv.moviedb.data.repository.PersonRepository;
import com.quangnv.moviedb.data.source.local.MovieLocalDataSource;
import com.quangnv.moviedb.data.source.remote.MovieRemoteDataSource;
import com.quangnv.moviedb.data.source.remote.PersonRemoteDataSource;
import com.quangnv.moviedb.databinding.ActivityCastBinding;
import com.quangnv.moviedb.screen.ItemMovieNavigator;
import com.quangnv.moviedb.screen.moviedetail.SmallMovieAdapter;
import com.quangnv.moviedb.screen.search.SearchActivity;
import com.quangnv.moviedb.util.rx.SchedulerProvider;

public class CastActivity extends AppCompatActivity implements ItemMovieNavigator {

    public static final String EXTRA_CAST = "com.quangnv.moviedb.extras.EXTRA_CAST";
    private Cast mCast;
    private CastViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        ActivityCastBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_cast);
        PersonRepository personRepository =
                PersonRepository.getInstance(PersonRemoteDataSource.getInstance(this));
        MovieRepository movieRepository = MovieRepository.getInstance(
                MovieLocalDataSource.getInstance(this),
                MovieRemoteDataSource.getInstance(this));
        SmallMovieAdapter adapter = new SmallMovieAdapter(this);
        SchedulerProvider schedulerProvider = SchedulerProvider.getInstance();
        mViewModel = new CastViewModel(mCast);
        mViewModel.setPersonRepository(personRepository);
        mViewModel.setMovieRepository(movieRepository);
        mViewModel.setMovieAdapter(adapter);
        mViewModel.setSchedulerProvider(schedulerProvider);
        mViewModel.setItemMovieNavigator(this);
        mViewModel.onStart();
        setSupportActionBar(binding.toolbar);
        binding.setViewModel(mViewModel);
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.navigation_search:
                startActivity(getSearchIntent(this));
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onOpenMovieDetail(Movie movie) {
        startActivity(SearchActivity.getMovieDetailIntent(this, movie));
    }

    public static Intent getSearchIntent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    private void getData() {
        mCast = (Cast) getIntent().getSerializableExtra(EXTRA_CAST);
    }
}
