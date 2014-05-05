package cn.yahoo.asxhl2007.roundedimageview;

import android.graphics.Bitmap;

public interface ImageLoader {
    
    void load(String url, ImageLoaderCallback callback);

    public static interface ImageLoaderCallback {
         
        void callback(String url, Bitmap bitmap);
    }
}
