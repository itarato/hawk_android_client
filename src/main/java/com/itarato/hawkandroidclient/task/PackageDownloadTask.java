package com.itarato.hawkandroidclient.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.itarato.hawkandroidclient.model.ContentListFeedItem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PackageDownloadTask extends AsyncTask<ContentListFeedItem, Void, Boolean> {

    private static final String LOG_TAG = PackageDownloadTask.class.getSimpleName();
    private final Context context;

    public PackageDownloadTask(Context context) {
        super();
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(ContentListFeedItem... params) {
        Log.i(LOG_TAG, "Package download has been initiated");

        ContentListFeedItem content = params[0];
        final String packageName = String.valueOf(content.getId()) + ".zip";

        try {
            Log.i(LOG_TAG, "Attempt to extract ZIP: " + packageName);

            URL packageURL = new URL(content.getPackageURL());
            InputStream is = packageURL.openStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ZipInputStream zipInputStream = new ZipInputStream(bis);

            ZipEntry ze;
            while ((ze = zipInputStream.getNextEntry()) != null) {
                File extractedFile = new File(context.getFilesDir().getPath(), ze.getName());
                File parentFolder = extractedFile.getParentFile();

                Log.i(LOG_TAG, parentFolder.getPath());
                File fileFullPath = new File(parentFolder.getPath());
                if (!fileFullPath.exists()) {
                    boolean success = fileFullPath.mkdirs();
                    if (!success) {
                        Log.e(LOG_TAG, "File path cannot be created");
                    } else {
                        Log.i(LOG_TAG, "Folder has been created");
                    }
                }

                FileOutputStream fos = new FileOutputStream(extractedFile);
                int count;
                byte[] buffer = new byte[1 << 10];
                while ((count = zipInputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, count);
                }
                Log.i(LOG_TAG, ze.getName() + " has been extracted");
                fos.close();
            }
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
