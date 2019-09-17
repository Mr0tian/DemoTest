package com.example.daotest.activity;


import android.app.ActionBar;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;


import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @author tian on 2019/9/16
 */
public class PopWindowActivity extends AppCompatActivity {
    PopupWindow popup;
    @InjectView(R.id.btn)
    Button btn;

    boolean isShow = true;
    ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        actionBar = getActionBar();

        ButterKnife.inject(this);



        //加载R.layout.popup对应的界面布局文件
        View root = this.getLayoutInflater().inflate(R.layout.popup, null);
        //创建PopupWindow对象
        popup = new PopupWindow(root, 560, 720);

        btn.setOnClickListener(view->{
            if(isShow){
                //已下拉方式显示
                popup.showAsDropDown(view);
                //将PopupWindow显示在指定位置
                //popup.showAtLocation(findViewById(R.id.btn), Gravity.CENTER,20,20);
                isShow = !isShow;
                actionBar.show();
            }else {
                popup.dismiss();
                isShow = !isShow;
            }
        });
    }




}
