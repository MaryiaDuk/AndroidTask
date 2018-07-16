package com.example.masha.androidapplication.Dz6.Parsing;

import com.example.masha.androidapplication.Dz6.Person.Person;
import com.example.masha.androidapplication.Dz6.Person.PersonList;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class PersonListDeserializer implements JsonDeserializer<PersonList> {

    @Override
    public PersonList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonArray array = object.getAsJsonArray("people");
        return new PersonList.PersonListBuilder().addPeople(makeList(array)).create();
    }
    private List<Person> makeList(JsonArray array) {
        JsonDeserializationContext jsonDeserializationContext = null;
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            people.add(new PersonDeserializer().deserialize(element, Person.class, jsonDeserializationContext));
        }

        return people;
    }
}
