package com.android.vlad.movieapparchitecturecomponents.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.vlad.movieapparchitecturecomponents.BuildConfig;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.data.repository.remote.MovieResponse;
import com.android.vlad.movieapparchitecturecomponents.data.repository.remote.MoviesWebService;
import com.android.vlad.movieapparchitecturecomponents.di.ApplicationComponent;
import com.android.vlad.movieapparchitecturecomponents.di.DaggerApplicationComponent;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource {

    @Inject
    MoviesWebService moviesWebService;
    private static MovieDataSource INSTANCE;

    private MovieDataSource() {
        ApplicationComponent component = DaggerApplicationComponent.builder().dataSource(this).build();
        component.inject(this);
    }

    public synchronized static MovieDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovieDataSource();
        }

        return INSTANCE;
    }

    public LiveData<List<Movie>> getMovieResponse() {
        final MutableLiveData<List<Movie>> movieData = new MutableLiveData<>();

        moviesWebService.getTopRatedMovies(BuildConfig.ApiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                movieData.setValue(movieResponse.getMovies());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                movieData.setValue(null);
            }
        });
        return movieData;
    }

    public LiveData<Movie> getMovieDetails(String movieId) {
        final MutableLiveData<Movie> movieData = new MutableLiveData<>();

        moviesWebService.getMovieById(movieId, BuildConfig.ApiKey).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
        return movieData;
    }
}
