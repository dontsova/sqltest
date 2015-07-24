package com.greyhound.sqltest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    //table name
    private static final String DATABASE_TABLE = "cats";

    //columns names
    public static final String CAT_NAME_COLUMN = "cat_name";
    public static final String PHONE_COLUMN = "phone";
    public static final String AGE_COLUMN = "age";

    //sql-script
    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + CAT_NAME_COLUMN
            + " text not null, " + PHONE_COLUMN + " integer, " + AGE_COLUMN
            + " integer);";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Write to journal
        Log.w("SQLite", "Upgrade old version " + oldVersion + "to new " + newVersion);

        // Delete old table
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);

        // Create new table
        onCreate(db);
    }

    DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



}
