package com.android.vlad.movieapparchitecturecomponents.view.moviedetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.vlad.movieapparchitecturecomponents.R;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.databinding.FragmentMovieDetailsBinding;
import com.android.vlad.movieapparchitecturecomponents.viewmodel.MovieViewModel;

public class MovieDetailFragment extends Fragment {
    private static final String KEY_MOVIE_ID = "movie_id";
    private FragmentMovieDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MovieViewModel.Factory factory =
            new MovieViewModel.Factory(getActivity().getApplication(), getArguments().getString(KEY_MOVIE_ID));

        final MovieViewModel viewModel = ViewModelProviders.of(this, factory).get(MovieViewModel.class);

        binding.setMovieViewModel(viewModel);
        binding.setIsLoading(true);
        observeViewModel(viewModel);
    }

    public static MovieDetailFragment forMovie(String id) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();

        args.putString(KEY_MOVIE_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    private void observeViewModel(final MovieViewModel viewModel) {
        viewModel.getMovieObservable().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) {
                    binding.setIsLoading(false);
                    viewModel.setMovie(movie);
                }
            }
        });
    }
}
