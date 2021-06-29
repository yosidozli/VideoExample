package com.yosidozli.videoexample;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.yosidozli.videoexample.databinding.FragmentPlayVideoBinding;

import org.jetbrains.annotations.NotNull;

public class PlayVideoFragment extends Fragment {


    private SimpleExoPlayer player;
    private FragmentPlayVideoBinding binding;
    private int resourceId;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPlayVideoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         player = new SimpleExoPlayer.Builder(requireContext()).build();
         binding.playerView.setPlayer(player);

    }

    @Override
    public void onResume() {
        super.onResume();
        Uri uri = RawResourceDataSource.buildRawResourceUri(R.raw.a);
        MediaItem mediaItem = MediaItem.fromUri(uri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player = null;
    }
}