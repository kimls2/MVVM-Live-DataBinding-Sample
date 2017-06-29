package com.qualson.mvvm_live_databinding;

import android.app.Application;
import android.content.Context;
import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.injection.component.ApplicationComponent;
import com.qualson.mvvm_live_databinding.injection.component.DaggerApplicationComponent;
import com.qualson.mvvm_live_databinding.injection.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by ykim on 2017. 6. 28..
 */

public class MyApp extends Application {

  @Inject DataManager dataManager;
  private final ApplicationComponent applicationComponent = createApplicationComponent();
  private RefWatcher refWatcher;

  @Override public void onCreate() {
    super.onCreate();

    applicationComponent.inject(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      if (LeakCanary.isInAnalyzerProcess(this)) {
        return;
      }
      refWatcher = LeakCanary.install(this);
    }

    //dataManager = applicationComponent.dataManager();
  }

  protected ApplicationComponent createApplicationComponent() {
    return DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  public static MyApp get(Context context) {
    return (MyApp) context.getApplicationContext();
  }

  public ApplicationComponent getComponent() {
    return applicationComponent;
  }

  public DataManager getDataManager() {
    return dataManager;
  }

  public static RefWatcher getRefWatcher(Context context) {
    MyApp application = (MyApp) context.getApplicationContext();
    return application.refWatcher;
  }
}
