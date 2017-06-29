package com.qualson.mvvm_live_databinding.injection.component;

import com.qualson.mvvm_live_databinding.ui.main.MainActivity;
import com.qualson.mvvm_live_databinding.injection.PerActivity;
import com.qualson.mvvm_live_databinding.injection.module.ActivityModule;
import dagger.Component;

@PerActivity @Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity mainActivity);
}

