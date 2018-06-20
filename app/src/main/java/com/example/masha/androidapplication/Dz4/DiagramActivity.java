package com.example.masha.androidapplication.Dz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.masha.androidapplication.R;

public class DiagramActivity extends AppCompatActivity {
    private EditText a;
    private EditText b;
    private EditText c;
    private Diagram diagram;
    private View.OnClickListener showDiagram = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                diagram.setData(a.getText(), b.getText(), c.getText());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Пустое поле.", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagram);
        diagram=findViewById(R.id.viewDiagram);
        a = findViewById(R.id.textOne);
        b = findViewById(R.id.textTwo);
        c = findViewById(R.id.textThree);
        Button button = findViewById(R.id.buttonDiagram);
        button.setOnClickListener(showDiagram);
    }
}
