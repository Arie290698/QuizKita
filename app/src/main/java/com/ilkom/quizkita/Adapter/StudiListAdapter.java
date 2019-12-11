package com.ilkom.quizkita.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.ilkom.quizkita.Model.StudiModel;
import com.ilkom.quizkita.Quiz;
import com.ilkom.quizkita.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudiListAdapter extends ArrayAdapter<StudiModel> {
    Intent intent;
    private Context context;

    public StudiListAdapter(Context context, int resourceId, List<StudiModel> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView tvStudi;
        ImageView ivStudi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        StudiModel tweet = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.studi, null);
            holder = new ViewHolder();
            holder.tvStudi = (TextView) convertView.findViewById(R.id.tvStudi);
            holder.ivStudi = (ImageView) convertView.findViewById(R.id.ivStudi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvStudi.setText(tweet.getName() + " - " + tweet.getTotal() +" Soal");
        Picasso.with(context).load(tweet.getImage()).into(holder.ivStudi);

        final String kode = tweet.getKode();
        final int total = tweet.getTotal();
        final CardView cardView = (CardView) convertView.findViewById(R.id.studie);
        cardView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(context, Quiz.class);
                intent.putExtra("kode", kode);
                intent.putExtra("total", total);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}
