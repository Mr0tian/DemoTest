package com.example.daotest.activity;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

/**
 * @author tian on 2019/10/11
 */
public class ObjectAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        LinearLayout container = findViewById(R.id.container);
        //添加MyAnimationView组件
        container.addView(new MyAnimationView(this));
    }

    private class MyAnimationView extends View {
        public MyAnimationView(Context context) {
            super(context);
            //加载动画资源
            ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(ObjectAnimActivity.this,R.animator.color_anim);
            colorAnim.setEvaluator(new ArgbEvaluator());
            //对该View本身应用属性动画
            colorAnim.setTarget(this);
            colorAnim.start();
        }
    }
}
