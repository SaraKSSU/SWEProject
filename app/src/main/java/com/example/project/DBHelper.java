package com.example.project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "GlamBook.db";
    private static final int DATABASE_VERSION = 1;

    // Tables names
    private static final String Client = "client";
    private static final String Appointment  = "appointment";
    private static final String Staff  = "staff";
    private static final String Service  = "service";

    // Client table column names
    private static final String ClientID = "userID";
    private static final String ClientName = "clientName";
    private static final String Email = "email";
    private static final String PhoneNumber = "phoneNumber";
    private static final String Birthday = "birthday";
    private static final String Password = "password";

    // Appointment table column names
    private static final String AppointmentID = "appointmentID";
    private static final String Date = "date";
    private static final String Time = "time";
    // Staff table column names
    private static final String StaffID = "staffID";
    private static final String StaffName = "staffName";
    private static final String Rating = "rating";
    private static final String Specialty = "specialty";

    // Service table column names
    private static final String ServiceID = "serviceID";
    private static final String ServiceName = "serviceName";
    private static final String Description = "description";
    private static final String Price = "price";

//constructor
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // create the tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create client table
        String createClientTableQuery = "CREATE TABLE " + Client + "("
                + ClientID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientName + " TEXT,"
                + Email + " TEXT,"
                + PhoneNumber + " TEXT,"
                + Birthday + " TEXT,"
                + Password + " TEXT" + ")";
        db.execSQL(createClientTableQuery);

        // Create appointment table
        String createAppointmentTableQuery = "CREATE TABLE " + Appointment + "("
                + AppointmentID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ClientID + " INTEGER,"
                + Date + " TEXT,"
                + Time + " TEXT,"
                + StaffID + " INTEGER,"
                + ServiceID + " INTEGER,"
                + StaffName + " TEXT,"
                + "FOREIGN KEY(" + ClientID + ") REFERENCES " + Client + "(" + ClientID + "),"
                + "FOREIGN KEY(" + StaffID + ") REFERENCES " + Staff + "(" + StaffID + "),"
                + "FOREIGN KEY(" + ServiceID + ") REFERENCES " + Service + "(" + ServiceID + ")" + ")";
        db.execSQL(createAppointmentTableQuery);
        // Create staff table
        String createStaffTableQuery = "CREATE TABLE " + Staff + "("
                + StaffID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + StaffName + " TEXT,"
                + Rating + " REAL,"
                + Specialty + " TEXT" + ")";
        db.execSQL(createStaffTableQuery);

        // Create service table
        String createServiceTableQuery = "CREATE TABLE " + Service + "("
                + ServiceID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ServiceName + " TEXT,"
                + Description + " TEXT,"
                + Price + " REAL" + ")";
        db.execSQL(createServiceTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Client);
        db.execSQL("DROP TABLE IF EXISTS " + Appointment);
        db.execSQL("DROP TABLE IF EXISTS " + Staff);
        db.execSQL("DROP TABLE IF EXISTS " + Service);
        // Create tables again
        onCreate(db);
    }
}

