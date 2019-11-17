package com.example.sqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button saveContactBtn;
    private Button showContactListBtn;
    private Button showAuthorsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveContactBtn = findViewById(R.id.saveContactButton);

        showContactListBtn = findViewById(R.id.showContactListButton);

        //On click - Open new webview with authors html info
        showAuthorsBtn = findViewById(R.id.showAuthorsBtn);
        showAuthorsBtn.setOnClickListener(view -> showAuthorInfo());

        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Pawel", "+488885568486"));
        db.addContact(new Contact("Kacper", "+48609876453"));
        db.addContact(new Contact("Aga", "+48978345234"));
        db.addContact(new Contact("Ola", "+48505222345"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
                    cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    //Opening activity with saved contacts
    

    //Opening activity with webview
    public void showAuthorInfo() {
        Intent activity2Intent = new Intent(this, Main2Activity.class);
        startActivity(activity2Intent);
    }

}
