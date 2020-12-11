package com.example.campus_map;

public class BuildingItem {
    private int mImageResource;
    private String building;
    private String altName;
    private String dept;

    public BuildingItem(int imageResource, String name, String alternateName, String department) {
        mImageResource = imageResource;
        building = name;
        altName = alternateName;
        dept = department;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getBuilding() {
        return building;
    }

    public String getAltName() {
        return altName;
    }

    public String getDept() {
        return dept;
    }

    public void changeText2(String text){
        altName = text;
    }
}
