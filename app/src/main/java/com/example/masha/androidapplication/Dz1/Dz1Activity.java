package com.example.masha.androidapplication.Dz1;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.masha.androidapplication.R;

public class Dz1Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz1);
        Button button = findViewById(R.id.button2);
        TextView textView1= findViewById(R.id.textView1);
        TextView textView2=findViewById(R.id.textView2);

        textView1.setOnClickListener(this);
        View.OnClickListener clickListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                click();
            }
        };
        textView2.setOnClickListener(clickListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    @Override
    public void onClick(View v) {
        click();
    }

    private void click(){
        TextView textView1= findViewById(R.id.textView1);
        TextView textView2=findViewById(R.id.textView2);
        String text1=textView1.getText().toString();
        String text2=textView2.getText().toString();
        Drawable color1=textView1.getBackground();
        Drawable color2=textView2.getBackground();
        textView1.setText(text2);
        textView2.setText(text1);
        textView1.setBackground(color2);
        textView2.setBackground(color1);
    }
}
