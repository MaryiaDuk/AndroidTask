package com.example.masha.androidapplication.Dz8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masha.androidapplication.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class FirstActivity extends AppCompatActivity {
    private PublishSubject<Integer> integerPublishSubject;
    private TextView textView;

    public Observer<Integer> r = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Integer integer) {
            textView.setText(String.valueOf(integer));
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };
    private int x = 0;
    private View.OnClickListener counter = (View v) -> {
        x++;
        integerPublishSubject.onNext(x);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView = findViewById(R.id.textRX);
        LinearLayout linearLayout = findViewById(R.id.hat);
        linearLayout.setOnClickListener(counter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        integerPublishSubject = PublishSubject.create();
        integerPublishSubject.subscribe(r);
    }

    @Override
    protected void onPause() {
        super.onPause();
        integerPublishSubject.onComplete();
    }

}

