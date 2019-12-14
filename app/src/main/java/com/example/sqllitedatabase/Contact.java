package com.example.sqllitedatabase;

import androidx.annotation.NonNull;

public class Contact {

    public static final String ID = " ID: ";
    public static final String NAME = " NAME: ";
    public static final String PHONE_NUMBER = " PHONE NUMBER: ";

    private int id;
    private String name;
    private String phoneNumber;


    public Contact(){

    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return ID + id + NAME + name + PHONE_NUMBER + phoneNumber;
    }
}
