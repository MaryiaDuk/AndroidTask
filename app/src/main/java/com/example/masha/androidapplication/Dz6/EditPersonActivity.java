package com.example.masha.androidapplication.Dz6;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.masha.androidapplication.Dz3.CircleImageTransformation;
import com.example.masha.androidapplication.Dz6.Person.ChangeActivity;
import com.example.masha.androidapplication.Dz6.Person.Person;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.example.masha.androidapplication.R;
import com.squareup.picasso.Picasso;

public class EditPersonActivity extends AppCompatActivity {

    private static Person person;
    private PersonList personList;

    private EditText name, surname, age, gender;
    private ImageView imageView;

    public static void start(Activity activity, int position) {
        person = Singleton.getState().getPeople().getPerson(position);

        Intent intent = new Intent(activity, EditPersonActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        init();
        fill();
    }

    public void init() {
        Button btnDelete = findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(listener);

        Button btnEdit = findViewById(R.id.buttonChange);
        btnEdit.setOnClickListener(listener);

        name = findViewById(R.id.editName);
        surname = findViewById(R.id.editSurname);
        imageView = findViewById(R.id.imagePerson);
        age = findViewById(R.id.editAge);
        gender = findViewById(R.id.editGender);

        personList = Singleton.getState().getPeople();
    }

    public void fill() {
        name.setText(person.getName());
        age.setText(person.getAge());
        surname.setText(person.getSurname());
        gender.setText(person.getGender());
        Picasso.get().load(person.getPhoto()).transform(new CircleImageTransformation(150)).into(imageView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Hometask6Activity.class);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.buttonDelete) {
                personList.removePerson(person);
                startNewActivity();
            }
            if (v.getId() == R.id.buttonChange) {
                ChangeActivity.start(EditPersonActivity.this, person);
                finish();
            }
        }
    };

    private void startNewActivity() {
        Intent intent = new Intent(EditPersonActivity.this, Hometask6Activity.class);
        startActivity(intent);
        finish();
    }
}
