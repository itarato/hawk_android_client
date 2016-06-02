package com.itarato.hawk.model;

public class Content {

    private final int id;
    private final String title;
    private final String packageURL;

    public Content(int id, String title, String packageURL) {
        this.id = id;
        this.title = title;
        this.packageURL = packageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getPackageURL() {
        return packageURL;
    }

    public int getId() {
        return id;
    }
}
