package com.example.campus_map;

public class BuildingItem {
    private int mImageResource;
    private String building;
    private String altName;
    private String dept;
    private int id;

    public BuildingItem(int id, int imageResource, String name, String alternateName, String department) {
        this.id = id;
        mImageResource = imageResource;
        building = name;
        altName = alternateName;
        dept = department;
    }

    public int getID(){return id;}

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
