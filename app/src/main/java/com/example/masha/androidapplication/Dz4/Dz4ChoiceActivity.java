package com.example.masha.androidapplication.Dz4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.masha.androidapplication.R;

public class Dz4ChoiceActivity extends AppCompatActivity {
private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4_choice);
        Button clock = findViewById(R.id.clockButton);
        Button diagram = findViewById(R.id.diagramButton);
        Button anim = findViewById(R.id.animButton);

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(Dz4ChoiceActivity.this, ClockActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in, R.anim.out);
            }
        });

        diagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Dz4ChoiceActivity.this, DiagramActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in, R.anim.out);

            }
        });

        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Dz4ChoiceActivity.this, AnimationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in, R.anim.out);

            }
        });
    }
}
