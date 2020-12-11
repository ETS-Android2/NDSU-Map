package com.example.campus_map;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;

//building selector imports
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//end of building selector imports

import java.util.ArrayList;

public class BuildingActivity extends AppCompatActivity {

    private static final String TAG = "StartingActivity";

    private DatabaseHelper db;

    private ArrayList<ArrayList<String>> buildings;

    private TextView building, altName, dept, info;
    private ImageView img;
    private EditText buildingSearch;

    private ArrayList<BuildingItem> buildingList = new ArrayList<>();  //beginning of old buildingselector
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

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.selectionToolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //initialize database
        db = new DatabaseHelper(this);

        buildings = db.getBuildingData();

        //populate buildingList with all database entries as BuildingItem objects
        for(ArrayList building : buildings) {
            String name = building.get(1).toString();
            String altName = building.get(3).toString();
            String dept = building.get(4).toString();
            BuildingItem bi = new BuildingItem(Integer.parseInt(building.get(2).toString()), name, altName, dept);
            buildingList.add(bi);
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView = setHasFixedSize(true); only if recycler view won't change in size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BuildingAdapter(buildingList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BuildingAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position){
                //changeItem(position, "Clicked");
                passBuildings(position);
                //change color
                //TextView clicked = findViewById(R.id.textView2);
                //clicked.setTextColor(Color.parseColor("#FF0000"));
            }
        });
        //end of old buildingselector

        //  search feature
        buildingSearch = (EditText) findViewById(R.id.editTextSearch);
        buildingSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }

    public void changeItem(int position, String text){
        buildingList.get(position).changeText2(text);
        mAdapter.notifyItemChanged(position);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void passBuildings(int position){
        buildingsToPass.add(buildingList.get(position).getBuilding());
        if(buildingsToPass.size() == 1){
            changeItem(position, "Starting Point");
            TextView title = findViewById(R.id.SelectTitle);
            title.setText("Select a Destination Building");
            buildingSearch.getText().clear();
        }
        if(buildingsToPass.size()==2){
            changeItem(position, "Destination");

            // go to map page
            Intent launchActivity = new Intent(BuildingActivity.this, MapsActivity.class);
            launchActivity.putExtra("places", buildingsToPass);
            startActivity(launchActivity);
        }
    }

    private void filter(String text)
    {
        ArrayList<BuildingItem> filterNames = new ArrayList<>();

        //looping through existing elements
        for(BuildingItem b: buildingList)
        {
            String buildingName = b.getBuilding();
            if(buildingName.toLowerCase().contains(text.toLowerCase()))
                filterNames.add(b);
        }
        mAdapter.filterList(filterNames);
    }
}
