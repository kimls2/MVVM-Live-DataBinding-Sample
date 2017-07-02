package com.qualson.mvvm_live_databinding.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.qualson.mvvm_live_databinding.ui.main.MainViewModel;
import com.qualson.mvvm_live_databinding.viewmodel.MyViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module abstract class ViewModelModule {
  @Binds @IntoMap @ViewModelKey(MainViewModel.class)
  abstract ViewModel bindMainViewModel(MainViewModel userViewModel);

  @Binds abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);
}
