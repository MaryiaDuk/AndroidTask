package com.example.masha.androidapplication.Dz4;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.masha.androidapplication.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ImageView image = findViewById(R.id.image);
        image.setBackgroundResource(R.drawable.owl_anim);
        AnimationDrawable animation = (AnimationDrawable) image.getBackground();
        animation.start();
    }
}
