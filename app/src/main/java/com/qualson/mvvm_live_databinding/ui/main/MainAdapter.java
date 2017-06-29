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

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.data.model.GalleryImage;
import com.qualson.mvvm_live_databinding.databinding.ItemMainBinding;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

  private List<GalleryImage> items;

  public MainAdapter() {
    items = new ArrayList<>();
  }

  public void replaceData(List<GalleryImage> tasks) {
    setList(tasks);
  }

  @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemMainBinding binding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main,
            parent, false);

    return new MainViewHolder(binding);
  }

  @Override public void onBindViewHolder(MainViewHolder holder, int position) {
    holder.binding.setGalleryImage(items.get(position));
    holder.binding.executePendingBindings();
  }

  @Override public int getItemCount() {
    return items.size();
  }

  private void setList(List<GalleryImage> tasks) {
    items = tasks;
    notifyDataSetChanged();
  }

  static class MainViewHolder extends RecyclerView.ViewHolder {
    final ItemMainBinding binding;

    MainViewHolder(ItemMainBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
