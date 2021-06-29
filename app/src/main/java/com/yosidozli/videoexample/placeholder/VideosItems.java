package com.yosidozli.videoexample.placeholder;

import com.yosidozli.videoexample.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class VideosItems {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<VideoItem> ITEMS = new ArrayList<VideoItem>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, VideoItem> ITEM_MAP = new HashMap<String, VideoItem>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
            addItem(new VideoItem("a.mp4", R.raw.a,"a"));
            addItem(new VideoItem("b.mp4",R.raw.b,"b"));
            addItem(new VideoItem("c.mp4",R.raw.c,"c"));
            addItem(new VideoItem("d.mp4",R.raw.d,"d"));
//        }
    }

    private static void addItem(VideoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

//    private static VideoItem createPlaceholderItem(int position) {
//        return new VideoItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A placeholder item representing a piece of content.
     */
    public static class VideoItem {
        public final String id;
        public final int resourceId;
        public final String name;

        public VideoItem(String id, int resourceId, String name) {
            this.id = id;
            this.resourceId = resourceId;
            this.name = name;
        }

    }
}