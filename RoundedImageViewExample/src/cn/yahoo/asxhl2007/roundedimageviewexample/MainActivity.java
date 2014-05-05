package cn.yahoo.asxhl2007.roundedimageviewexample;

import cn.yahoo.asxhl2007.roundedimageview.RoundedImageView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        
        RoundedImageView img1 = findView( R.id.img1 );
        img1.setBorderColor( Color.CYAN );
        img1.setBorderWidth( 16 );
        img1.setRoundWidth( 32 );
    }
    
    @SuppressWarnings("unchecked")
    private <V extends View> V findView(int id){
        return (V) findViewById( id );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

}
