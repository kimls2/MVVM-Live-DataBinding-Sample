package com.qualson.mvvm_live_databinding.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.qualson.mvvm_live_databinding.data.DataManager;
import com.qualson.mvvm_live_databinding.data.SingleLiveEvent;
import com.qualson.mvvm_live_databinding.data.model.GalleryImage;
import com.qualson.mvvm_live_databinding.util.SnackbarMessage;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Exposes the data to be used in the statistics screen.
 * <p>
 * This ViewModel uses both {@link ObservableField}s ({@link ObservableBoolean}s in this case) and
 * {@link Bindable} getters. The values in {@link ObservableField}s are used directly in the layout,
 * whereas the {@link Bindable} getters allow us to add some logic to it. This is
 * preferable to having logic in the XML layout.
 */
public class MainViewModel extends AndroidViewModel {

    private final DataManager dataManager;
    private final CompositeDisposable compositeDisposable;
    private SingleLiveEvent<List<GalleryImage>> singleLiveEvent;
    private SnackbarMessage snackbarMessage = new SnackbarMessage();

    @Inject
    public MainViewModel(Application context, DataManager dataManager) {
        super(context);
        this.dataManager = dataManager;
        singleLiveEvent = new SingleLiveEvent<>();
        compositeDisposable = new CompositeDisposable();
    }

    public void start() {
        loadImages();
    }

    public void loadImages() {
        compositeDisposable.add(dataManager.getGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<GalleryImage>>() {
                    @Override
                    public void onNext(@NonNull List<GalleryImage> galleryImages) {
                        singleLiveEvent.setValue(galleryImages);
                        snackbarMessage.setValue("success");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e.getMessage());
                        snackbarMessage.setValue(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        snackbarMessage.setValue("completed");
                    }
                }));
    }

    public void destroy() {
        compositeDisposable.clear();
    }

    public SingleLiveEvent<List<GalleryImage>> getGalleryImages() {
        return singleLiveEvent;
    }

    public SnackbarMessage getSnackbarMessage() {
        return snackbarMessage;
    }

    public void getTest() {
        dataManager.getResponse();
    }
}
