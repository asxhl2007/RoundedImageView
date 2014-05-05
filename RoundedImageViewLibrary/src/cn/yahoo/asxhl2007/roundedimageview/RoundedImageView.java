package cn.yahoo.asxhl2007.roundedimageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class RoundedImageView extends NetImageView {

    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;

    private Context context;
    
    private Paint paint;
    private int borderColor;
    private int borderWidth = -1;
    private int roundWidth = -1;
    private RectF drawRect;
    private Path drawPath;

    public RoundedImageView(Context context) {
        super( context );
        this.context = context;
        init(null);
    }

    public RoundedImageView(Context context , AttributeSet attrs) {
        super( context, attrs );
        this.context = context;
        init(attrs);
    }

    public RoundedImageView(Context context , AttributeSet attrs , int defStyle) {
        super( context, attrs, defStyle );
        this.context = context;
        init(attrs);
    }

    @SuppressLint("NewApi")
    private void init(AttributeSet attrs) {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType( LAYER_TYPE_SOFTWARE, null );
        }
        borderColor = DEFAULT_BORDER_COLOR;
        if(attrs != null){
        	TypedArray array = context.obtainStyledAttributes(attrs, R.styleable._RoundedImageView);
        	if(array != null){
        		borderColor = array.getColor(R.styleable._RoundedImageView_borderColor, DEFAULT_BORDER_COLOR);
        		borderWidth = array.getDimensionPixelSize(R.styleable._RoundedImageView_borderWidth, -1);
        		roundWidth = array.getDimensionPixelSize(R.styleable._RoundedImageView_roundWidth, -1);
        		array.recycle();
        	}
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getContext().getSystemService( Context.WINDOW_SERVICE );
        windowManager.getDefaultDisplay().getMetrics( displayMetrics );

        paint = new Paint();
        paint.setStyle( Style.STROKE );
        paint.setColor( borderColor );
        paint.setAntiAlias( true );
        paint.setStrokeWidth( borderWidth );
        Xfermode xfermode = new PorterDuffXfermode( PorterDuff.Mode.SRC );
        paint.setXfermode( xfermode );

        drawRect = new RectF();
        drawPath = new Path();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        invalidate();
    }

    public void setRoundWidth(int roundWidth) {
        this.roundWidth = roundWidth;
        invalidate();
    }

    public void setBorderColor(int color) {
        this.borderColor = color;
        paint.setColor( borderColor );
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float borderWidth = this.borderWidth;
        float roundWidth = this.roundWidth;
        if (borderWidth < 0) {
            borderWidth = ( getWidth() + getHeight() ) * .02f;
        }
        paint.setStrokeWidth( borderWidth );
        if (roundWidth < 0) {
            roundWidth = borderWidth * 2;
        }

        drawPath.reset();
//        drawRect.set( 0 + roundWidth / 2, 0 + roundWidth / 2, getWidth() - roundWidth / 2, getHeight() - roundWidth / 2 );
        drawRect.set( 0 + borderWidth / 2, 0 + borderWidth / 2, getWidth() - borderWidth / 2, getHeight() - borderWidth / 2 );
        drawPath.addRoundRect( drawRect, roundWidth, roundWidth, Direction.CCW );

        canvas.save();
        canvas.clipPath( drawPath );
        super.onDraw( canvas );
        canvas.restore();

        canvas.drawRoundRect( drawRect, roundWidth, roundWidth, paint );
    }

}
