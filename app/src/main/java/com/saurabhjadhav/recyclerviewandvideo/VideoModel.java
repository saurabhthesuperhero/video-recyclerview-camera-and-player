package com.saurabhjadhav.recyclerviewandvideo;

import android.net.Uri;

public class VideoModel {
private Uri videoUrl;
private String videoName;
private String onlineUrl;

    public VideoModel(Uri videoUrl, String videoName,String onlineUrl) {
        this.videoUrl = videoUrl;
        this.videoName = videoName;
        this.onlineUrl = onlineUrl;
    }

    public String getOnlineUrl() {
        return onlineUrl;
    }

    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
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
