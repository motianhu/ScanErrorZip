package com.smona.base.scan;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class HDValidate extends AbstractValidate {

    static {
        RELUTIONMAP.put("240x213", "240x213");
        RELUTIONMAP.put("312x277", "312x277");
        RELUTIONMAP.put("384x341", "384x341");
        RELUTIONMAP.put("456x405", "456x405");
        RELUTIONMAP.put("528x469", "528x469");
        RELUTIONMAP.put("600x533", "600x533");
        RELUTIONMAP.put("672x597", "672x597");
        RELUTIONMAP.put("744x661", "744x661");
        RELUTIONMAP.put("816x725", "816x725");
        RELUTIONMAP.put("960x854", "960x854");
        RELUTIONMAP.put("1080x960", "1080x960");
        RELUTIONMAP.put("1440x1280", "1440x1280");
        RELUTIONMAP.put("2160x1920", "2160x1920");
        RELUTIONMAP.put("thumbnail", "thumbnail");
    }

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
                            && (filePre + ".jpg").equals(fileName)) {
                        cell.plusFolder();
                        cell.plusImage();
                    } else {
                        printReport("Path: " + path + "; Dir: " + dirName
                                + "; File: " + fileName);
                        break;
                    }
                } else {
                    if ((filePre + ".jpg").equals(name)) {
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

        if (cell.getFolder() != RELUTIONMAP.size()) {
            printReport("relution: " + path + "; file: " + file);
            return;
        }

        if (cell.getFolder() != (cell.getImage() - 1)) {
            printReport("image: " + path + "; file: " + file);
            return;
        }
        printDetail("[path: " + path + "; file: " + file + "] is Ok!");
    }
}
