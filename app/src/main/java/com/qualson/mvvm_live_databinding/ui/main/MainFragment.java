package com.qualson.mvvm_live_databinding.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qualson.mvvm_live_databinding.R;
import com.qualson.mvvm_live_databinding.SnackbarMessage;
import com.qualson.mvvm_live_databinding.binding.FragmentDataBindingComponent;
import com.qualson.mvvm_live_databinding.databinding.FragmentMainBinding;
import com.qualson.mvvm_live_databinding.ui.common.TestMainAdapter;
import com.qualson.mvvm_live_databinding.util.SnackbarUtils;

/**
 * Created by ykim on 2017. 6. 28..
 */

public class MainFragment extends Fragment {

  private MainViewModel mainViewModel;
  private FragmentMainBinding fragmentMainBinding;
  private MainAdapter adapter;
  private TestMainAdapter testMainAdapter;

  DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    fragmentMainBinding =
        DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
    return fragmentMainBinding.getRoot();
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mainViewModel = MainActivity.obtainViewModel(getActivity());
    adapter = new MainAdapter();
    testMainAdapter =
        new TestMainAdapter(dataBindingComponent, repo -> {

        });
    fragmentMainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    fragmentMainBinding.recyclerView.setAdapter(adapter);
    mainViewModel.getGalleryImages()
        .observe((LifecycleOwner) getActivity(),
            galleryImages -> adapter.replaceData(galleryImages));
    mainViewModel.getSnackbarMessage()
        .observe((LifecycleOwner) getActivity(), new SnackbarMessage.SnackbarObserver() {
          @Override public void onNewMessage(String message) {
            SnackbarUtils.showSnackbar(getView(), message);
          }
        });
  }

  @Override public void onResume() {
    super.onResume();
    mainViewModel.start();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mainViewModel.destroy();
  }

  // for testing
  public boolean isActive() {
    return isAdded();
  }
}
