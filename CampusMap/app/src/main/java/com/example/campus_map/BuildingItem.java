package com.example.campus_map;

public class BuildingItem {
    private int mImageResource;
    private String building;
    private String altName;
    private String dept;
    private String info;

    public BuildingItem(int imageResource, String text1, String text2, String text3, String text4) {
        mImageResource = imageResource;
        building = text1;
        altName = text2;
        dept = text3;
        info = text4;
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

    public String getInfo() {
        return info;
    }

    public void changeText2(String text){
        altName = text;
    }
}
