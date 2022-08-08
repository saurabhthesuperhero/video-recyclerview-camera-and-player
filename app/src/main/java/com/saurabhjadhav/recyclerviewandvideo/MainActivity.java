package com.saurabhjadhav.recyclerviewandvideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Adapter_rv adapter_rv;
    ArrayList<VideoModel> videoList;
    RecyclerView rvList;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    int tempPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.recyclerview);
        videoList = new ArrayList<>();
        VideoModel videoModel = new VideoModel(null, "","https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4");
        VideoModel videoModel1 = new VideoModel(null, "","https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4");
        VideoModel videoModel2 = new VideoModel(null, "","https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4");
        videoList.add(videoModel);
        videoList.add(videoModel1);
        videoList.add(videoModel2);
        adapter_rv = new Adapter_rv(videoList, getApplicationContext(), new Adapter_rv.OnClickListener() {
            @Override
            public void onRowClick(int position,int type) {
                if (type==0)
                {
                    dispatchTakeVideoIntent(position);

                }
                else
                {
                    playVideo(position);

                }
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvList.setAdapter(adapter_rv);
    }

    private void playVideo(int position) {
        Intent intent= new Intent(this,PlayerActivity.class);
        intent.putExtra("url",videoList.get(position).getOnlineUrl());
        this.startActivity(intent);

    }

    private void dispatchTakeVideoIntent(int pos) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            tempPos = pos;
            videoResultLauncher.launch(takeVideoIntent);
        }
    }

    ActivityResultLauncher<Intent> videoResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Uri videoUri = result.getData().getData();
                        videoList.get(tempPos).setVideoUrl(videoUri);
                        adapter_rv.notifyDataSetChanged();
                    }
                }
            });
}