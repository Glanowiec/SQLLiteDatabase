package com.example.sqllitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    private static final String DB_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone_number";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE_QUERY = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE_QUERY);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        //Create table again
        onCreate(db);
    }

    //Conde to add the new contact
    public void addContact(Contact contact){
       SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //Contact Name
        values.put(KEY_NAME, contact.getName());
        //Contact Phone
        values.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //Insert row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); //Closing database connection
    }

    //Get contact by id
    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                KEY_ID, KEY_NAME, KEY_PHONE_NUMBER}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return contact;
    }


    //Get all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        //Query
        String selectAllContactsQuery = "SELECT * FROM "
                + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAllContactsQuery, null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    //Update contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contact.getName());
        contentValues.put(KEY_PHONE_NUMBER, contact.getPhoneNumber());

        return db.update(TABLE_CONTACTS, contentValues, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    //Get amount of contacts
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}

