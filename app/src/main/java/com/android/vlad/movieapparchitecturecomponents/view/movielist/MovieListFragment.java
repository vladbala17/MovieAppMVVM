package com.android.vlad.movieapparchitecturecomponents.view.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.vlad.movieapparchitecturecomponents.R;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.databinding.FragmentMovieListBinding;
import com.android.vlad.movieapparchitecturecomponents.view.ApplicationActivity;
import com.android.vlad.movieapparchitecturecomponents.viewmodel.MovieListViewModel;
import java.util.List;

public class MovieListFragment extends Fragment implements MovieClickCallback {
    public static final String TAG = "MovieListFragment";
    private MovieAdapter movieAdapter;
    FragmentMovieListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);

        movieAdapter = new MovieAdapter(this);
        binding.movieRecyclerView.setAdapter(movieAdapter);
        binding.setIsLoading(true);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MovieListViewModel viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(MovieListViewModel viewModel) {
        viewModel.getMovieListObservable().observe(this, new Observer<List<Movie>>() {

            @Override
            public void onChanged(List<Movie> movies) {
                binding.setIsLoading(false);
                movieAdapter.setMovieList(movies);
            }
        });
    }

    @Override
    public void onClick(Movie movie) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            ((ApplicationActivity) getActivity()).show(movie);
        }
    }
}
