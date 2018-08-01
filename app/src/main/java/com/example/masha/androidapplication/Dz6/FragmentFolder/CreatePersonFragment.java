package com.example.masha.androidapplication.Dz6.FragmentFolder;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.masha.androidapplication.Dz6.Person.CreatePersonActivity;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;

public class CreatePersonFragment extends Fragment {

    private EditText name, surname, age, id;
    private RadioButton male, female;

    private PersonList personList = Singleton.getState().getPeople();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id=view.findViewById(R.id.createId);
        name = view.findViewById(R.id.createName);
        surname = view.findViewById(R.id.createSurname);
        age = view.findViewById(R.id.createAge);
        RadioGroup radioGroup = view.findViewById(R.id.createGender);
        male = view.findViewById(R.id.radioMan);
        female = view.findViewById(R.id.radioWoman);
        Button btnSave = view.findViewById(R.id.buttonSave);
        Button btnBack = view.findViewById(R.id.buttonCancel);
        radioGroup.clearCheck();

        btnSave.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);

        radioGroup.clearCheck();
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonSave) {
                if (name.length() != 0 && surname.length() != 0
                        && (male.isChecked() || female.isChecked())&&age.length()!=0) {
                    create();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.personlist_fragment_tablet, new PersonListFragment());
                    transaction.commit();
                    getFragmentManager().popBackStack();
                } else {
                    Toast.makeText(getActivity(),
                            "Заполните поля", Toast.LENGTH_SHORT).show();
                }
            }
            if (v.getId() == R.id.buttonCancel) {
                getFragmentManager().popBackStack();
            }
        }
    };

    public void create() {
        String gender;
        if(male.isChecked()){
            gender ="male";
        }else {
            gender ="female";
        }
        personList.addPerson(new CreatePersonActivity.AddNewPerson().addId(id).addName(name)
                .addSurname(surname).addGender(gender).addAge(age).addPhoto(null).create());
    }
}
