package com.example.deepeshmehta.contactsrealm.models;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by deepeshmehta on 2017-12-13.
 */

public class Contact extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private int age;
    private String major;
    private int gender;

    public Contact() {

        this.id = getNextKey();
        this.name = "";
        this.age = 20;
        this.major = "";
        this.gender =  1;
    }

    public Contact(String name, int age, String major, int gender) {
        this.id = getNextKey();
        this.name = name;
        this.age = age;
        this.major = major;
        this.gender = gender;
    }

    public Contact(Contact con) {
        this.id = getNextKey();
        this.name = con.name;
        this.age = con.age;
        this.major = con.major;
        this.gender = con.gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public static int getNextKey() {
        try {
            Number number = Realm.getDefaultInstance().where(Contact.class).max("id");
            if (number != null) {
                return number.intValue() + 1;
            } else {
                return 0;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
