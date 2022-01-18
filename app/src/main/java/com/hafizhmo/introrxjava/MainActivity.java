package com.hafizhmo.introrxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
    * Disposable is used to dispose the subscription when an Observer no longer
    * wants to listen to Observable. In android disposable are very useful in
    * avoiding memory leaks. */
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // observable.
        Observable<String> animalsObservable = getAnimalsObservable();

        //observer.
        Observer<String> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable.
        animalsObservable
                .observeOn(Schedulers.io()) // This tells the Observer to receive the data on android UI thread so that you can take any UI related actions.
                .subscribeOn(AndroidSchedulers.mainThread()) // This tell the Observable to run the task on a background thread.
                .subscribe(animalsObserver);

    }

    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {

            // Method will be called when an Observer subscribes to Observable.
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            // This method will be called when Observable starts emitting the data.
            @Override
            public void onNext(@NonNull String s) {
                Log.d(TAG, "Name: " + s);
            }

            // In case of any error, onError() method will be called.
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            // When an Observable completes the emission of all the items, onComplete() will be called.
            @Override
            public void onComplete() {
                Log.d(TAG, "All members are emitted!");
            }
        };
    }

    private Observable<String> getAnimalsObservable() {
        return Observable.just("Yiren", "Aisha", "SiHyeon", "Onda", "Mia", "E:U");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose(); // calling disposable.dispose() in onDestroy() will un-subscribe the Observer.
    }
}