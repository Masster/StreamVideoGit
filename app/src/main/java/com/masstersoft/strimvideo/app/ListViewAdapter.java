package com.masstersoft.strimvideo.app;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<TVChannel> {

    private final ArrayList<TVChannel> list;
    private final Activity context;

    public ListViewAdapter(Activity context, ArrayList<TVChannel> list) {
        super(context, R.layout.singleitemview, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView tvTitle;
        protected TextView tvLink;
        protected Button btnPlay;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.singleitemview, null);

            final ViewHolder viewHolder = new ViewHolder();

            //Log.d("log_tag","In getView position = "+position);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            viewHolder.tvLink = (TextView) view.findViewById(R.id.tvLink);
            viewHolder.btnPlay = (Button)view.findViewById(R.id.btnPlayChannel);

            viewHolder.btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TVChannel tvc = list.get((Integer) viewHolder.btnPlay.getTag());
                    Intent intent = new Intent(context, SingleItemView.class);

                    intent.putExtra("title", tvc.getTitle());
                    intent.putExtra("link", tvc.getLink());

                    context.startActivity(intent);
                }
            });

            view.setTag(viewHolder);
            viewHolder.btnPlay.setTag(position);

        } else {
            view = convertView;
            //Log.d("log_tag","In getView position = "+position);
            ((ViewHolder) view.getTag()).btnPlay.setTag(position);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvLink.setText(list.get(position).getLink());

        return view;
    }
}

