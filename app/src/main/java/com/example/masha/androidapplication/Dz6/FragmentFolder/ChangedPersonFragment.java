package com.example.masha.androidapplication.Dz6.FragmentFolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.masha.androidapplication.Dz6.Person.Person;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;

public class ChangedPersonFragment extends Fragment {
    private Person p;

    private EditText name, surname, age, id, gender;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        p = Singleton.getState().getPeople().getPerson(PersonListFragment.getPosition());
        Button btnCancel = view.findViewById(R.id.changeCancelBtn);
        Button btnSave = view.findViewById(R.id.changeSaveBtn);

        btnCancel.setOnClickListener(listener);
        btnSave.setOnClickListener(listener);

        id = view.findViewById(R.id.changeId);
        name = view.findViewById(R.id.changeName);
        surname = view.findViewById(R.id.changeSurname);

        age = view.findViewById(R.id.changeAge);
        gender = view.findViewById(R.id.changeGender);
        hint();
    }


    public void hint() {
        name.setHint(p.getName());
        surname.setHint(p.getSurname());
        gender.setHint(p.getGender());
        age.setHint(p.getAge());
        id.setHint(p.getId());
    }

    private View.OnClickListener listener = v -> {
        if (v.getId() == R.id.changeCancelBtn) {
            getFragmentManager().popBackStack();
        }
        if (v.getId() == R.id.changeSaveBtn) {
            update();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.personlist_fragment_tablet, new PersonListFragment());
            transaction.commit();
            getFragmentManager().popBackStack();
        }
    };

    public void update() {
        if (name.getText().length() != 0)
            p.setName(name.getText().toString());
        if (surname.getText().length() != 0)
            p.setSurname(surname.getText().toString());
        if (gender.getText().length() != 0)
            p.setGender(gender.getText().toString());
        if (id.getText().length() != 0)
            p.setId(String.valueOf(id));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_changed_person, container, false);
    }
}
