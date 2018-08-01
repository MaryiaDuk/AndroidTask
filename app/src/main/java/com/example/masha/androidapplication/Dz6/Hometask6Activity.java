package com.example.masha.androidapplication.Dz6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.masha.androidapplication.Dz6.FragmentFolder.PersonListFragment;
import com.example.masha.androidapplication.Dz6.Person.CreatePersonActivity;
import com.example.masha.androidapplication.Dz6.Person.PersonAdapter;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.R;

public class Hometask6Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btnCreate;
    private PersonAdapter personAdapter;
    private FrameLayout tablet;

    private EditText findPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometask6);
        tablet = findViewById(R.id.person_fragment_tablet);
        if (tablet == null) {
            init();
            recyclerView();
        } else {
            showFragmentTablet(new PersonListFragment());
        }
    }
    public void init() {
        btnCreate = findViewById(R.id.addNewPerson);
        btnCreate.setOnClickListener(listener);

        PersonList personList = Singleton.getState().getPeople();
        findPerson = findViewById(R.id.searchPerson);
        findPerson.addTextChangedListener(watcher);
    }
    public void showFragmentTablet(Fragment fragment) {
               FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transition = fragmentManager.beginTransaction();
                transition.replace(R.id.personlist_fragment_tablet, fragment);
                transition.commit();
    }
    public void recyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Hometask6Activity.this));
        recyclerView.setHasFixedSize(true);
        personAdapter = new PersonAdapter(position -> {
            EditPersonActivity.start(Hometask6Activity.this, position);
            finish();
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
