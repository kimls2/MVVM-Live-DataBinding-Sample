package com.qualson.mvvm_live_databinding.main;

import android.app.Application;
import android.arch.core.executor.testing.InstantTaskExecutorRule;
import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.data.model.GalleryResponse;
import com.qualson.mvvm_live_databinding.ui.main.MainViewModel;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

/**
 * Created by ykim on 2017. 6. 29..
 */
public class MainViewModelTest {

  @Rule public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

  @Mock private DataManager dataManager;
  private MainViewModel mainViewModel;

  @Before public void setUpMainViewModel() {
    MockitoAnnotations.initMocks(this);
    mainViewModel = new MainViewModel(mock(Application.class), dataManager);
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(
        schedulerCallable -> Schedulers.trampoline());
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
  }

  @After public void tearDown() {
    RxAndroidPlugins.reset();
    RxJavaPlugins.reset();
  }

  @Test public void loadImage_showsSuccessful() {
    //mainViewModel = new MainViewModel(app, dataManager);
    //mainViewModel.loadImages();
    GalleryResponse response = new GalleryResponse().setSuccess(true);
    Mockito.doReturn(Observable.just(response)).when(dataManager).getResponse();
    mainViewModel.getTest();
    TestObserver<GalleryResponse> testObserver = new TestObserver<>();
    dataManager.getResponse().subscribe(testObserver);
    testObserver.assertComplete();
    testObserver.assertNoErrors();


  }
}