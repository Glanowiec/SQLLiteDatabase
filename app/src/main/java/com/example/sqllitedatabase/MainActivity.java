package com.example.sqllitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText phoneNumberField;

    private Button saveContactBtn;
    private Button showContactListBtn;
    private Button showAuthorsBtn;

    private final String NOT_SAVED_MESSAGE = "Kontakt niezapisany. Uzupełnij wszystkie pola.";
    private final String SAVED_MESSAGE = "Kontakt zapisany.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveContactBtn = findViewById(R.id.saveContactButton);
        saveContactBtn.setOnClickListener(view -> saveContact());

        showContactListBtn = findViewById(R.id.showContactListButton);
        showContactListBtn.setOnClickListener(view -> showSavedContacts());

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

    //Save contact to DB
    private void saveContact() {
        nameField = findViewById(R.id.nameField);
        phoneNumberField = findViewById(R.id.phoneNumberField);
        DatabaseHandler db = new DatabaseHandler(this);
        if(!nameField.getText().toString().isEmpty() && !phoneNumberField.getText().toString().isEmpty()) {
            db.addContact(new Contact(nameField.getText().toString(), phoneNumberField.getText().toString()));
            showToast(SAVED_MESSAGE);
        } else {
            showToast(NOT_SAVED_MESSAGE);
        }
    }

    //Opening activity with saved contacts
    private void showSavedContacts() {
    }

    //Opening activity with webview
    public void showAuthorInfo() {
        Intent activity2Intent = new Intent(this, Main2Activity.class);
        startActivity(activity2Intent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
