package com.example.daotest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/16
 */
public class ToastTestActivity extends AppCompatActivity {


    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.sv)
    SearchView sv;
    @InjectView(R.id.btn_ok)
    Button btnOk;
    @InjectView(R.id.btn_cancel)
    Button btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_test);
        ButterKnife.inject(this);

        //设置该SearchView默认是否自动缩小为图标
        sv.setIconifiedByDefault(false);
        //设置该searView显示搜索按钮
        sv.setSubmitButtonEnabled(true);
        //设置默认显示的提示文本
        sv.setQueryHint("查找");
        //添加监听
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        //获取系统的NotificationManager服务




    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn_ok, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Toast.makeText(this, "显示信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn2:

                //创建一个Toast提示信息
                Toast toast = new Toast(this);
                //设置toast的显示位置
                toast.setGravity(Gravity.CENTER, 0, 0);
                //创建一个imageView
                ImageView image = new ImageView(this);
                image.setImageResource(R.drawable.icon_pause);
                //创建一个LineLayout 容器
                LinearLayout ll = new LinearLayout(this);
                //向LinearLayout中添加图片,原有的view
                ll.addView(image);
                //创建一个文本
                TextView textView = new TextView(this);
                textView.setText("带图片的提示信息");
                //设置文本框内的颜色
                textView.setTextSize(24f);
                textView.setTextColor(Color.MAGENTA);
                ll.addView(textView);
                //设置Toast显示自定义view
                toast.setView(ll);
                //设置显示时间
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();


                break;

            case R.id.btn_ok:
                break;
            case R.id.btn_cancel:
                break;

            default:
                break;
        }
    }

}
