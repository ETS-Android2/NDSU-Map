package com.example.campus_map;

//import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>{
    private static ArrayList<BuildingItem> buildingList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class BuildingViewHolder extends RecyclerView.ViewHolder{
        public ImageView buildingImage;
        public TextView buildingName;
        public TextView buildingAltName;
        public TextView buildingDept;

        public BuildingViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            buildingImage = itemView.findViewById(R.id.buildingImage);
            buildingName = itemView.findViewById(R.id.buildingName);
            buildingAltName = itemView.findViewById(R.id.buildingAltName);
            buildingDept = itemView.findViewById(R.id.buildingDept);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            int id = buildingList.get(position).getID();
                            listener.onItemClick(id-1);
                        }
                    }
                }
            });

        }
    }

    public BuildingAdapter(ArrayList<BuildingItem> buildings){
        buildingList = buildings;
    }

    @Override
    public BuildingViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_item, parent, false);
        BuildingViewHolder evh = new BuildingViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(BuildingViewHolder holder, int position){
        BuildingItem currentItem = buildingList.get(position);

        holder.buildingImage.setImageResource(currentItem.getImageResource());
        holder.buildingName.setText(currentItem.getBuilding());
        holder.buildingAltName.setText(currentItem.getAltName());
        holder.buildingDept.setText(currentItem.getDept());
    }

    @Override
    public int getItemCount(){
        return buildingList.size();
    }

    public void filterList(ArrayList<BuildingItem> building)
    {
        this.buildingList = building;
        this.notifyDataSetChanged();
    }

}

//citation: Coding In Flow's "RecyclerView + CardView Android Studio Tutorial
