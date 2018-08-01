package com.example.masha.androidapplication.Dz6.Person;

import android.support.v7.widget.RecyclerView;

import com.example.masha.androidapplication.Dz6.Hometask6Activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonList {
    private List<Person> people = new ArrayList<>();

    public PersonList() {
    }

    public PersonList(List<Person> people) {
        this.people = people;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }
    public Person getPerson(int key){
        return people.get(key);
    }

    public void removePerson(Object key) {
        people.remove(key);
    }

    public static class PersonListBuilder {
        private PersonList personList = new PersonList();

        public PersonListBuilder addPeople(List<Person> jsonPerson) {
            personList.setPeople(jsonPerson);
            return this;
        }

        public PersonList create() {
            return personList;
        }
    }

}
