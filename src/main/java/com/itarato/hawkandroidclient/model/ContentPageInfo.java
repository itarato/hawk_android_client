package com.itarato.hawkandroidclient.model;

public class ContentPageInfo {

    private final String zipPath;
    private final String canoncialPath;

    public ContentPageInfo(String zipPath, String canoncialPath) {
        this.zipPath = zipPath;
        this.canoncialPath = canoncialPath;
    }

    public String getZipPath() {
        return zipPath;
    }

    public String getCanoncialPath() {
        return canoncialPath;
    }

}
