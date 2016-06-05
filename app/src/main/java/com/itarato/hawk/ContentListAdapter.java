package com.itarato.hawk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itarato.hawk.model.Content;

import java.util.List;

public class ContentListAdapter extends ArrayAdapter<Content> {

    public ContentListAdapter(Context context, int resource, List<Content> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.content_list_item, null);

        Button downloadButton = (Button) v.findViewById(R.id.content_download_button);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Hello" + String.valueOf(position), Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent("com.itarato.hawk.CONTENT");
                i.putExtra("position", position);
                getContext().sendBroadcast(i);
            }
        });

        Button viewButton = (Button) v.findViewById(R.id.content_view_button);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "view", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(getContext(), ContentViewSliderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Content content = getItem(position);
                i.putExtra("id", content.getId());
                getContext().startActivity(i);
            }
        });

        TextView tv = (TextView) v.findViewById(R.id.content_title);
        tv.setText(getItem(position).getTitle());
        return v;
    }

}
