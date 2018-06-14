package com.cx.uilib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by cx on 2018/6/5.
 */
public class RoundedImageView extends ImageView {
    private static final String TAG = "RoundedImageView";
    private float srcRadius;
    private float backgroundRadius;

    public RoundedImageView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (attrs == null) return;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, defStyleAttr, defStyleRes);
        try {
            srcRadius = typedArray.getDimension(R.styleable.RoundedImageView_src_radius, 0);
            backgroundRadius=typedArray.getDimension(R.styleable.RoundedImageView_background_radius,0);
        } finally {
            typedArray.recycle();
        }
        if (srcRadius!=0) {
            Drawable srcDrawable = getDrawable();
            if (srcDrawable != null) {
                if (srcDrawable instanceof RoundedBitmapDrawable) {
                    ((RoundedBitmapDrawable) srcDrawable).setCornerRadius(srcRadius);
                } else {
                    setImageDrawable(srcDrawable);
                }
            }
        }
        if (backgroundRadius!=0){
            Drawable backgroundDrawable=getBackground();
            if (backgroundDrawable != null) {
                if (backgroundDrawable instanceof RoundedBitmapDrawable) {
                    ((RoundedBitmapDrawable) backgroundDrawable).setCornerRadius(backgroundRadius);
                } else {
                  setBackground(backgroundDrawable);
                }
            }
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
//        super.setImageBitmap(bm);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bm);
        roundedBitmapDrawable.setCornerRadius(srcRadius);
        setImageDrawable(roundedBitmapDrawable);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        if (drawable == null) return;
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                roundedBitmapDrawable.setCornerRadius(srcRadius);
                super.setImageDrawable(roundedBitmapDrawable);
                return;
            }
        }
        super.setImageDrawable(drawable);
    }

    @Override
    public void setBackground(Drawable background) {
        if (background == null) return;
        if (background instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) background).getBitmap();
            if (bitmap != null) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                roundedBitmapDrawable.setCornerRadius(backgroundRadius);
                super.setImageDrawable(roundedBitmapDrawable);
                return;
            }
        }else if (background instanceof ColorDrawable){
            ColorDrawable colorDrawable= (ColorDrawable) background;
            Canvas canvas=new Canvas();
            Bitmap bitmap=Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
            canvas.setBitmap(bitmap);
            canvas.drawColor(colorDrawable.getColor());
            RoundedBitmapDrawable roundedBitmapDrawable=RoundedBitmapDrawableFactory.create(getResources(),bitmap);
            roundedBitmapDrawable.setCornerRadius(backgroundRadius);
            super.setBackground(roundedBitmapDrawable);
            return;
        }
        super.setBackground(background);
    }

    public void setBackgroundRadius(float backgroundRadius) {
        this.backgroundRadius = backgroundRadius;
        Drawable background=getBackground();
        if (background!=null&&background instanceof RoundedBitmapDrawable){
            ((RoundedBitmapDrawable) background).setCornerRadius(backgroundRadius);
        }
    }

    public void setSrcRadius(float srcRadius) {
        this.srcRadius = srcRadius;
        Drawable drawable=getDrawable();
        if (drawable!=null&&drawable instanceof RoundedBitmapDrawable){
            ((RoundedBitmapDrawable) drawable).setCornerRadius(srcRadius);
        }
    }
}
