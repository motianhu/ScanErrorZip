package com.smona.base.scan;

public class NewsPicValidate extends WordValidate {
    protected int getImageCount(int fileCount) {
        return fileCount - 4;
    }
}
