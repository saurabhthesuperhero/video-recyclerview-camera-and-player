package com.saurabhjadhav.recyclerviewandvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

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
        rvList=findViewById(R.id.recyclerview);
        videoList= new ArrayList<>();
        VideoModel videoModel= new VideoModel(null,"");
        VideoModel videoModel1= new VideoModel(null,"");
        VideoModel videoModel2= new VideoModel(null,"");
        videoList.add(videoModel);
        videoList.add(videoModel1);
        videoList.add(videoModel2);
        adapter_rv= new Adapter_rv(videoList, getApplicationContext(), new Adapter_rv.OnClickListener() {
            @Override
            public void onRowClick(int position) {
                dispatchTakeVideoIntent(position);
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvList.setAdapter(adapter_rv);
    }
    private void dispatchTakeVideoIntent(int pos) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            tempPos=pos;
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();

            videoList.get(tempPos).setVideoUrl(videoUri);
            adapter_rv.notifyDataSetChanged();
//            mVideoView.setVideoURI(videoUri);
        }
    }
}