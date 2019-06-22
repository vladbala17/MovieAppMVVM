package com.android.vlad.movieapparchitecturecomponents.view.movielist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import com.android.vlad.movieapparchitecturecomponents.data.repository.remote.MoviesWebService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:text")
    public static void setFloat(TextView view, float value) {
        if (Float.isNaN(value)) view.setText("");
        else view.setText(String.valueOf(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static float getFloat(TextView view) {
        String num = view.getText().toString();
        if(num.isEmpty()) return 0.0F;
        try {
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            return 0.0F;
        }
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).applyDefaultRequestOptions(RequestOptions.centerCropTransform()).load(MoviesWebService.IMAGE_BASE_URL + url).into(imageView);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).applyDefaultRequestOptions(RequestOptions.circleCropTransform()).load(MoviesWebService.IMAGE_BASE_URL + url).into(view);
    }

}
