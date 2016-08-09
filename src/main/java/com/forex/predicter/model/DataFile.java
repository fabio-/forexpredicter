package com.forex.predicter.model;

import org.springframework.data.annotation.Id;

public class DataFile
{
    @Id
    private String id;

    private String filename;

    public DataFile(String filename) {
        this.filename = filename;
    }

    public DataFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
