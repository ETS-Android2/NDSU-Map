package com.example.campus_map;
import java.util.ArrayList;

public class BuilingSelector extends AppCompatActivity{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_selector);

        ArrayList<ExampleItem> exampleList = new ArrayList<>;

        //populate exampleList with all database entries as ExampleItem objects

        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView = setHasFixedSize(true); only if recycler view won't change in size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}