package com.example.masha.androidapplication.Dz8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.masha.androidapplication.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textRXTwo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable<Long> interval = Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .filter(Integer -> Integer % 2 == 0).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        interval.subscribe(r);
    }

    private Observer<Long> r = new Observer<Long>() {

        @Override
        public void onSubscribe(Disposable d) {
               SecondActivity.this.disposable = d;
        }

        @Override
        public void onNext(Long aLong) {
            textView.setText(String.valueOf(aLong));
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
