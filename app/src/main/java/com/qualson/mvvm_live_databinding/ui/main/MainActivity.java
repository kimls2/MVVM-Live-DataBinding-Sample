package com.qualson.mvvm_live_databinding.ui.main;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.ui.base.BaseActivity;
import com.qualson.mvvm_live_databinding.util.ActivityUtils;

import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements LifecycleRegistryOwner, HasSupportFragmentInjector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findOrCreateViewFragment();
    }

    @NonNull
    private MainFragment findOrCreateViewFragment() {
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
