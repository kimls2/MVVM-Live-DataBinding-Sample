package com.qualson.mvvm_live_databinding.util;

import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageUtil {

  public static void loadImage(ImageView imageView, String url) {
    Glide.with(imageView.getContext()).load(url).centerCrop().into(imageView);
  }
}
