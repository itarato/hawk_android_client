package com.itarato.hawk.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

public class ImageDownloadTask extends AsyncTask<String, Void, Void> {

    private static final String LOG_TAG = ImageDownloadTask.class.getSimpleName();
    private Bitmap bitmap;
    private final ImageView imageView;

    public ImageDownloadTask(ImageView imageView) {
        super();
        this.imageView = imageView;
    }

    @Override
    protected Void doInBackground(String... params) {
        // @TODO cache result -> into temp folder.
        try {
            URL imageURL = new URL(params[0]);
            InputStream inputStream = imageURL.openStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            this.bitmap = BitmapFactory.decodeStream(bufferedInputStream);
            bufferedInputStream.close();
            inputStream.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Cannot download image: " + params[0]);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        this.imageView.setImageBitmap(this.bitmap);
    }

}
