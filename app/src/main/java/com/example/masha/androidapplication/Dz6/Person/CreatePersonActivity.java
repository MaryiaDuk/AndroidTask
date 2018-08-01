package com.example.masha.androidapplication.Dz6.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.masha.androidapplication.Dz6.Hometask6Activity;
import com.example.masha.androidapplication.Dz6.Singleton;
import com.example.masha.androidapplication.R;

public class CreatePersonActivity extends AppCompatActivity {
    private EditText name, surname, age, id;
private String gender;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private Button btnSave, btnBack;

    private PersonList personList = Singleton.getState().getPeople();


    public static void start(Activity activity) {
        Intent intent = new Intent(activity,  CreatePersonActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_person);
        init();
    }
    public void init() {
        id=findViewById(R.id.createId);
        name = findViewById(R.id.createName);
        surname = findViewById(R.id.createSurname);
        age = findViewById(R.id.createAge);
        radioGroup = findViewById(R.id.createGender);
        male = findViewById(R.id.radioMan);
        female = findViewById(R.id.radioWoman);
        btnSave = findViewById(R.id.buttonSave);
        btnBack = findViewById(R.id.buttonCancel);
        radioGroup.clearCheck();

        btnSave.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);

    }

    public void create() {
        if(male.isChecked()){
            gender="male";
        }else {
            gender="female";
        }
      personList.addPerson(new CreatePersonActivity.AddNewPerson().addId(id).addName(name)
                .addSurname(surname).addGender(gender).addAge(age).addPhoto(null).create());
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonSave) {
                if (name.length() != 0 && surname.length() != 0
                        && (male.isChecked() || female.isChecked())&&age.length()!=0) {
                    create();
                    Intent intent = new Intent(CreatePersonActivity.this,Hometask6Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreatePersonActivity.this,
                            "Заполните поля", Toast.LENGTH_SHORT).show();
                }
            }
            if (v.getId() == R.id.buttonCancel) {
                Intent intent = new Intent(CreatePersonActivity.this,Hometask6Activity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public static class AddNewPerson {
        private Person p = new Person();

        public AddNewPerson addName(EditText name) {

            p.setName(name.getText().toString());
            return this;
        }

        public AddNewPerson addSurname(EditText surname) {
            p.setSurname(surname.getText().toString());
            return this;
        }

        public AddNewPerson addGender(String gender) {
            p.setGender(gender);
            return this;
        }

        public AddNewPerson addPhoto(EditText photo) {
            if (photo == null) {
                p.setPhoto("http://bipbap.ru/wp-content/uploads/2017/04/rozi-foto-07.jpg");
            } else
                p.setPhoto(photo.getText().toString());
            return this;
        }

        public AddNewPerson addId(EditText id) {
            p.setId(String.valueOf(id));
            return this;
        }

        public AddNewPerson addAge(EditText age) {
            p.setAge (String.valueOf(age));
            return this;
        }

        public Person create() {
            return p;
        }
    }

}
