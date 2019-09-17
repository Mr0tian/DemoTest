package com.example.daotest.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author tian on 2019/9/16
 */
public class RatingBarActivity extends AppCompatActivity {
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.rating_bar)
    RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        ButterKnife.inject(this);

        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            //当星级评分条的状态改变时触发该方法
            //动态改变图片的透明度
            //5颗星星就代表最大值255
            img.setImageAlpha((int) (rating*255/5));
        });
    }
}
