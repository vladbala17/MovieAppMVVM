package com.android.vlad.movieapparchitecturecomponents.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.data.repository.MovieDataSource;

public class MovieViewModel extends AndroidViewModel {
    private final LiveData<Movie> movieObservable;
    private final String movieId;

    public ObservableField<Movie> movieObservableField = new ObservableField<>();

    public MovieViewModel(@NonNull Application application, String movieId) {
        super(application);
        this.movieId = movieId;
        movieObservable = MovieDataSource.getInstance().getMovieDetails(movieId);
    }

    public LiveData<Movie> getMovieObservable() {
        return movieObservable;
    }

    public void setMovie(Movie movie) {
        movieObservableField.set(movie);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;

        private final String movieId;

        public Factory(@NonNull Application application, String movieId) {
            this.application = application;
            this.movieId = movieId;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MovieViewModel(application, movieId);
        }
    }

}
