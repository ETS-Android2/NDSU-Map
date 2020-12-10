package com.example.campus_map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

//building selector imports
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//end of building selector imports

import java.util.ArrayList;

public class BuildingActivity extends AppCompatActivity {

    private DatabaseHelper db;

    private ArrayList<ArrayList<String>> data;

    private TextView building, altName, dept, info;
    private ImageView img;

    private ArrayList<BuildingItem> exampleList = new ArrayList<>();  //beginning of old buildingselector
    private ArrayList<String> buildingsToPass = new ArrayList<>();   //mega important arraylist, max size 2

    //building selector private fields
    private RecyclerView mRecyclerView;
    private BuildingAdapter mAdapter;
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
//        ArrayList firstItem = data.get(0);
//        building.setText(firstItem.get(1).toString());
//        img.setImageResource(Integer.parseInt(firstItem.get(2).toString()));
//        altName.setText(firstItem.get(3).toString());
//        dept.setText(firstItem.get(4).toString());
//        info.setText(firstItem.get(5).toString());

        //populate exampleList with all database entries as ExampleItem objects
        for(ArrayList element : data) {
//            ExampleItem ex = new ExampleItem(element.img, element.building, element.altName, element.dept, element.info);
            String building = element.get(1).toString();
            String altName = element.get(3).toString();
            String dept = element.get(4).toString();
            String info = element.get(5).toString();
            BuildingItem ex = new BuildingItem(Integer.parseInt(element.get(2).toString()), building, altName, dept, info);
            exampleList.add(ex);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView = setHasFixedSize(true); only if recycler view won't change in size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BuildingAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BuildingAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                changeItem(position, "Clicked");
                passBuildings(position);
            }
        });
        //end of old buildingselector


    }

    public void changeItem(int position, String text){
        exampleList.get(position).changeText2(text);
        mAdapter.notifyItemChanged(position);
    }

    public void passBuildings(int position){
        buildingsToPass.add(exampleList.get(position).getBuilding());
        if(buildingsToPass.size()==2){
            //go to map page
            Intent launchactivity = new Intent(BuildingActivity.this, MapsActivity.class);
            startActivity(launchactivity);
        }
    }
}
