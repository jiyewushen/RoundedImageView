package com.cx.uilib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author cx
 * @date 2018/6/5
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
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, defStyleAttr, defStyleRes);
        try {
            srcRadius = typedArray.getDimension(R.styleable.RoundedImageView_src_radius, 0);
            backgroundRadius = typedArray.getDimension(R.styleable.RoundedImageView_background_radius, 0);
        } finally {
            typedArray.recycle();
        }
        refreshDrawableRadius();
    }

    private void refreshDrawableRadius() {
        if (srcRadius != 0) {
            Drawable srcDrawable = getDrawable();
            if (srcDrawable != null) {
                if (srcDrawable instanceof RoundedBitmapDrawable) {
                    ((RoundedBitmapDrawable) srcDrawable).setCornerRadius(srcRadius);
                } else {
                    setImageDrawable(srcDrawable);
                }
            }
        }
        if (backgroundRadius != 0) {
            Drawable backgroundDrawable = getBackground();
            if (backgroundDrawable != null) {
                if (backgroundDrawable instanceof RoundedBitmapDrawable) {
                    ((RoundedBitmapDrawable) backgroundDrawable).setCornerRadius(backgroundRadius);
                } else if (backgroundDrawable instanceof PaintDrawable) {
                    ((PaintDrawable) backgroundDrawable).setCornerRadius(backgroundRadius);
                } else {
                    setBackground(backgroundDrawable);
                }
            }
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bm);
        roundedBitmapDrawable.setCornerRadius(srcRadius);
        setImageDrawable(roundedBitmapDrawable);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                roundedBitmapDrawable.setCornerRadius(srcRadius);
                super.setImageDrawable(roundedBitmapDrawable);
                return;
            }
        } else if (drawable instanceof ColorDrawable) {
            setScaleType(ScaleType.FIT_XY);
            RoundedBitmapDrawable roundedBitmapDrawable = getRoundedBitmapDrawable((ColorDrawable) drawable, srcRadius);
            super.setImageDrawable(roundedBitmapDrawable);
            return;
        } else if (drawable instanceof PaintDrawable) {
//            it not visible at ImageView when use setImageDrawable
//            PaintDrawable paintDrawable= (PaintDrawable) drawable;
//            paintDrawable.setCornerRadius(srcRadius);
//            super.setImageDrawable(paintDrawable);
            setScaleType(ScaleType.FIT_XY);
            RoundedBitmapDrawable roundedBitmapDrawable = getRoundedBitmapDrawable(new ColorDrawable(((PaintDrawable) drawable).getPaint().getColor()), srcRadius);
            super.setImageDrawable(roundedBitmapDrawable);

            return;
        }
        super.setImageDrawable(drawable);
    }

    @Override
    public void setBackground(Drawable background) {
        if (background == null) {
            return;
        }
        if (background instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) background).getBitmap();
            if (bitmap != null) {
                RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
                roundedBitmapDrawable.setCornerRadius(backgroundRadius);
                super.setBackground(roundedBitmapDrawable);
                return;
            }
        } else if (background instanceof ColorDrawable) {
            PaintDrawable paintDrawable = getPaintDrawable((ColorDrawable) background, backgroundRadius);
            super.setBackground(paintDrawable);
            return;
        } else if (background instanceof PaintDrawable) {
            PaintDrawable paintDrawable = (PaintDrawable) background;
            paintDrawable.setCornerRadius(backgroundRadius);
            super.setBackground(paintDrawable);
            return;
        }
        super.setBackground(background);
    }

    @NonNull
    private RoundedBitmapDrawable getRoundedBitmapDrawable(@NonNull ColorDrawable colorDrawable, float radius) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        canvas.drawColor(colorDrawable.getColor());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCornerRadius(radius);
        return roundedBitmapDrawable;
    }

    @NonNull
    private PaintDrawable getPaintDrawable(@NonNull ColorDrawable colorDrawable, float radius) {
        PaintDrawable paintDrawable = new PaintDrawable(colorDrawable.getColor());
        paintDrawable.setCornerRadius(radius);
        return paintDrawable;
    }

    public void setBackgroundRadius(float backgroundRadius) {
        this.backgroundRadius = backgroundRadius;
        Drawable background = getBackground();
        if (background != null) {
            if (background instanceof RoundedBitmapDrawable) {
                ((RoundedBitmapDrawable) background).setCornerRadius(backgroundRadius);
            } else if (background instanceof PaintDrawable) {
                ((PaintDrawable) background).setCornerRadius(backgroundRadius);
            } else {
                setBackground(background);
            }
        }
    }

    public void setSrcRadius(float srcRadius) {
        this.srcRadius = srcRadius;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            if (drawable instanceof RoundedBitmapDrawable) {
                ((RoundedBitmapDrawable) drawable).setCornerRadius(srcRadius);
            }else {
                setImageDrawable(drawable);
            }
        }
    }
}
