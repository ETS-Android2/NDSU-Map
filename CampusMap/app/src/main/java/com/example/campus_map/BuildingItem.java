package com.example.campus_map;

public class BuildingItem {
    private int mImageResource;
    private String building;
    private String altName;
    private String dept;

    public BuildingItem(int imageResource, String text1, String text2, String text3) {
        mImageResource = imageResource;
        building = text1;
        altName = text2;
        dept = text3;
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
