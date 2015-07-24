package com.greyhound.sqltest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class mainActivity extends Activity  {

    private DataBaseHelper mDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mDatabaseHelper = new DataBaseHelper(this, "mydatabase.db", null, 1);

        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        // Set values for columns
        newValues.put(DataBaseHelper.CAT_NAME_COLUMN, "Рыжик");
        newValues.put(DataBaseHelper.PHONE_COLUMN, "495553443");
        newValues.put(DataBaseHelper.AGE_COLUMN, "5");
        // Insert into table
        mSQLiteDatabase.insert("cats", null, newValues);
    }

    public void onClick(View v) {
        Cursor cursor = mSQLiteDatabase.query("cats", new String[] {DataBaseHelper.CAT_NAME_COLUMN,
                        DataBaseHelper.PHONE_COLUMN, DataBaseHelper.AGE_COLUMN},
                null, null,
                null, null, null);

        cursor.moveToFirst();

        String catName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.CAT_NAME_COLUMN));
        String phoneNumber = cursor.getString(cursor.getColumnIndex(DataBaseHelper.PHONE_COLUMN));
        String age = cursor.getString(cursor.getColumnIndex(DataBaseHelper.AGE_COLUMN));

        TextView infoTextView = (TextView)findViewById(R.id.textView);
        infoTextView.setText("Кот " + catName + " имеет телефон "
                + phoneNumber + " и ему " + age + " лет");

        cursor.close();

    }

}
