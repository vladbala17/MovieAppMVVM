package com.android.vlad.movieapparchitecturecomponents.di;

import com.android.vlad.movieapparchitecturecomponents.application.BaseApplication;
import com.android.vlad.movieapparchitecturecomponents.data.repository.MovieDataSource;
import dagger.BindsInstance;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(MovieDataSource dataSource);
    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder dataSource(MovieDataSource movieDataSource);
        ApplicationComponent build();

    }
}
