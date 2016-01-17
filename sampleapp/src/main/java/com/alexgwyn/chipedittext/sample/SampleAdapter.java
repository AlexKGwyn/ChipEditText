package com.alexgwyn.chipedittext.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Alex on 1/17/16.
 */
public class SampleAdapter extends ArrayAdapter<Name> {

    public SampleAdapter(Context context) {
        super(context, 0);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_name, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Name item = getItem(position);
        holder.image.setImageBitmap(item.getImage(getContext()));
        holder.name.setText(item.getText());
        return convertView;
    }


    private class ViewHolder {
        TextView name;
        ImageView image;

        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

}
