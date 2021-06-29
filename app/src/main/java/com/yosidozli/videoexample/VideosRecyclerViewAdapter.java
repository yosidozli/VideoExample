package com.yosidozli.videoexample;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yosidozli.videoexample.data.model.VideoEntity;
import com.yosidozli.videoexample.placeholder.VideosItems.VideoItem;
import com.yosidozli.videoexample.databinding.FragmentVideoBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link VideoItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class VideosRecyclerViewAdapter extends RecyclerView.Adapter<VideosRecyclerViewAdapter.ViewHolder> {

    private final List<VideoEntity> mValues;

    public VideosRecyclerViewAdapter(List<VideoEntity> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.title.setText(mValues.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public VideoEntity mItem;
        public ImageView imageView;

        public ViewHolder(FragmentVideoBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            imageView = binding.image;
            String path = "android.resource://" + binding.getRoot().getContext().getPackageName() + "/" + mItem.resource;
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            Uri videoUri = Uri.parse(path);
            retriever.setDataSource(binding.getRoot().getContext(), videoUri);
            Bitmap bitmap = retriever
                    .getFrameAtTime(100000,MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
            Drawable drawable = new BitmapDrawable(binding.getRoot().getResources(), bitmap);
            imageView.setImageDrawable(drawable);
        }

    }
}