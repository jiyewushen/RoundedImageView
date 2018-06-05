package com.cx.uilib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 对大分辨的图片无效
 * Created by cx on 2018/6/5.
 */
public class RoundedImageView extends ImageView {
   private Path mPath;
   private RectF mRectF;
   private float radius;

    public RoundedImageView(Context context) {
        super(context);
        init(context,null,0,0);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0,0);
    }

    public RoundedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mPath = new Path();
        this.post(new Runnable() {
            @Override
            public void run() {
                mRectF = new RectF(0, 0, RoundedImageView.this.getWidth(), RoundedImageView.this.getHeight());
            }
        });
        if (attrs==null)return;
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.RoundedImageView,defStyleAttr,defStyleRes);
        try {
           radius= typedArray.getDimension(R.styleable.RoundedImageView_radius,0);
        }finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mRectF != null) {
            mPath.addRoundRect(mRectF, radius, radius, Path.Direction.CW);
            canvas.clipPath(mPath);
        }
        super.onDraw(canvas);
    }

    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }
}
