package com.saurabhjadhav.recyclerviewandvideo;

import android.net.Uri;

public class VideoModel {
private Uri videoUrl;
private String videoName;

    public VideoModel(Uri videoUrl, String videoName) {
        this.videoUrl = videoUrl;
        this.videoName = videoName;
    }



    public Uri getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(Uri videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
}
