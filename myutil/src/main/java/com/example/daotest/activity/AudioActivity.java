package com.example.daotest.activity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.bean.Music;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tian on 2019/10/11
 */
public class AudioActivity extends AppCompatActivity {

    private static List<Music> musicList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList = new ArrayList<>();
        List<Music> myMusic = getAllMusic(this);

        for (Music music: myMusic){
            Log.i("TAG","文件名:"+music.name + "  创建时间:"+ music.author + "  文件路径:"+ music.path );
        }
    }

    /***
     * 获取音频的方法
     */
    public static List<Music> getAllMusic (Context context){
        musicList.clear();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        /**
         * public String name;//歌曲名
         * public String author;//歌手
         * public String path;//路径
         *  还可以获取其他信息
         */
        String[] projection = {MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.DATE_ADDED,
                MediaStore.Audio.Media.DATA};
        //seclection 查找指定文件
        //加上后边的参数一块用 如果要查全部的文件, 参数设为null即可,注意两个参数都为null
        //下边这段代码是获取指定 .amr 或者是mp3类型的文件
        //Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Files.FileColumns.DATA + " like ?" ,new String[]{"%.amr"},null);
        //Cursor cursor = context.getContentResolver().query(uri, projection, MediaStore.Files.FileColumns.DATA + " like ? " + " or" +MediaStore.Files.FileColumns.DATA + " like ? ",new String[]{"%.amr","%.mp3"},null);
        Cursor cursor = context.getContentResolver().query(uri, projection, null ,null,null);
        while (cursor.moveToNext()){
            Music music = new Music();
            music.author = cursor.getString(1);
            music.name = cursor.getString(0);
            music.path = cursor.getString(2);
            musicList.add(music);
        }


        return musicList;
    }

}
