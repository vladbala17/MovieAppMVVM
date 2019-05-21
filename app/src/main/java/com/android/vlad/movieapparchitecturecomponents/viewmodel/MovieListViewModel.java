package com.android.vlad.movieapparchitecturecomponents.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.data.repository.MovieDataSource;
import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private final LiveData<List<Movie>> movieListObservable;

    public MovieListViewModel(@NonNull Application application) {
        super(application);

        movieListObservable = MovieDataSource.getInstance().getMovieResponse();
    }

    public LiveData<List<Movie>> getMovieListObservable() {
        return movieListObservable;
    }
}
