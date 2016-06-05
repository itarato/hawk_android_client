package com.itarato.hawk.task;

import android.os.AsyncTask;

import com.itarato.hawk.ContentListAdapter;
import com.itarato.hawk.ContentLoader;
import com.itarato.hawk.model.Content;

import org.json.JSONArray;
import org.json.JSONObject;

public class ContentListUpdateTask extends AsyncTask<Object, Void, String> {

    private ContentListAdapter contentListAdapter;

    public ContentListUpdateTask(ContentListAdapter contentListAdapter) {
        super();
        this.contentListAdapter = contentListAdapter;
    }

    @Override
    protected String doInBackground(Object... params) {
        ContentLoader loader = new ContentLoader();

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
                    JSONArray pagesJSON = contentListJSON.getJSONObject(i).getJSONArray("pages");
                    String[] pages = new String[pagesJSON.length()];
                    for (int j = 0; j < pagesJSON.length(); j++) {
                        pages[j] = pagesJSON.getString(j);
                    }

                    Content content = new Content(
                            contentListJSON.getJSONObject(i).getInt("id"),
                            contentListJSON.getJSONObject(i).getString("title"),
                            contentListJSON.getJSONObject(i).getString("package"),
                            pages
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
