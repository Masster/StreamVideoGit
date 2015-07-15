package com.masstersoft.strimvideo.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class SingleItemView extends Activity {
    String tvTitle;
    String tvLink;
    VideoView videoView;
    ProgressDialog progressDialog;

    TVChannel tvChannel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleviewforvideo);

        Intent intent = getIntent();

        tvTitle = intent.getStringExtra("title");
        tvLink = intent.getStringExtra("link");

        tvChannel = new TVChannel(tvTitle,tvLink);

        TextView tvTitleView = (TextView) findViewById(R.id.tvTitle);
        TextView tvLinkView = (TextView) findViewById(R.id.tvLink);
        videoView = (VideoView)findViewById(R.id.videoout);

        tvTitleView.setText(tvChannel.getTitle());
        tvLinkView.setText(tvChannel.getLink());


        progressDialog = new ProgressDialog(SingleItemView.this);
        progressDialog.setTitle("Загрузка видео");
        progressDialog.setMessage("Буферизация...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();

        try {
            MediaController mc = new MediaController(SingleItemView.this);
            mc.setAnchorView(videoView);
            Uri video = Uri.parse(tvChannel.getLink());
            videoView.setMediaController(mc);
            videoView.setVideoURI(video);

        } catch (Exception e) {
            //Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoView.start();
            }
        });

    }
}
