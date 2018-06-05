package com.cx.roundedimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cx.uilib.RoundedImageView;

public class MainActivity extends AppCompatActivity {
  private RoundedImageView mRoundedImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoundedImageView=findViewById(R.id.image);
//        mRoundedImageView.setRadius(30);
        mRoundedImageView.setImageResource(R.drawable.city);
    }
}
