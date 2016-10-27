package com.smona.base.scan;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Logger.init();
        String encode = System.getProperty("file.encoding");
        println(encode);
        String path = System.getProperty("user.dir");
        println(path);
        scanZipFiles(path);
    }

    private static void scanZipFiles(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanZipFiles(file.getAbsolutePath());
            } else {
                String fileName = file.getName();
                if (fileName.endsWith(".zip")) {
                    executeTask(filePath, fileName);
                }
            }
        }
    }

    private static void executeTask(String path, String fileName) {
        String[] splits = fileName.split("-");
        if (splits == null || splits.length != 4) {
            // log
            return;
        }
        String type = splits[1];

        if ("G".equals(type)) {
            IValidate hd = new HDValidate();
            hd.validate(path, fileName);
        } else if ("X".equals(type)) {
            IValidate mood = new MoodValidate();
            mood.validate(path, fileName);
        } else if ("Z".equals(type)) {
            IValidate newsPic = new NewsPicValidate();
            newsPic.validate(path, fileName);
        }
    }
    
    private static void println(String msg) {
        Logger.printDetail(msg);
    }
}
