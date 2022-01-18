package com.hafizhmo.introrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> animalsObservable = Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");

        Log.d(TAG, "\nAnimals Observable: " + animalsObservable.blockingLast());

    }
}