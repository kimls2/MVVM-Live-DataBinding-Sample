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

package com.qualson.mvvm_live_databinding.ui.common;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.data.model.GalleryImage;
import com.qualson.mvvm_live_databinding.databinding.ItemMainBinding;
import com.qualson.mvvm_live_databinding.util.Objects;

public class TestMainAdapter extends DataBoundListAdapter<GalleryImage, ItemMainBinding> {
  private final DataBindingComponent dataBindingComponent;
  private final GalleryClickCallback galleryClickCallback;

  public TestMainAdapter(DataBindingComponent dataBindingComponent,
      GalleryClickCallback galleryClickCallback) {
    this.dataBindingComponent = dataBindingComponent;
    this.galleryClickCallback = galleryClickCallback;
  }

  @Override protected ItemMainBinding createBinding(ViewGroup parent) {
    ItemMainBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main,
            parent, false, dataBindingComponent);
    return binding;
  }

  @Override protected void bind(ItemMainBinding binding, GalleryImage item) {
    binding.setGalleryImage(item);
  }

  @Override protected boolean areItemsTheSame(GalleryImage oldItem, GalleryImage newItem) {
    return com.qualson.mvvm_live_databinding.util.Objects.equals(oldItem.getDescription(),
        newItem.getDescription()) && com.qualson.mvvm_live_databinding.util.Objects.equals(
        oldItem.getTitle(), newItem.getTitle());
  }

  @Override protected boolean areContentsTheSame(GalleryImage oldItem, GalleryImage newItem) {
    return Objects.equals(oldItem.getCover(), newItem.getCover()) && Objects.equals(
        oldItem.getThumbnailSize(), newItem.getThumbnailSize());
  }

  public interface GalleryClickCallback {
    void onClick(GalleryImage repo);
  }

  //private List<GalleryImage> items;
  //
  //public TestMainAdapter() {
  //  items = new ArrayList<>();
  //}
  //
  //public void replaceData(List<GalleryImage> tasks) {
  //  setList(tasks);
  //}
  //
  //@Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
  //  ItemMainBinding binding =
  //      DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main,
  //          parent, false);
  //
  //  return new MainViewHolder(binding);
  //}
  //
  //@Override public void onBindViewHolder(MainViewHolder holder, int position) {
  //  holder.binding.setGalleryImage(items.get(position));
  //  holder.binding.executePendingBindings();
  //}
  //
  //@Override public int getItemCount() {
  //  return items.size();
  //}
  //
  //private void setList(List<GalleryImage> tasks) {
  //  items = tasks;
  //  notifyDataSetChanged();
  //}
  //
  //static class MainViewHolder extends RecyclerView.ViewHolder {
  //  final ItemMainBinding binding;
  //
  //  MainViewHolder(ItemMainBinding binding) {
  //    super(binding.getRoot());
  //    this.binding = binding;
  //  }
  //}
}
