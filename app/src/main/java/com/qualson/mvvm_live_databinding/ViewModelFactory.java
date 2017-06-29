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

package com.qualson.mvvm_live_databinding;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.ui.main.MainViewModel;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  @SuppressLint("StaticFieldLeak") private static volatile ViewModelFactory INSTANCE;

  private final DataManager dataManager;
  private final Application application;

  public static ViewModelFactory getInstance(Application application) {
    synchronized (ViewModelFactory.class) {
      if (INSTANCE == null) {
        INSTANCE = new ViewModelFactory(application);
      }
    }
    return INSTANCE;
  }

  private ViewModelFactory(Application application) {
    this.application = application;
    this.dataManager = ((MyApp) application).getDataManager();
  }

  @Override public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(application, dataManager);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}