package com.example.masha.androidapplication.Dz6;

import com.example.masha.androidapplication.Dz6.Parsing.ParsingJSON;
import com.example.masha.androidapplication.Dz6.Person.PersonList;

public class Singleton {
    private static Singleton instance;
    private PersonList list;

    private Singleton() {
        start();
        list = new PersonList();
    }

    public PersonList getPeople() {
        return list;
    }

    private void start() {
        new Thread(() -> list = new ParsingJSON().makeList()).start();
    }

    public static Singleton getState() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}