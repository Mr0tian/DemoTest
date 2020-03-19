package com.example.daotest.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import static com.tencent.stat.common.StatConstants.LOG_TAG;

/**
 * @author tian on 2019/12/26
 */
public class FileNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_receiver_test);

    }


    /**
     * 检查外部存储的的可用性
     */
    /**
     * 读/写检查
     */
    public boolean isExternalStorageWriterable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    /**
     * 只读检查
     */
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



    /**
     * 获取文件
     */
    public File getDir(String allName){
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),allName);
        if (!file.mkdirs()){
            Log.e(LOG_TAG, "Directory not created");
        }

        return file;
    }
}
