package com.itarato.hawk.model;

public class ContentListFeedItem {

    private final int id;
    private final String title;
    private final String packageURL;
    private final String imageURL;

    public ContentListFeedItem(int id, String title, String packageURL, String imageURL) {
        this.id = id;
        this.title = title;
        this.packageURL = packageURL;
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

}
