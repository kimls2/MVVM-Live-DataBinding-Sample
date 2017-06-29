package com.qualson.mvvm_live_databinding.injection.module;

import android.content.Context;
import com.qualson.mvvm_live_databinding.MyApp;
import com.qualson.mvvm_live_databinding.data.remote.ImgurService;
import com.qualson.mvvm_live_databinding.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  private final MyApp mApplication;

  public ApplicationModule(MyApp application) {
    mApplication = application;
  }

  @Provides MyApp provideApplication() {
    return mApplication;
  }

  @Provides @ApplicationContext Context provideContext() {
    return mApplication;
  }

  @Provides @Singleton ImgurService provideImgurService() {
    return ImgurService.Factory.makeImugurService();
  }
}
