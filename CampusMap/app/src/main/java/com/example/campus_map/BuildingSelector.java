package com.example.campus_map;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BuildingSelector extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_selector);



        ArrayList<BuildingItem> exampleList = new ArrayList<>();

        //populate exampleList with all database entries as ExampleItem objects

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView = setHasFixedSize(true); only if recycler view won't change in size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BuildingAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}