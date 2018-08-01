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
import android.widget.ImageView;

import com.example.masha.androidapplication.Dz3.CircleImageTransformation;
import com.example.masha.androidapplication.Dz6.Person.Person;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;
import com.squareup.picasso.Picasso;


public class PersonFragment extends Fragment {

    private static Person person;
    private PersonList personList;

    private ImageView imageView;

    private EditText name, surname, age, gender;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        person = Singleton.getState().getPeople().getPerson(PersonListFragment.getPosition());

        Button btnDelete = view.findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(listener);

        Button btnEdit = view.findViewById(R.id.buttonChange);
        btnEdit.setOnClickListener(listener);

        name = view.findViewById(R.id.editName);
        surname = view.findViewById(R.id.editSurname);
        imageView = view.findViewById(R.id.imagePerson);
        age = view.findViewById(R.id.editAge);
        gender = view.findViewById(R.id.editGender);

        personList = Singleton.getState().getPeople();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fill();
    }

    public void fill() {
        name.setText(person.getName());
        age.setText(person.getAge());
        surname.setText(person.getSurname());
        gender.setText(person.getGender());
        Picasso.get().load(person.getPhoto()).transform(new CircleImageTransformation(150)).into(imageView);

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonDelete) {
                personList.removePerson(person);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.personlist_fragment_tablet, new PersonListFragment());
                transaction.commit();
                getFragmentManager().popBackStack();
            }
            if (v.getId() == R.id.buttonChange) {
                showFragmentTablet(new ChangedPersonFragment());
            }
        }
    };

    public void showFragmentTablet(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction();
        transition.replace(R.id.person_fragment_tablet, fragment);
        transition.addToBackStack(null);
        transition.commit();
    }


}
