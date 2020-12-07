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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
        //context = this.context;
        int imageRes[] = {R.drawable.aghill, R.drawable.library, R.drawable.minard, R.drawable.qbb, R.drawable.union};
        this.insertData("A. Glenn Hill Center", convertImg(context, imageRes[0]), "AGHill, STEM Building", "Computer Labs", "The state-funded building houses " +
                "classrooms, labs, and study areas, with a focus on science, technology, engineering and mathematics-related courses.");
        this.insertData("Library", convertImg(context, imageRes[1]), "", "Center for Writers, Computer Labs, Libraries, Office of the Ombudsperson", "");
        this.insertData("Minard Hall", convertImg(context, imageRes[2]), "", "Anthropology, College of Arts, Humanities and Social Sciences, College of Science and Mathematics," +
                "Department of Communication, Computer Labs, Emergency Management, English, History, Mathematics, Modern Language, Philogophy, Psychology, Sociology","");
        this.insertData("Quentin Burdick Building", convertImg(context, imageRes[3]), "QBB", "Computer Labs, Computer Science, Scomputer Systems Institute, Information Technology Services," +
                "ITS Help Desk, Technology Learning and Media Center, Upper Great Plains Transportation Institute", "");
        this.insertData("Memorial Union", convertImg(context, imageRes[4]), "", "Bookstore, Card Center, Design&Sign, One Stop, Student Activities Office, " +
                "Student Government, Thundar's Game Room, Union Dining Center, US Bank", "");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" + Building_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Building_col2 + " TEXT, "
                + Building_col3 + " BLOB, " + Building_col4 + " TEXT, " + Building_col5 + " TEXT, " + Building_col6 + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertData(String name, byte[] img, String altName, String dept, String info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Building_col2, name);
        contentValues.put(Building_col3, img);
        contentValues.put(Building_col4, altName);
        contentValues.put(Building_col5, dept);
        contentValues.put(Building_col6, info);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<ArrayList<String>> getBuildingData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> colData = new ArrayList<>();
        //create cursor to select all value
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        if(cursor.moveToFirst())
        {
            do {
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Building_col1))));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col2)));
                colData.add(new String(cursor.getBlob(cursor.getColumnIndex(Building_col3))));
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
//    private byte[] readFile(String file) {
//        ByteArrayOutputStream bos = null;
//        try {
//            File f = new File(file);
//            FileInputStream fis = new FileInputStream(f);
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
    private byte[] convertImg(Context context, int res)
    {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
        return bos.toByteArray();
    }
}
