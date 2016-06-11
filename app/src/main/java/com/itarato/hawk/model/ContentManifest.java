package com.itarato.hawk.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentManifest {

    private ContentPageInfo[] pages;

    public static ContentManifest fromJson(JSONObject jsonObject) throws JSONException {
        JSONArray jsonPages = jsonObject.getJSONArray("pages");
        ContentManifest instance = new ContentManifest();
        instance.pages = new ContentPageInfo[jsonPages.length()];
        for (int i = 0; i < jsonPages.length(); i++) {
            ContentPageInfo pageInfo = new ContentPageInfo(
                    jsonPages.getJSONObject(i).getString("zipPath"),
                    jsonPages.getJSONObject(i).getString("canonicalPath")
            );
            instance.pages[i] = pageInfo;
        }
        return instance;
    }

    public ContentPageInfo[] getPages() {
        return pages;
    }
}
