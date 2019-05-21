package com.android.vlad.movieapparchitecturecomponents.di;

import androidx.lifecycle.ViewModelProvider;
import com.android.vlad.movieapparchitecturecomponents.data.repository.remote.MoviesWebService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AppModule {

    @Singleton
    @Provides
    MoviesWebService provideMovieWebService() {
        return new Retrofit.Builder().baseUrl(MoviesWebService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesWebService.class);
    }

    ViewModelProvider.Factory provideViewModelFactory(ViewModelSubComponent.Builder viewModelSubComponent) {
        return null;
    }
}
