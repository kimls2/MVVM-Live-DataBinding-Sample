package com.qualson.mvvm_live_databinding.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.ViewModelFactory;
import com.qualson.mvvm_live_databinding.ui.base.BaseActivity;
import com.qualson.mvvm_live_databinding.util.ActivityUtils;

public class MainActivity extends BaseActivity {


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityComponent().inject(this);
    setContentView(R.layout.activity_main);

    findOrCreateViewFragment();
  }

  public static MainViewModel obtainViewModel(FragmentActivity activity) {
    ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
    return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
  }

  @NonNull private MainFragment findOrCreateViewFragment() {
    MainFragment mainFragment =
        (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (mainFragment == null) {
      mainFragment = MainFragment.newInstance();
      ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), mainFragment,
          R.id.contentFrame);
    }
    return mainFragment;
  }
}
