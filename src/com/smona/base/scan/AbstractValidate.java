package com.smona.base.scan;

import java.util.HashMap;

public abstract class AbstractValidate implements IValidate {
    
    protected static HashMap<String, String> RELUTIONMAP = new HashMap<String, String>();
    
    @Override
    public void validate(String path, String fileName) {
        try {
            readZipFile(path, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void readZipFile(String path, String fileName)
            throws Exception;
    
    
    protected void printDetail(String msg) {
        Logger.printDetail(msg);
    }
    
    protected void printReport(String msg) {
        Logger.printReport(msg);
    }
}
