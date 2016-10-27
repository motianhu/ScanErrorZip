package com.smona.base.scan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WordValidate extends AbstractValidate {

    static {
        RELUTIONMAP.put("106x190", "106x190");
        RELUTIONMAP.put("160x285", "160x285");
        RELUTIONMAP.put("180x320", "180x320");
        RELUTIONMAP.put("240x427", "240x427");
        RELUTIONMAP.put("270x480", "270x480");
        RELUTIONMAP.put("350x623", "350x623");
        RELUTIONMAP.put("360x640", "360x640");
        RELUTIONMAP.put("480x854", "480x854");
        RELUTIONMAP.put("540x960", "540x960");
        RELUTIONMAP.put("720x1280", "720x1280");
        RELUTIONMAP.put("1080x1920", "1080x1920");
        RELUTIONMAP.put("thumbnail", "thumbnail");
    }

    @Override
    public void readZipFile(String path, String file) throws Exception {
        InputStream in = new BufferedInputStream(new FileInputStream(path
                + File.separator + file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;

        String filePre = file.substring(0, file.length() - 4);

        Cell cell = new Cell();
        cell.setFolder(0);
        cell.setImage(0);

        while ((ze = zin.getNextEntry()) != null) {
            if (ze.isDirectory()) {

            } else {
                String name = ze.getName();
                int fileIndex = name.indexOf("/");
                if (fileIndex != -1) {
                    String dirName = name.substring(0, fileIndex);
                    String fileName = name.substring(fileIndex + 1,
                            name.length());
                    if (RELUTIONMAP.containsKey(dirName)
                            && isCommonFile(filePre, fileName)) {
                        cell.plusFolder();
                        cell.plusImage();
                    } else {
                        printReport("Path: " + path + "; Dir: " + dirName
                                + "; File: " + fileName);
                        break;
                    }
                } else {
                    if (isCommonFile(filePre, name)) {
                        cell.plusImage();
                    } else {
                        printReport("File: " + name);
                        break;
                    }
                }
            }
        }
        zin.closeEntry();
        zin.close();

        if (cell.getFolder() != 2 * RELUTIONMAP.size()) {
            printReport("folder path: " + path + "; file: " + file);
            return;
        }

        if (cell.getFolder() != (getImageCount(cell.getImage()))) {
            printReport("image path: " + path + "; file: " + file);
            return;
        }
        printDetail("[path: " + path + "; file: " + file + "] is Ok!");
    }

    protected int getImageCount(int fileCount) {
        return fileCount;
    }

    private boolean isCommonFile(String filePre, String fileName) {
        return (filePre + ".jpg").equals(fileName)
                || (filePre + "_font.jpg").equals(fileName)
                || (filePre + ".xml").equals(fileName)
                || (filePre + ".properties").equals(fileName);
    }

}
