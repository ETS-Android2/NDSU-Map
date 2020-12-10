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
    public static final String TABLE_NAME = "Building_table";
    public static final String Building_col1 = "ID";
    public static final String Building_col2 = "Name";
    public static final String Building_col3 = "Image";
    public static final String Building_col4 = "AltName";
    public static final String Building_col5 = "Dept";
    public static final String Building_col6 = "Info";
    //public static Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 7);
        //context = this.context;
//        int imageRes[] = {R.drawable.aghill, R.drawable.library, R.drawable.minard, R.drawable.qbb, R.drawable.union};
//        this.insertBuildingData("A. Glenn Hill Center", convertImg(context, imageRes[0]), "AGHill, STEM Building", "Computer Labs", "The state-funded building houses " +
//                "classrooms, labs, and study areas, with a focus on science, technology, engineering and mathematics-related courses.");
//        this.insertBuildingData("Library", convertImg(context, imageRes[1]), "", "Center for Writers, Computer Labs, Libraries, Office of the Ombudsperson", "");
//        this.insertBuildingData("Minard Hall", convertImg(context, imageRes[2]), "", "Anthropology, College of Arts, Humanities and Social Sciences, College of Science and Mathematics," +
//                "Department of Communication, Computer Labs, Emergency Management, English, History, Mathematics, Modern Language, Philogophy, Psychology, Sociology","");
//        this.insertBuildingData("Quentin Burdick Building", convertImg(context, imageRes[3]), "QBB", "Computer Labs, Computer Science, Scomputer Systems Institute, Information Technology Services," +
//                "ITS Help Desk, Technology Learning and Media Center, Upper Great Plains Transportation Institute", "");
//        this.insertBuildingData("Memorial Union", convertImg(context, imageRes[4]), "", "Bookstore, Card Center, Design&Sign, One Stop, Student Activities Office, " +
//                "Student Government, Thundar's Game Room, Union Dining Center, US Bank", "");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create building table
        String query = "create table " + TABLE_NAME + "(" + Building_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Building_col2 + " TEXT, "
                + Building_col3 + " INTEGER, " + Building_col4 + " TEXT, " + Building_col5 + " TEXT, " + Building_col6 + " TEXT)";
        db.execSQL(query);

        // insert building table data
        int imageRes[] = {R.drawable.aghill, R.drawable.library, R.drawable.minard, R.drawable.qbb, R.drawable.union};

        String aghill = "INSERT INTO " + TABLE_NAME + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + "," + Building_col6 +") " +
                "VALUES('A. Glenn Hill Center'," + imageRes[0] + ",'AGHill, STEM Building','Computer Labs','The state-funded building houses " +
                "classrooms, labs, and study areas, with a focus on science, technology, engineering and mathematics-related courses.' )";
        db.execSQL(aghill);

        String library = "INSERT INTO " + TABLE_NAME + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + "," + Building_col6 +") " +
                "VALUES('Library'," + imageRes[1] + ",'','Center for Writers, Computer Labs, Libraries, Office of the Ombudsperson','' )";
        db.execSQL(library);

        String minard = "INSERT INTO " + TABLE_NAME + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + "," + Building_col6 +") " +
                "VALUES('Minard Hall'," + imageRes[2] + ",'','Anthropology, College of Arts, Humanities and Social Sciences, College of Science and Mathematics, Department " +
                "of Communication, Computer Labs, Emergency Management, English, History, Mathematics, Modern Language, Philogophy, Psychology, Sociology','' )";
        db.execSQL(minard);

        String qbb = "INSERT INTO " + TABLE_NAME + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + "," + Building_col6 +") " +
                "VALUES('Quentin Burdick Building'," + imageRes[3] + ",'QBB','Computer Labs, Computer Science, Scomputer Systems Institute, Information Technology Services," +
                "ITS Help Desk, Technology Learning and Media Center, Upper Great Plains Transportation Institute','' )";
        db.execSQL(qbb);

        String union = "INSERT INTO " + TABLE_NAME + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + "," + Building_col6 +") " +
                "VALUES('Memorial Union'," + imageRes[4] + ",'','Bookstore, Card Center, Design&Sign, One Stop, Student Activities Office, Student Government, Thundar\"s Game Room, Union Dining Center, US Bank','' )";
        db.execSQL(union);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
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
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
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
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col6)));
                arrayList.add(colData);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    //convert image to appropriate type in order to store in table
//    private byte[] readFile(File file) {
//        ByteArrayOutputStream bos = null;
//        try {
//            //File f = new File(file);
//            FileInputStream fis = new FileInputStream(file);
//            byte[] buffer = new byte[1024];
//            bos = new ByteArrayOutputStream();
//            for (int len; (len = fis.read(buffer)) != -1;) {
//                bos.write(buffer, 0, len);
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException e2) {
//            System.err.println(e2.getMessage());
//        }
//        return bos != null ? bos.toByteArray() : null;
//    }
//    private byte[] convertImg(Context context, int res)
//    {
//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
//        return bos.toByteArray();
//    }
}
