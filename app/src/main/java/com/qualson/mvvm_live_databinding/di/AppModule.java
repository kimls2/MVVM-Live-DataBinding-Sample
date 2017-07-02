/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qualson.mvvm_live_databinding.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qualson.mvvm_live_databinding.BuildConfig;
import com.qualson.mvvm_live_databinding.data.remote.ImgurService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class) class AppModule {
  @Singleton @Provides ImgurService provideImgurService() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(
        BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    OkHttpClient okHttpClient = new OkHttpClient.Builder() //
        .addInterceptor(logging)  //
        .addInterceptor(chain -> {
          Request request = chain.request()
              .newBuilder()
              .addHeader("Authorization", "Client-ID fb89f7a636907c2")
              .build();
          return chain.proceed(request);
        }) //
        .build();

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    Retrofit retrofit = new Retrofit.Builder() //
        .baseUrl(ImgurService.ENDPOINT) //
        .client(okHttpClient) //
        .addConverterFactory(GsonConverterFactory.create(gson)) //
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
        .build();
    return retrofit.create(ImgurService.class);
  }
}
