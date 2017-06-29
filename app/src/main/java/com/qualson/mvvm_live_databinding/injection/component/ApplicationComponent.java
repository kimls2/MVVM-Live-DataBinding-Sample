package com.qualson.mvvm_live_databinding.injection.component;

import android.content.Context;
import com.qualson.mvvm_live_databinding.MyApp;
import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.injection.ApplicationContext;
import com.qualson.mvvm_live_databinding.injection.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by ykim on 2017. 4. 11..
 */

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  @ApplicationContext Context context();

  void inject(MyApp app);

  DataManager dataManager();
}
