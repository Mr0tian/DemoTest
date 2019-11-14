package com.example.daotest.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.IVideoPlayer;
import org.song.videoplayer.PlayListener;
import org.song.videoplayer.QSVideo;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/10/15
 */
public class FourVideoActivity extends AppCompatActivity {
    @InjectView(R.id.video_play1)
    DemoQSVideoView videoPlay1;
   /* @InjectView(R.id.video_play2)
    DemoQSVideoView videoPlay2;
    @InjectView(R.id.video_play3)
    DemoQSVideoView videoPlay3;
    @InjectView(R.id.video_play4)
    DemoQSVideoView videoPlay4;
*/
    /**
     * 视频地址
     */
    private String[] paths = { "http://cdn.hcloud.xshuai.com//upload/language_resource/30e946be-a3fb-4407-b104-034ec0c1ae09.mov","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4","http://vjs.zencdn.net/v/oceans.mp4", "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4" };
    private String url = "http://cdn.hcloud.xshuai.com//upload/language_resource/30e946be-a3fb-4407-b104-034ec0c1ae09.mov";
    List<DemoQSVideoView> views = new ArrayList<>();
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_video);
        ButterKnife.inject(this);

        initVideo();


    }

    private void initVideo() {
        //设置多个清晰度
        videoPlay1.setUp(QSVideo.Build(url).title("这是标清标题").definition("标清").resolution("标清 720P").build(),
                QSVideo.Build(url).title("这是高清标题").definition("高清").resolution("高清 1080P").build());
        //封面
        //videoPlay.getCoverImageView().setImageResource(R.mipmap.ic_launcher);

        //设置监听
        videoPlay1.setPlayListener(new PlayListener() {
            @Override
            public void onStatus(int status) {
                //播放器的ui状态
                if (status == IVideoPlayer.STATE_AUTO_COMPLETE){

                    videoPlay1.quitWindowFullscreen();//播放完成退出全屏
                }
            }

            @Override
            public void onMode(int mode) {
                //全屏/普通/浮窗..
            }

            @Override
            public void onEvent(int what, Integer... extra) {
                //播放事件 初始化完成/缓冲/出错/点击事件..
            }
        });
        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
        videoPlay1.enterFullMode=3;
        videoPlay1.play();



    }

}
