package com.example.masha.androidapplication.Dz8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.masha.androidapplication.R;

public class Hometask8Activity extends AppCompatActivity {
    private View.OnClickListener RXOne = (View v) -> {
        startActivity(new Intent(this, FirstActivity.class));
    };

    private View.OnClickListener RXTwo = (View v) -> {
        startActivity(new Intent(this, SecondActivity.class));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometask8);
        Button rxOne = findViewById(R.id.buttonRXFirst);
        Button rxTwo = findViewById(R.id.buttonRXSecond);
        rxOne.setOnClickListener(RXOne);
        rxTwo.setOnClickListener(RXTwo);
    }

}
