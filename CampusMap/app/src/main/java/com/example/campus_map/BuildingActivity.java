package com.example.campus_map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BuildingActivity extends AppCompatActivity {

    DatabaseHelper db;

    ArrayList<ArrayList<String>> data;

    TextView building, altName, dept, info;
    ImageView img;
    //byte[] imageByte;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building);

        //assign variable

        building = findViewById(R.id.buildingName);
        altName = findViewById(R.id.altName);
        dept = findViewById(R.id.department);
        info = findViewById(R.id.info);
        img = findViewById(R.id.buildingImg);

        //initialize database
        db = new DatabaseHelper(this);

        data = db.getBuildingData();
        // test first Building item in data (works properly)
        ArrayList firstItem = data.get(0);
        building.setText(firstItem.get(1).toString());
        img.setImageResource(Integer.parseInt(firstItem.get(2).toString()));
        altName.setText(firstItem.get(3).toString());
        dept.setText(firstItem.get(4).toString());
        info.setText(firstItem.get(5).toString());




    }
}