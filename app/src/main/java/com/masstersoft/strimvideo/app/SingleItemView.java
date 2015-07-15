package com.masstersoft.strimvideo.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.MediaController;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class SingleItemView extends Activity {
    String tvTitle;
    String tvLink;
    VideoView videoout;
    ProgressDialog pDialog;

    TVChannel channel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleviewforvideo);

        Intent i = getIntent();

        tvTitle = i.getStringExtra("title");
        tvLink = i.getStringExtra("link");

        channel = new TVChannel(tvTitle,tvLink);

        TextView tvtitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvlink = (TextView) findViewById(R.id.tvLink);
        videoout = (VideoView)findViewById(R.id.videoout);

        tvtitle.setText(channel.getTitle());
        tvlink.setText(channel.getLink());


        pDialog = new ProgressDialog(SingleItemView.this);
        pDialog.setTitle("Загрузка видео");
        pDialog.setMessage("Буферизация...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        try {
            MediaController mediacontroller = new MediaController(SingleItemView.this);
            mediacontroller.setAnchorView(videoout);
            Uri video = Uri.parse(channel.getLink());
            videoout.setMediaController(mediacontroller);
            videoout.setVideoURI(video);

        } catch (Exception e) {
            //Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoout.requestFocus();
        videoout.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoout.start();
            }
        });

    }
}
