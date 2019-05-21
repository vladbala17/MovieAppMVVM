package com.android.vlad.movieapparchitecturecomponents.data.repository.remote;

import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesWebService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500";

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String key);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieById(@Path("movie_id") String movieId, @Query("api_key") String key);
}
