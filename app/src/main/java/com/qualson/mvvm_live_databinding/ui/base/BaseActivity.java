package com.qualson.mvvm_live_databinding.ui.base;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v7.app.AppCompatActivity;
import com.qualson.mvvm_live_databinding.BuildConfig;
import com.qualson.mvvm_live_databinding.MyApp;
import com.qualson.mvvm_live_databinding.injection.component.ActivityComponent;
import com.qualson.mvvm_live_databinding.injection.component.DaggerActivityComponent;

/**
 * Created by ykim on 2017. 4. 11..
 */

public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

  private ActivityComponent activityComponent;
  private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

  public ActivityComponent activityComponent() {
    if (activityComponent == null) {
      activityComponent = DaggerActivityComponent.builder()
          .applicationComponent(MyApp.get(this).getComponent())
          .build();
    }
    return activityComponent;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (BuildConfig.DEBUG) {
      MyApp.getRefWatcher(this).watch(this);
    }
  }

  @Override public LifecycleRegistry getLifecycle() {
    return lifecycleRegistry;
  }
}
