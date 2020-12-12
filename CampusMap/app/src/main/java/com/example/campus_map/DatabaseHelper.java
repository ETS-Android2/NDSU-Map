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

/*******************************************************************************
 * Reference 1
 * Title: Android SQLite Database Tutorial (Select, Insert, Update, Delete)
 * Author: Singh, M
 * URL: http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
 *
 * Reference 2
 * SQLiteOpenHelper : Android Developers
 * URL: https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 ********************************************************************************/
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
    public static final String Route_col5 = "EstTime";

    // direction table
    public static final String TABLE_NAME3 = "Direction_table";
    public static final String Direction_col1 = "ID";
    public static final String Direction_col2 = "RouteID";
    public static final String Direction_col3 = "Longitude";
    public static final String Direction_col4 = "Latitude";
    public static final String Direction_col5 = "Sequence";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 13);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create building table
        String query = "create table if not exists " + TABLE_NAME1 + "(" + Building_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Building_col2 + " TEXT, "
                + Building_col3 + " INTEGER, " + Building_col4 + " TEXT, " + Building_col5 + " TEXT)";
        db.execSQL(query);

        // insert building table data
        int imageRes[] = {R.drawable.aghill, R.drawable.ceres, R.drawable.dolve, R.drawable.gatecity, R.drawable.library, R.drawable.union, R.drawable.minard, R.drawable.qbb, R.drawable.southengr,
                R.drawable.sudro, R.drawable.wellness};

        String aghill = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('A. Glenn Hill Center'," + imageRes[0] + ",'AGHill, STEM Building','Computer Labs')";
        db.execSQL(aghill);

        String ceres = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Ceres Hall'," + imageRes[1] + ",'','Office of Admission, Career and Advising Center, Counseling Center, Office of Registration and Records')";
        db.execSQL(ceres);

        String dolve = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Dolve Hall'," + imageRes[2] + ",'','Department of Mechanical Engineering')";
        db.execSQL(dolve);

        String gateCity = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Gate City Bank Auditorium'," + imageRes[3] + ",'','Lecture Room')";
        db.execSQL(gateCity);

        String library = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Library'," + imageRes[4] + ",'','Center for Writers, Computer Labs, Libraries, Office of the Ombudsperson' )";
        db.execSQL(library);

        String union = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 +") " +
                "VALUES('Memorial Union'," + imageRes[5] + ",'','Bookstore, Card Center, Design&Sign, One Stop, Student Activities Office, Student Government, Thundar\"s Game Room, Union Dining Center, US Bank')";
        db.execSQL(union);

        String minard = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Minard Hall'," + imageRes[6] + ",'','Anthropology, College of Arts, Humanities and Social Sciences, College of Science and Mathematics, Department " +
                "of Communication, Computer Labs, Emergency Management, English, History, Mathematics, Modern Language, Philogophy, Psychology, Sociology' )";
        db.execSQL(minard);

        String qbb = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Quentin Burdick Building'," + imageRes[7] + ",'QBB','Computer Labs, Computer Science, Scomputer Systems Institute, Information Technology Services," +
                "ITS Help Desk, Technology Learning and Media Center, Upper Great Plains Transportation Institute')";
        db.execSQL(qbb);

        String souteENGR = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('South Engineering'," + imageRes[8] + ",'','Department of Physics')";
        db.execSQL(souteENGR);

        String sudro = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Sudro Hall'," + imageRes[9] + ",'','Allied Sciences, Medical Laboratory Science, Radiologic Sciences, School of Pharmacy')";
        db.execSQL(sudro);

        String wellness = "INSERT INTO " + TABLE_NAME1 + "(" + Building_col2 + "," + Building_col3 + "," + Building_col4 + "," + Building_col5 + ") " +
                "VALUES('Wallman Wellness Center'," + imageRes[10] + ",'','Child Care Service, Disability Services (students), Fitness Programs, Intramural Sports, Student Health Service, Wallman Wellness Center')";
        db.execSQL(wellness);


        // create route table
        String query2 = "create table if not exists " + TABLE_NAME2 + "(" + Route_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Route_col2 + " TEXT, "
                + Route_col3 + " TEXT, " + Route_col4 + " TEXT, " + Route_col5 + " TEXT)";
        db.execSQL(query2);

        // insert route table data
        String MtoQ = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('Quentin Burdick Building', 'Minard Hall'," +
                "'1. Walk out from the north door of Minard Hall; Head north;2. Turn left at Morrill Hall;3. Enter the east door of Morrill Hall;4. Go to the second floor of Morrill Hall;" +
                "5. Head north;6. Walk through the tunnel from Morrill Hall to Hultz Hall;7. Keep heading north;8. Walk through the tunnel from Hultz Hall to Quentin Burdick Building;9. You are at " +
                "the second floor of the Quentin Burdick Building', '4 minutes')";
        db.execSQL(MtoQ);

        String QtoU = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('Memorial Union', 'Quentin Burdick Building'," +
                "'1. Go to the second floor of the Quentin Burdick Building;2. Head south;3. Walk through the tunnel from Quentin Burdick Building to Hultz Hall;4. Keep heading south;" +
                "5. Turn left at the entry of another tunnel;6. Head east;7. Enter Dunbar Hall;8. Walk out from the east door of Dunbar Hall;9. Head east;10. Enter the west door of Memorial Union', '5 minutes')";
        db.execSQL(QtoU);

        String UtoA = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('A. Glenn Hill Center', 'Memorial Union'," +
                "'1. Walk out from the east door of Memorial Union;2. Head east;3. Enter the west door of A. Glenn Hill Center', '1 minute')";
        db.execSQL(UtoA);

        String QtoW = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('Wallman Wellness Center', 'Quentin Burdick Building'," +
                "'1. Walk out from the north door of Quentin Burdick Building;2. Turn left and head west of Centennial Blvd;3. Enter the south door of Wallaman Wellness Center', '6 minutes')";
        db.execSQL(QtoW);

        String StoD = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('Quentin Burdick Building', 'Sudro Hall'," +
                "'1. Walk out from the south door of Sudro Hall;2. Turn right;3. Turn left toward Albrecht Blvd;4. Head south;5. Enter the east door of Quentin Burdick Building', '4 minutes')";
        db.execSQL(StoD);

        String LtoS = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('South Engineering', 'Library'," +
                "'1. From Library main entry, head northeast;2. Enter the south door of South Engineering', '1 minute')";
        db.execSQL(LtoS);

        String UtoD = "INSERT INTO " + TABLE_NAME2 + "(" + Route_col2 + "," + Route_col3 + "," + Route_col4 + "," + Route_col5 + ") VALUES('Dolve Hall', 'Memorial Union'," +
                "'1. Head north to enter Family Life Center;2. Go downstairs;3. Walk out from the north door of Family Life Center; 4. Head north and enter the west door of Dolve Hall', '4 minutes')";
        db.execSQL(UtoD);

        // create direction table
        String query3 = "create table if not exists " + TABLE_NAME3 + "(" + Direction_col1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Direction_col2 + " INTEGER, "
                + Direction_col3 + " TEXT, " + Direction_col4 + " TEXT, " + Direction_col5 + " INTEGER, FOREIGN KEY("+ Direction_col2 + ") REFERENCES "
                + TABLE_NAME2 + " (" + Route_col1+"))";
        db.execSQL(query3);

        //insert direction table data
        String MtoQDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(1, '46.89154275176031', '-96.8027488328993', 1), (1, '46.89244644583737', '-96.80279767300239', 2), " +
                "(1, '46.892491124314866', '-96.80328225161705', 3), (1, '46.89359934932082', '-96.8034189499907', 4)";
        db.execSQL(MtoQDirection);

        String QtoUDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(2, '46.89359677884727', '-96.80343565284473', 1), (2, '46.89317810495277', '-96.80349753884032', 2), " +
                "(2, '46.89324576964199', '-96.80215461273599', 3), (2, '46.8931157878145', '-96.80138010836264', 4)";
        db.execSQL(QtoUDirection);

        String UtoADirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(3, '46.89317254101427', '-96.80027050427611', 1), (3, '46.89317701504675', '-96.7997096039748', 2)";
        db.execSQL(UtoADirection);

        String QtoWDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(4, '46.89373287761349', '-96.8034234682245', 1), (4, '46.89425342471551', '-96.80355218481172', 2), (4, '46.89468602271379', '-96.80782231764424', 3)";
        db.execSQL(QtoWDirection);

        String StoQDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(5, '46.89373287761349', '-96.8034234682245', 1), (5, '46.893791559096535', '-96.80270464093584', 2), " +
                "(5, '46.8956537833733', '-96.80271530181581', 3), (5, '46.89588838098085', '-96.80194278353217', 4)";
        db.execSQL(StoQDirection);

        String LtoSDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(6, '46.890918000701085', '-96.80125421396424', 1), (6, '46.8918499098324', '-96.80143825649495', 2)";
        db.execSQL(LtoSDirection);

        String UtoDDirection = "INSERT INTO " + TABLE_NAME3 + "(" + Direction_col2 + "," + Direction_col3 + "," + Direction_col4 + "," + Direction_col5 + ") " +
                "VALUES(7, '46.8931157878145', '-96.80138010836264', 1), (7, '46.89411300455109', '-96.80111450054073', 2)," +
                "(7, '46.89409100593973', '-96.80079263356069', 3), (7, '46.895118868760015', '-96.80068881263924', 4)";
        db.execSQL(UtoDDirection);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        String query2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        String query3 = "DROP TABLE IF EXISTS " + TABLE_NAME3;
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
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
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Building_col3))));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col4)));
                colData.add(cursor.getString(cursor.getColumnIndex(Building_col5)));
                arrayList.add(colData);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<ArrayList<String>> getAllRouteData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> colData;

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME2, null);
        if(cursor.moveToFirst())
        {
            do {
                colData = new ArrayList<>();
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Route_col1))));
                colData.add(cursor.getString(cursor.getColumnIndex(Route_col2)));
                colData.add(cursor.getString(cursor.getColumnIndex(Route_col3)));
                colData.add(cursor.getString(cursor.getColumnIndex(Route_col4)));
                colData.add(cursor.getString(cursor.getColumnIndex(Route_col5)));
                arrayList.add(colData);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<String> getRouteData(String destination, String starting)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> colData = new ArrayList();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME2 + " where " + Route_col2 + " = ? AND " + Route_col3 + " = ?", new String[]{destination, starting});

        if(cursor.moveToFirst())
        {
            colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Route_col1))));
            colData.add(cursor.getString(cursor.getColumnIndex(Route_col2)));
            colData.add(cursor.getString(cursor.getColumnIndex(Route_col3)));
            colData.add(cursor.getString(cursor.getColumnIndex(Route_col4)));
            colData.add(cursor.getString(cursor.getColumnIndex(Route_col5)));
        }
        cursor.close();
        return colData;
    }

    public ArrayList<ArrayList<String>> getRouteDirection(int routeId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        ArrayList<String> colData;

        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME3 + " where " + Direction_col2 + " = ?", new String[]{String.valueOf(routeId)});
        if(cursor.moveToFirst())
        {
            do {
                colData = new ArrayList<>();
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Direction_col1))));
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Direction_col2))));
                colData.add(cursor.getString(cursor.getColumnIndex(Direction_col3)));
                colData.add(cursor.getString(cursor.getColumnIndex(Direction_col4)));
                colData.add(String.valueOf(cursor.getInt(cursor.getColumnIndex(Direction_col5))));
                arrayList.add(colData);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }
}
