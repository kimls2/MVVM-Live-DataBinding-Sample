package com.qualson.mvvm_live_databinding;

import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.data.model.GalleryResponse;
import com.qualson.mvvm_live_databinding.data.remote.ImgurService;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by ykim on 2017. 6. 29..
 */
@RunWith(MockitoJUnitRunner.class) public class DataManagerTest {

  @Mock ImgurService imgurService;
  @InjectMocks DataManager dataManager;

  @Test public void getImages() {
    GalleryResponse response = new GalleryResponse().setSuccess(true);

    Mockito.doReturn(Observable.just(response)).when(imgurService).getGallery("test", "test");

    TestObserver<GalleryResponse> testObserver = new TestObserver<>();
    //dataManager.getImage().subscribe(testObserver);
    testObserver.assertComplete();
    testObserver.assertNoErrors();
  }
}