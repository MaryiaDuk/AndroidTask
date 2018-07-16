package com.example.masha.androidapplication.Dz6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.masha.androidapplication.Dz6.Person.CreatePersonActivity;
import com.example.masha.androidapplication.Dz6.Person.PersonAdapter;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.R;

public class Hometask6Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PersonList personList;
    private Button btnCreate;
    private PersonAdapter personAdapter;

    private EditText findU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometask6);
        init();
        recyclerView();
    }
    public void init() {
        btnCreate = findViewById(R.id.addNewPerson);
        btnCreate.setOnClickListener(listener);

        personList = Singleton.getState().getPeople();
        findU = findViewById(R.id.searchPerson);
        findU.addTextChangedListener(watcher);
    }

    public void recyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Hometask6Activity.this));
        recyclerView.setHasFixedSize(true);
        personAdapter = new PersonAdapter(new PersonAdapter.Holder.CustomClickListener() {
            @Override
            public void onUserClickListener(int position) {
                EditPersonActivity.start(Hometask6Activity.this, position);
                finish();
            }
        });
        recyclerView.setAdapter(personAdapter);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            personAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private View.OnClickListener listener = v -> {
        CreatePersonActivity.start(Hometask6Activity.this);
        finish();
    };
}
