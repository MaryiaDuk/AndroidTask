package com.example.masha.androidapplication.Dz3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.masha.androidapplication.R;
import com.squareup.picasso.Picasso;

public class Dz3Activity extends AppCompatActivity {
   private Button button;
    private EditText editText;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3);
        String link = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Lilium_candidum_2.jpg/275px-Lilium_candidum_2.jpg";
         button = findViewById(R.id.buttonHTTP);
         editText = findViewById(R.id.textHTTP);
         image = findViewById(R.id.imageHTTP);

        editText.setText(link);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(editText.getText().toString())
                        .into(image);
            }
        });
    }





}
