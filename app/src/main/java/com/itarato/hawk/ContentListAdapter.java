package com.itarato.hawk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itarato.hawk.model.ContentListFeedItem;
import com.itarato.hawk.task.ImageDownloadTask;

import java.net.URI;
import java.util.List;

public class ContentListAdapter extends ArrayAdapter<ContentListFeedItem> {

    private static final String LOG_TAG = ContentListAdapter.class.getSimpleName();

    public ContentListAdapter(Context context, int resource, List<ContentListFeedItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.content_list_item, null);

        Button downloadButton = (Button) v.findViewById(R.id.content_download_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("com.itarato.hawk.CONTENT");
                i.putExtra("position", position);
                getContext().sendBroadcast(i);
            }
        });

        Button viewButton = (Button) v.findViewById(R.id.content_view_button);
        final ContentListFeedItem feedListItem = getItem(position);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ContentViewSliderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                ContentListFeedItem content = feedListItem;
                i.putExtra("id", content.getId());
                getContext().startActivity(i);
            }
        });

        TextView tv = (TextView) v.findViewById(R.id.content_title);
        tv.setText(feedListItem.getTitle());

        String imageURL = feedListItem.getImageURL();
        Log.i(LOG_TAG, "Image will be presented: " + imageURL);
        if (imageURL.length() > 0) {
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            new ImageDownloadTask(imageView).execute(imageURL);
        }

        return v;
    }

}
