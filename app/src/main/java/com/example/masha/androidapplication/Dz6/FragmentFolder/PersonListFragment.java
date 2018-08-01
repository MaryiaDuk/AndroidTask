package com.example.masha.androidapplication.Dz6.FragmentFolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.masha.androidapplication.Dz6.Person.PersonAdapter;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;


public class PersonListFragment extends Fragment {
    private static int position;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private PersonList personList;
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
    private View.OnClickListener listener = v -> showFragmentTablet(new CreatePersonFragment());

    public static int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        personList = Singleton.getState().getPeople();
        Button btnCreate = view.findViewById(R.id.addNewPersonFr);
        recyclerView = view.findViewById(R.id.recyclerViewFr);
        recyclerView();
        btnCreate.setOnClickListener(listener);
        EditText findU = view.findViewById(R.id.searchPerson);
        findU.addTextChangedListener(watcher);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // recyclerView();
    }

    public void recyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(position -> {
            showFragmentTablet(new PersonFragment());
            setPosition(position);
        });
        recyclerView.setAdapter(personAdapter);
    }

    public void showFragmentTablet(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.person_fragment_tablet, fragment);
        transition.addToBackStack(null);
        transition.commit();
    }
}