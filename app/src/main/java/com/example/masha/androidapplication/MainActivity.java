package com.example.masha.androidapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.masha.androidapplication.Dz1.Dz1Activity;
import com.example.masha.androidapplication.Dz2.Dz2Activity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDz1 = findViewById(R.id.buttonDz1);
        Button buttonDz2 = findViewById(R.id.buttonDz2);

        buttonDz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dz1Activity.class);
                startActivity(intent);
            }
        });

        buttonDz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dz2Activity.class);
                startActivity(intent);
            }
        });

    }
}
