package com.itarato.hawk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.itarato.hawk.model.Content;

import java.util.List;

public class ContentListAdapter extends ArrayAdapter<Content> {

    public ContentListAdapter(Context context, int resource, List<Content> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(this.getContext()).inflate(R.layout.content_list_item, null);
        TextView tv = (TextView) v.findViewById(R.id.content_title);
        tv.setText(getItem(position).getTitle());
        return v;
    }

}
