package com.qualson.mvvm_live_databinding.ui.main;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.binding.FragmentDataBindingComponent;
import com.qualson.mvvm_live_databinding.databinding.MainFragmentBinding;
import com.qualson.mvvm_live_databinding.di.Injectable;
import com.qualson.mvvm_live_databinding.util.AutoClearedValue;
import com.qualson.mvvm_live_databinding.util.SnackbarMessage;
import com.qualson.mvvm_live_databinding.util.SnackbarUtils;

import javax.inject.Inject;

/**
 * Created by ykim on 2017. 6. 28..
 */

public class MainFragment extends Fragment implements LifecycleRegistryOwner, Injectable {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MainViewModel mainViewModel;
    private android.databinding.DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    private AutoClearedValue<MainFragmentBinding> binding;
    private AutoClearedValue<MainAdapter> adapter;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        MainAdapter adapter = new MainAdapter(dataBindingComponent, galleryImage -> SnackbarUtils.showSnackbar(getView(), galleryImage.getTitle()));
        this.adapter = new AutoClearedValue<>(this, adapter);
        binding.get().imageList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.get().imageList.setAdapter(adapter);
        mainViewModel.getGalleryImages().observe(this, galleryImages -> this.adapter.get().replace(galleryImages));
        mainViewModel.getSnackbarMessage().observe(this, (SnackbarMessage.SnackbarObserver) message -> SnackbarUtils.showSnackbar(getView(), message));
        mainViewModel.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        MainFragmentBinding dataBinding =
                DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        binding = new AutoClearedValue<>(this, dataBinding);
        return dataBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
//        mainViewModel.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainViewModel.destroy();
    }

    // for testing
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }


}
