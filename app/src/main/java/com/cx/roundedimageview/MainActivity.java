package com.cx.roundedimageview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cx.uilib.RoundedImageView;

/**
 * @author cx
 */
public class MainActivity extends AppCompatActivity {
    private RoundedImageView mRoundedImageView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoundedImageView = findViewById(R.id.image);
        mImageView = findViewById(R.id.image_view);
//        mImageView.setImageDrawable(new PaintDrawable(Color.GRAY));
//        mImageView.setBackground(new PaintDrawable(Color.RED));

        mRoundedImageView.setSrcRadius(10);
        mRoundedImageView.setImageDrawable(new ColorDrawable(Color.GRAY));

//        mRoundedImageView.setBackgroundRadius(20);
//        mRoundedImageView.setBackground(new ColorDrawable(Color.GRAY));
    }
}
