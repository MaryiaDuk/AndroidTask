package com.example.masha.androidapplication.Dz6.Parsing;

import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParsingJSON {
    public PersonList makeList() {
        URL url = null;
        try {
            url = new URL("https://raw.githubusercontent.com/MaryiaDuk/OnlineBase/master/People.json");
            HttpURLConnection connection = null;

            connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            Gson gson = new GsonBuilder().setPrettyPrinting()
                    .registerTypeAdapter(PersonList.class, new PersonListDeserializer()).create();

            PersonList personList = null;

            personList = gson.fromJson(new BufferedReader
                    (new InputStreamReader(connection.getInputStream())), PersonList.class);
            connection.disconnect();
            return personList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
