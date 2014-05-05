package cn.yahoo.asxhl2007.roundedimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import cn.yahoo.asxhl2007.roundedimageview.ImageLoader.ImageLoaderCallback;

public class NetImageView extends ImageView {
    private String url;
    private String lastUrl;

    private AlphaAnimation animation;
    
    private ImageLoader imageLoader;

    public NetImageView(Context context) {
        super( context );
        init();
    }

    public NetImageView(Context context , AttributeSet attrs) {
        super( context, attrs );
        init();
    }

    public NetImageView(Context context , AttributeSet attrs , int defStyle) {
        super( context, attrs, defStyle );
        init();
    }
    
    public void setImageLoader(ImageLoader imageLoader){
        this.imageLoader = imageLoader;
    }

    private void init() {
        animation = new AlphaAnimation( 0, 1 );
        animation.setDuration( 1000 );
    }

    public void setImageUrl(final String url) {
        if (url == null || url.equals( "" ) || url.equals( this.url ) && url.equals( lastUrl )) {
            return;
        }
        this.url = url;
        if ( !url.equals( lastUrl )) {
            setImageBitmap( null );
        }
        clearAnimation();
        
        if(imageLoader == null){
            return;
        }
        imageLoader.load(url, new ImageLoaderCallback() {
			
			@Override
			public void callback(String url, Bitmap bitmap) {
				if (url.equals( NetImageView.this.url )) {
                  if ( !url.equals( lastUrl )) {
                      setImageBitmap( bitmap );
                      lastUrl = url;
                      startAnimation( animation );
                  }
              }
			}
		});
    }
}
