package com.example.karthikmam.dbactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created by Karthik M A M on 20-04-2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "contacts";
    private static final String TABLE_NAME = "info";
    private static final String CONTACT_NAME = "name";
    private static final String CONTACT_PHONE = "phone";

    private static final String EQUAL_CLAUSE = "=?";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME + "(" +
                        CONTACT_NAME + " text, " +
                        CONTACT_PHONE + " text" +
                        ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "drop table if exists " + TABLE_NAME + ";"
        );
    }

    public void insertData(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(CONTACT_NAME, listItem.contactName);
        insertValues.put(CONTACT_PHONE, listItem.contactNumber);

        db.insert(TABLE_NAME, null, insertValues);
    }

    public ListItem[] getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor resultCursor = db.rawQuery(
                "select * from " + TABLE_NAME + ";",
                null
        );

        resultCursor.moveToFirst();
        ListItem[] listItems = new ListItem[resultCursor.getCount()];
        for(int i=0; i<resultCursor.getCount(); i++) {
            listItems[i] = new ListItem(
                    resultCursor.getString(0),
                    resultCursor.getString(1)
            );
            resultCursor.moveToNext();
        }
        resultCursor.close();

        return listItems;
    }

    public void updateData(ListItem listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues updateValues = new ContentValues();
        updateValues.put(CONTACT_NAME, listItem.contactName);

        db.update(
                TABLE_NAME,
                updateValues,
                CONTACT_PHONE + EQUAL_CLAUSE,
                new String[] { listItem.contactNumber }
        );
    }

    public void deleteData(String contactPhone) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(
                TABLE_NAME,
                CONTACT_PHONE + EQUAL_CLAUSE,
                new String[] { contactPhone }
        );
    }
}
