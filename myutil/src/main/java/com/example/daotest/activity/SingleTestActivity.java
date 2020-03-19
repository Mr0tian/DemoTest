package com.example.daotest.activity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;
import com.example.daotest.wight.GestureSignatureView;

import java.io.File;
import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/12/23
 * 练习签名控件
 */
public class SingleTestActivity extends AppCompatActivity {

    @InjectView(R.id.single)
    GestureSignatureView single;
    @InjectView(R.id.btn_save)
    Button btnSave;
    @InjectView(R.id.btn_clean)
    Button btnClean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_save, R.id.btn_clean})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                //获取内置SD卡路径
                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                //图片文件路径
                String filePath = sdCardPath + File.separator + "sign.png";
                try {
                    single.save(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clean:
                single.clear();
                break;
        }
    }
}
