package com.qualson.mvvm_live_databinding.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by ykim on 2017. 4. 11..
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
