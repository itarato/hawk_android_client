package com.itarato.hawk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.itarato.hawk.model.ContentListFeedItem;
import com.itarato.hawk.task.PackageDownloadTask;

public class ContentListBroadcastReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = ContentListBroadcastReceiver.class.getSimpleName();
    private final ContentListAdapter contentListAdapter;

    ContentListBroadcastReceiver(ContentListAdapter contentListAdapter) {
        super();
        this.contentListAdapter = contentListAdapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG_TAG, "Message received.");
        int pos = intent.getIntExtra("position", -1);
        ContentListFeedItem content = this.contentListAdapter.getItem(pos);
        Log.i(LOG_TAG, content.getPackageURL());

        new PackageDownloadTask(context).execute(content);
    }

}
