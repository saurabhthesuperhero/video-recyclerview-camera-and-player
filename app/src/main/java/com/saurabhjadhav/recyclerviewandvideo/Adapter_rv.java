package com.saurabhjadhav.recyclerviewandvideo;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_rv extends RecyclerView.Adapter<Adapter_rv.MyViewHolder> {
    ArrayList<VideoModel> videoList;
    Context mContext;
    private OnClickListener onClickListener;

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

        if (videoModel.getVideoUrl()!=null){
            MediaMetadataRetriever mMMR = new MediaMetadataRetriever();
            mMMR.setDataSource(mContext, videoModel.getVideoUrl());
            Bitmap bmp = mMMR.getFrameAtTime();
            holder.imageView.setImageBitmap(bmp);
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onRowClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            int pos = videoList.indexOf(videoList.get(getAdapterPosition()));
            onClickListener.onRowClick(pos);

        }
    }

    public interface OnClickListener {
        void onRowClick(int position);
    }
}
