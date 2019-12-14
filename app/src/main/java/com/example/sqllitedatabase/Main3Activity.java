package com.example.sqllitedatabase;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Main3Activity extends AppCompatActivity {

    ArrayList<Contact> contactList;
    //todo what is arrayadapter
    ArrayAdapter contactListAdapter;
    ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        DatabaseHandler db = new DatabaseHandler(this);
        contactListView = findViewById(R.id.contactListView);

        contactList = (ArrayList<Contact>) db.getAllContacts();
        contactListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList);

        contactListView.setAdapter(contactListAdapter);
    }
}
