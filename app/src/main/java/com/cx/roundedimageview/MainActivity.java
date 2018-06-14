package com.cx.roundedimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.cx.uilib.RoundedImageView;

public class MainActivity extends AppCompatActivity {
  private RoundedImageView mRoundedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoundedImageView=findViewById(R.id.image);
        Glide.with(this).load(R.drawable.city).into(mRoundedImageView);
//        mRoundedImageView.setImageResource(R.drawable.city);
    }
}
