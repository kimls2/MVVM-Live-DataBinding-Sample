/*
 *  Copyright 2017 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.qualson.mvvm_live_databinding.ui.main;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.data.model.GalleryImage;
import com.qualson.mvvm_live_databinding.databinding.MainItemBinding;
import com.qualson.mvvm_live_databinding.ui.common.DataBoundListAdapter;
import com.qualson.mvvm_live_databinding.util.Objects;

public class MainAdapter extends DataBoundListAdapter<GalleryImage, MainItemBinding> {
    private final DataBindingComponent dataBindingComponent;
    private final GalleryClickCallback galleryClickCallback;

    public MainAdapter(DataBindingComponent dataBindingComponent,
                       GalleryClickCallback galleryClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.galleryClickCallback = galleryClickCallback;
    }

    @Override
    protected MainItemBinding createBinding(ViewGroup parent) {
        MainItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.main_item,
                        parent, false, dataBindingComponent);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryImage galleryImage = binding.getGalleryImage();
                galleryClickCallback.onClick(galleryImage);
            }
        });
        return binding;
    }

    @Override
    protected void bind(MainItemBinding binding, GalleryImage item) {
        binding.setGalleryImage(item);
    }

    @Override
    protected boolean areItemsTheSame(GalleryImage oldItem, GalleryImage newItem) {
        return com.qualson.mvvm_live_databinding.util.Objects.equals(oldItem.getDescription(),
                newItem.getDescription()) && com.qualson.mvvm_live_databinding.util.Objects.equals(
                oldItem.getTitle(), newItem.getTitle());
    }

    @Override
    protected boolean areContentsTheSame(GalleryImage oldItem, GalleryImage newItem) {
        return Objects.equals(oldItem.getCover(), newItem.getCover()) && Objects.equals(
                oldItem.getThumbnailSize(), newItem.getThumbnailSize());
    }

    public interface GalleryClickCallback {
        void onClick(GalleryImage galleryImage);
    }


}
