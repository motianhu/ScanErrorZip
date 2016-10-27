package com.smona.base.scan;

public class MoodValidate extends WordValidate {

    protected int getImageCount(int fileCount) {
        return fileCount - 3;
    }
}
