package com.android.vlad.movieapparchitecturecomponents.view.movielist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.android.vlad.movieapparchitecturecomponents.R;
import com.android.vlad.movieapparchitecturecomponents.data.model.Movie;
import com.android.vlad.movieapparchitecturecomponents.databinding.MovieItemLayoutBinding;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private MovieClickCallback movieClickCallback;
    private List<Movie> movieList;

    public MovieAdapter(MovieClickCallback movieClickCallback) {
        this.movieClickCallback = movieClickCallback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
            R.layout.movie_item_layout,parent, false);

        binding.setCallback(movieClickCallback);

        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.setMovie(movieList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(final List<Movie> movies) {
        if (movieList == null) {
            movieList = movies;
            notifyItemRangeChanged(0, movies.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movies.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return movieList.get(oldItemPosition).getId() == movies.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Movie movie = movies.get(newItemPosition);
                    Movie oldMovie = movies.get(oldItemPosition);
                    return movie.getId() == oldMovie.getId();
                }
            });
            movieList = movies;
            result.dispatchUpdatesTo(this);
        }
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieItemLayoutBinding binding;

        public MovieViewHolder(@NonNull MovieItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
