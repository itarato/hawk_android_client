package com.itarato.hawk.task;

import android.os.AsyncTask;

import com.itarato.hawk.ContentListAdapter;
import com.itarato.hawk.ContentLoader;
import com.itarato.hawk.model.ContentListFeedItem;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContentListUpdateTask extends AsyncTask<String, Void, String> {

    private ContentListAdapter contentListAdapter;

    public ContentListUpdateTask(ContentListAdapter contentListAdapter) {
        super();
        this.contentListAdapter = contentListAdapter;
    }

    @Override
    protected String doInBackground(String... params) {
        String URL = params[0];
        ContentLoader loader = new ContentLoader(URL);

        try {
            String contentFeedRaw = loader.load();
            System.out.println(contentFeedRaw);
            return contentFeedRaw;
        } catch (Exception e) {
            System.out.println("JSON related error");
            e.printStackTrace();
        }

        return "";
    }

    @Override
    protected void onPostExecute(String contentFeedRaw) {
        try {
            JSONObject json = new JSONObject(contentFeedRaw);
            final JSONArray contentListJSON = json.getJSONArray("content");

            contentListAdapter.clear();
            System.out.println(contentListJSON);
            System.out.println(contentListJSON.length());

            for (int i = 0; i < contentListJSON.length(); i++) {
                try {
                    JSONObject contentItemJSON = contentListJSON.getJSONObject(i);
                    ContentListFeedItem content = new ContentListFeedItem(
                            contentItemJSON.getInt("id"),
                            contentItemJSON.getString("title"),
                            contentItemJSON.getString("package"),
                            contentItemJSON.getString("image")
                    );
                    contentListAdapter.add(content);
                } catch (Exception e) {
                    System.out.println("Exception when added new elements.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error on JSON interpretation.");
        }

        super.onPostExecute(contentFeedRaw);
    }

}
