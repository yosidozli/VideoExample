package com.yosidozli.videoexample;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yosidozli.videoexample.data.model.VideoEntity;
import com.yosidozli.videoexample.databinding.FragmentVideoBinding;

import java.util.List;


public class VideosRecyclerViewAdapter extends RecyclerView.Adapter<VideosRecyclerViewAdapter.ViewHolder> {

    private final List<VideoEntity> mValues;

    public VideosRecyclerViewAdapter(List<VideoEntity> items) {
        Log.d("RecyclerView.Adapter", "VideosRecyclerViewAdapter:  "+ items.size() +items.get(0).resource);
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        VideoEntity mItem = mValues.get(position);
        holder.title.setText(mItem.name);
        String path = "android.resource://" + holder.itemView.getContext().getPackageName() + "/" + mItem.resource;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        Uri videoUri = Uri.parse(path);
        retriever.setDataSource(holder.itemView.getContext(), videoUri);
        Bitmap bitmap = retriever
                .getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
        Drawable drawable = new BitmapDrawable(holder.itemView.getResources(), bitmap);
        holder.imageView.setImageDrawable(drawable);
        holder.itemView.setOnClickListener(v -> {
            VideoFragmentDirections.ActionVideoFragmentToPlayVideoFragment action =
                    VideoFragmentDirections.actionVideoFragmentToPlayVideoFragment(mItem.resource);
            Navigation.findNavController(holder.itemView).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public ImageView imageView;

        public ViewHolder(FragmentVideoBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            imageView = binding.image;

        }

    }
}