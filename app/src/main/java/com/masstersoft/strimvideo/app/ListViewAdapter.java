package com.masstersoft.strimvideo.app;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<TVChannel> {

    private final ArrayList<TVChannel> tvChannels;
    private final Activity context;

    public ListViewAdapter(Activity context, ArrayList<TVChannel> tvChannels) {
        super(context, R.layout.singleitemview, tvChannels);
        this.context = context;
        this.tvChannels = tvChannels;
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

                    TVChannel tvChannel = tvChannels.get((Integer) viewHolder.btnPlay.getTag());
                    Intent intent = new Intent(context, SingleItemView.class);

                    intent.putExtra("title", tvChannel.getTitle());
                    intent.putExtra("link", tvChannel.getLink());

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
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.tvTitle.setText(tvChannels.get(position).getTitle());
        viewHolder.tvLink.setText(tvChannels.get(position).getLink());

        return view;
    }
}

