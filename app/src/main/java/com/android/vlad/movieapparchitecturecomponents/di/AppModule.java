package com.android.vlad.movieapparchitecturecomponents.di;

import com.android.vlad.movieapparchitecturecomponents.data.repository.remote.MoviesWebService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static MoviesWebService provideRetrofit() {
        return new Retrofit.Builder().baseUrl(MoviesWebService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesWebService.class);
    }
}
