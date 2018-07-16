package com.example.masha.androidapplication.Dz6.Parsing;

import com.example.masha.androidapplication.Dz6.Person.Person;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class PersonDeserializer implements JsonDeserializer<Person> {
    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        String id = object.get("id").getAsString();
        String name = object.get("name").getAsString();
        String surname = object.get("surname").getAsString();
        String age = object.get("age").getAsString();
        String gender = object.get("gender").getAsString();
        return new Person.PersonBuilder().addName(name).addId(id).addSurname(surname).addAge(age).addGender(gender).addPhoto("https://i.pinimg.com/564x/cb/8b/fd/cb8bfd4a7acda41ad657fad5592a7165.jpg").create();
    }
}
