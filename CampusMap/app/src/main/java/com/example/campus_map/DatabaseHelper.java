package com.example.campus_map;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NDSUMap.db";

    // building table
    public static final String TABLE_NAME1 = "Building_table";
    public static final String Building_col1 = "ID";
    public static final String Building_col2 = "Name";
    public static final String Building_col3 = "Image";
    public static final String Building_col4 = "AltName";
    public static final String Building_col5 = "Dept";

    // route table
    public static final String TABLE_NAME2 = "Route_table";
    public static final String Route_col1 = "ID";
    public static final String Route_col2 = "Destination";
    public static final String Route_col3 = "Starting";
    public static final String Route_col4 = "Description";

    // direction table
    public static final String TABLE_NAME3 = "Direction_table";
    public static final String Direction_col1 = "ID";
    public static final String Direction_col2 = "RouteID";
    public static final String Direction_col3 = "Longtitude";
    public static final String Direction_col4 = "Latitude";
    public static final String Direction_col5 = "Sequence";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create building table
        String query = "create table " + TABLE_NAME1 + "(" + Building_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Building_col2 + " TEXT, "
                + Building_col3 + " INTEGER, " + Building_col4 + " TEXT, " + Building_col5 + " TEXT)";
        db.execSQL(query);

        // insert building table data
        int imageRes[] = {R.drawable.aghill, R.drawable.library, R.drawable.minard, R.drawable.qbb, R.drawable.union};

        String aghill = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('A. Glenn Hill Center'," + imageRes[0] + ",'AGHill, STEM Building','Computer Labs')";
        db.execSQL(aghill);

        String library = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Library'," + imageRes[1] + ",'','Center for Writers, Computer Labs, Libraries, Office of the Ombudsperson' )";
        db.execSQL(library);

        String minard = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Minard Hall'," + imageRes[2] + ",'','Anthropology, College of Arts, Humanities and Social Sciences, College of Science and Mathematics, Department " +
                "of Communication, Computer Labs, Emergency Management, English, History, Mathematics, Modern Language, Philogophy, Psychology, Sociology' )";
        db.execSQL(minard);

        String qbb = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Quentin Burdick Building'," + imageRes[3] + ",'QBB','Computer Labs, Computer Science, Scomputer Systems Institute, Information Technology Services," +
                "ITS Help Desk, Technology Learning and Media Center, Upper Great Plains Transportation Institute')";
        db.execSQL(qbb);

        String union = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 +") " +
                "VALUES('Memorial Union'," + imageRes[4] + ",'','Bookstore, Card Center, Design&Sign, One Stop, Student Activities Office, Student Government, Thundar\"s Game Room, Union Dining Center, US Bank')";
        db.execSQL(union);

        // create route table

        // insert route table data

        // create direction table

        //insert direction table data

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        db.execSQL(query);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<ArrayList<String>> getBuildingData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> colData;
        //create cursor to select all value
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME1, null);
        if(cursor.moveToFirst())
        {
            do {
                colData = new ArrayList<>();
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Building_col1))));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col2)));
//                colData.add(new String(cursor.getBlob(cursor.getColumnIndex(Building_col3))));
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Building_col3))));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col4)));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col5)));
                arrayList.add(colData);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
}
