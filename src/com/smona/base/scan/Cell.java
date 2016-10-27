package com.smona.base.scan;

public class Cell {
    private int folder;
    private int image;

    public int getFolder() {
        return folder;
    }

    public void setFolder(int folder) {
        this.folder = folder;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void plusImage() {
        image++;
    }

    public void plusFolder() {
        folder++;
    }
}
