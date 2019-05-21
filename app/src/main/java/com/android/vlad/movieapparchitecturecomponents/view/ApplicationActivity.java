package com.android.vlad.movieapparchitecturecomponents.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.android.vlad.movieapparchitecturecomponents.R;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.view.moviedetail.MovieDetailFragment;
import com.android.vlad.movieapparchitecturecomponents.view.movielist.MovieListFragment;

public class ApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        if (savedInstanceState == null) {
            MovieListFragment fragment = new MovieListFragment();

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment,
                MovieListFragment.TAG).commit();
        }

    }

    /** Shows the project detail fragment */
    public void show(Movie movie) {
         MovieDetailFragment detailFragment= MovieDetailFragment.forMovie(movie.getId());

        getSupportFragmentManager()
            .beginTransaction()
            .addToBackStack("movie")
            .replace(R.id.fragment_container,
                detailFragment, null).commit();
    }
}
