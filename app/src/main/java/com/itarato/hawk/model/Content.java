package com.itarato.hawk.model;

public class Content {

    private final int id;
    private final String title;
    private final String packageURL;
    private final String[] pages;

    public Content(int id, String title, String packageURL, String[] pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
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

    public String[] getPages() {
        return pages;
    }

}
