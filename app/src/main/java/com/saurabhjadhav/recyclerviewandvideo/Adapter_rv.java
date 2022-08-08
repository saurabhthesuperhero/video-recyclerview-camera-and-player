package com.saurabhjadhav.recyclerviewandvideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class Adapter_rv extends RecyclerView.Adapter<Adapter_rv.MyViewHolder> {
    ArrayList<VideoModel> videoList;
    Context mContext;
    private OnClickListener onClickListener;
    int type = -1;

    public Adapter_rv(ArrayList<VideoModel> videoList, Context mContext, OnClickListener onClickListener) {
        this.videoList = videoList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Adapter_rv.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v, onClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_rv.MyViewHolder holder, int position) {
        VideoModel videoModel = videoList.get(position);
        if (videoModel.getVideoName().isEmpty()) {
            holder.textView.setText("Select Video");

        } else {
            holder.textView.setText(videoModel.getVideoName());
        }

        if (videoModel.getVideoUrl() != null) {
            MediaMetadataRetriever mMMR = new MediaMetadataRetriever();
            mMMR.setDataSource(mContext, videoModel.getVideoUrl());
            Bitmap bmp = mMMR.getFrameAtTime();
            holder.imageView.setImageBitmap(bmp);
        }
        if (videoModel.getOnlineUrl() != null) {
            Log.e("checkme", "onBindViewHolder: "+videoModel.getOnlineUrl() );
            Glide.with(mContext).load(videoModel.getOnlineUrl()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public OnClickListener onClickListener;
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            this.onClickListener = onClickListener;
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = 0;
                    onClickListener.onRowClick(getAdapterPosition(), 0);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = 1;
                    onClickListener.onRowClick(getAdapterPosition(), 1);
                }
            });
        }

        @Override
        public void onClick(View view) {
            int pos = videoList.indexOf(videoList.get(getAdapterPosition()));
            onClickListener.onRowClick(pos, type);

        }
    }

    public interface OnClickListener {
        void onRowClick(int position, int type);
    }
}
