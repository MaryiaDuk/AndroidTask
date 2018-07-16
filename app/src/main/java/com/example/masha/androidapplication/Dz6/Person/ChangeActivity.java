package com.example.masha.androidapplication.Dz6.Person;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import com.example.masha.androidapplication.Dz6.Hometask6Activity;
import com.example.masha.androidapplication.R;


public class ChangeActivity extends Activity {
    private static Person person;

    private EditText name, surname, age, id, gender;

    public static void start(Activity activity, Person p) {
        person = p;
        Intent intent = new Intent(activity, ChangeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        init();
        hint();
    }

    public void init() {
        Button btnBack = findViewById(R.id.changeCancelBtn);
        Button btnSave = findViewById(R.id.changeSaveBtn);

        btnBack.setOnClickListener(listener);
        btnSave.setOnClickListener(listener);
        id = findViewById(R.id.changeId);
        name = findViewById(R.id.changeName);
        surname = findViewById(R.id.changeSurname);

        age =findViewById(R.id.changeAge);
        gender=findViewById(R.id.changeGender);
    }

    public void hint() {
        name.setHint(person.getName());
        surname.setHint(person.getSurname());
        gender.setHint(person.getGender());
        age.setHint(person.getAge());
        id.setHint(person.getId());
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.changeCancelBtn) {
                startNewActivity();
            }
            if (v.getId() == R.id.changeSaveBtn) {
                update();
                startNewActivity();
            }
        }
    };

    public void update() {
        if (name.getText().length() != 0)
            person.setName(name.getText().toString());
        if (surname.getText().length() != 0)
            person.setSurname(surname.getText().toString());
        if(gender.getText().length()!=0)
            person.setGender(gender.getText().toString());
        if(id.getText().length()!=0)
            person.setId(String.valueOf(id));
        finish();
    }

    private void startNewActivity() {
        Intent intent = new Intent(ChangeActivity.this, Hometask6Activity.class);
        startActivity(intent);
        finish();
    }
}