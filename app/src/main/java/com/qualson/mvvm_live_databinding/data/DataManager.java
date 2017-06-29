package com.qualson.mvvm_live_databinding.data;

import com.qualson.mvvm_live_databinding.data.model.GalleryImage;
import com.qualson.mvvm_live_databinding.data.model.GalleryResponse;
import com.qualson.mvvm_live_databinding.data.remote.ImgurService;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by ykim on 2017. 4. 11..
 */

@Singleton public class DataManager {

  private final ImgurService mImgurService;

  @Inject public DataManager(ImgurService mImgurService) {
    this.mImgurService = mImgurService;
  }

  public Observable<List<GalleryImage>> getGallery() {
    return mImgurService.getGallery("hot", "viral")
        .filter(response -> response.isSuccess() && CollectionUtils.isNotEmpty(response.getData()))
        .map(GalleryResponse::getData);
  }

  public Observable<GalleryResponse> getResponse() {
    return mImgurService.getGallery("hot", "viral");
  }

  public String getTest() {
    return "test";
  }
}
