package com.example.masha.androidapplication.Dz6.Person;

import java.util.Objects;

public class Person {
    private String name;
    private String surname;
    private String age;
    private String gender;
    private String photo;
    private String id;

    public Person() {

    }

    public Person(String id, String name, String surname, String age, String gender, String photo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getAge(), person.getAge()) &&
                Objects.equals(getGender(), person.getGender()) &&
                Objects.equals(getPhoto(), person.getPhoto());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSurname(), getAge(), getGender(), getPhoto(), getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static class PersonBuilder {
        private Person person = new Person();

        public PersonBuilder addName(String name) {
            person.name = name;
            return this;
        }

        public PersonBuilder addSurname(String surname) {
            person.surname = surname;
            return this;
        }

        public PersonBuilder addAge(String age) {
            person.age = age;
            return this;
        }

        public PersonBuilder addGender(String gender) {
            person.gender = gender;
            return this;
        }

        public PersonBuilder addPhoto(String photo) {
            person.photo = photo;
            return this;
        }
        public  PersonBuilder addId(String id){
            person.id = id;
            return this;
        }

        public Person create() {
            return person;
        }

    }
}
