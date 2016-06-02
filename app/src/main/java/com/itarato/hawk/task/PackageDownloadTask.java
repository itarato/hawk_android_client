package com.itarato.hawk.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.itarato.hawk.model.Content;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class PackageDownloadTask extends AsyncTask<Content, Void, Boolean> {

    private static final String LOG_TAG = PackageDownloadTask.class.getSimpleName();
    private final Context context;

    public PackageDownloadTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Content... params) {
        Log.i(LOG_TAG, "Package download has been initiated");

        Content content = params[0];
        final String packageName = String.valueOf(content.getId()) + ".zip";

        try {
            Log.i(LOG_TAG, "Attempt to create file: " + packageName);
            FileOutputStream fileOutputStream = context.openFileOutput(packageName, Context.MODE_PRIVATE);

            URL packageURL = new URL(content.getPackageURL());
            InputStream is = packageURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            int current;
            byte[] data = new byte[50];

            while ((current = bis.read(data, 0, data.length)) != -1) {
                fileOutputStream.write(data, 0, current);
            }

            fileOutputStream.close();
            Log.i(LOG_TAG, "Package download successful");
        } catch (Exception e) {
            Log.e(LOG_TAG, "Cannot open package file");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
