package com.saurabhjadhav.recyclerviewandvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {
String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        videoUrl= getIntent().getStringExtra("url");
        VideoView videoView = findViewById(R.id.videoView);
        ProgressBar contentLoadingProgressBar = findViewById(R.id.loader);
        Log.e("checkme", "onCreate: "+videoUrl );
        // Uri object to refer the
        // resource from the videoUrl
        Uri uri = Uri.parse(videoUrl);

        // sets the resource from the
        // videoUrl to the videoView
        videoView.setVideoURI(uri);

        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(this);

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView);

        // sets the media controller to the videoView
        videoView.setMediaController(mediaController);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.setVisibility(View.VISIBLE);
                contentLoadingProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        // starts the video
        videoView.start();

    }
}