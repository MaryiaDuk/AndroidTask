package com.example.masha.androidapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.masha.androidapplication.Dz1.Dz1Activity;
import com.example.masha.androidapplication.Dz2.Dz2Activity;
import com.example.masha.androidapplication.Dz3.Dz3Activity;
import com.example.masha.androidapplication.Dz4.Dz4ChoiceActivity;
import com.example.masha.androidapplication.Dz5.Dz5Activity;
import com.example.masha.androidapplication.Dz6.Hometask6Activity;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.Dz8.Hometask8Activity;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener buttonDZ1 = (View v) -> startActivity(new Intent(this, Dz1Activity.class));
    private View.OnClickListener buttonDZ2 = (View v) -> startActivity(new Intent(this, Dz2Activity.class));
    private View.OnClickListener buttonDZ3 = (View v) -> startActivity(new Intent(this, Dz3Activity.class));
    private View.OnClickListener buttonDZ4 = (View v) -> startActivity(new Intent(this, Dz4ChoiceActivity.class));
    private View.OnClickListener buttonDZ5 = (View v) -> startActivity(new Intent(this, Dz5Activity.class));
    private View.OnClickListener buttonDZ6 = (View v) -> startActivity(new Intent(this, Hometask6Activity.class));
    private View.OnClickListener buttonDZ8 = (View v) -> startActivity(new Intent(this, Hometask8Activity.class));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Singleton.getState();
        Button buttonDz1 = findViewById(R.id.buttonDz1);
        Button buttonDz2 = findViewById(R.id.buttonDz2);
        Button buttonDz3 = findViewById(R.id.buttonDz3);
        Button buttonDz4 = findViewById(R.id.buttonDz4);
        Button buttonDz5 = findViewById(R.id.buttonDz5);
        Button buttonDz6 = findViewById(R.id.buttonDz6);
        Button buttonDz8 = findViewById(R.id.buttonDz8);
        buttonDz1.setOnClickListener(buttonDZ1);
        buttonDz2.setOnClickListener(buttonDZ2);
        buttonDz3.setOnClickListener(buttonDZ3);
        buttonDz4.setOnClickListener(buttonDZ4);
        buttonDz5.setOnClickListener(buttonDZ5);
        buttonDz6.setOnClickListener(buttonDZ6);
        buttonDz8.setOnClickListener(buttonDZ8);
    }
}
