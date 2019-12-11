package com.ilkom.quizkita.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ilkom.quizkita.Model.RankModel;
import com.ilkom.quizkita.R;

import java.util.List;

public class RankListAdapter extends ArrayAdapter<RankModel> {
    Intent intent;
    private Context context;

    public RankListAdapter(Context context, int resourceId, List<RankModel> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView Namae, Nilae;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        RankModel tweet = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.rank, null);
            holder = new ViewHolder();
            holder.Namae = (TextView) convertView.findViewById(R.id.Namae);
            holder.Nilae = (TextView) convertView.findViewById(R.id.Nilae);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Namae.setText(tweet.getNama());
        holder.Nilae.setText(tweet.getNilai());

        return convertView;
    }

}
