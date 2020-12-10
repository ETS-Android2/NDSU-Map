package com.example.campus_map;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mText3;
    private String mText4;

    public ExampleItem(int imageResource, String text1, String text2, String text3, String text4) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mText1 = text3;
        mText2 = text4;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public String getText3() {
        return mText3;
    }

    public String getText4() {
        return mText4;
    }
}
