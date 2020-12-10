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
import android.content.Intent;

//building selector imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//end of building selector imports

import java.util.ArrayList;

public class BuildingActivity extends AppCompatActivity {

    DatabaseHelper db;

    ArrayList<ArrayList<String>> data;

    TextView building, altName, dept, info;
    ImageView img;
    //byte[] imageByte;

    //building selector private fields
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //end of building selector private fields

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_selector);

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

        //beginning of old buildingselector
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        //mega important arraylist, max size 2
        ArrayList<String> buildingsToPass = new ArrayList<>();

        //populate exampleList with all database entries as ExampleItem objects
        for(Object element : data) {
            ExampleItem ex = new ExampleItem(img, building, altName, dept, info);
            exampleList.Add(ex);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView = setHasFixedSize(true); only if recycler view won't change in size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        public void changeItem(int position, String text){
            mExampleList.get(position).changeText2(text);
            mAdapter.notifyItemChanged(position);
        }

        public void passBuildings(int position){
            buildingsToPass.Add(mExampleList.get(position).getText1();
            if(buildingsToPass.size==2){
                //go to map page
                Intent launchactivity = new Intent(BuildingActivity.this, MapsActivity.class);
                startActivity(launchactivity);
            }
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListener(new ExampleAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                changeItem(position, "Clicked");
                passBuildings(position);
            }
        });
        //end of old buildingselector


    }
}
