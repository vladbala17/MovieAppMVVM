package com.android.vlad.movieapparchitecturecomponents.di;

import com.android.vlad.movieapparchitecturecomponents.viewmodel.MovieListViewModel;
import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    MovieListViewModel movieListViewModel();
}
