package com.example.daotest.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/20
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        put();
    }
    RemoteViews remoteViews;
    Notification mNotification;
    int NOTIFICATION_ID = 1;
    private void put() {

        NotificationManager notificationManager = (NotificationManager)this.getSystemService(android.content.Context.NOTIFICATION_SERVICE);      ;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
       //自定义布局
        remoteViews = new RemoteViews(getPackageName(), R.layout.view_notification_incense);

        mBuilder.setContent(remoteViews)
                //.setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(true)
                .setSmallIcon(R.drawable.icon_complete);
        mNotification = mBuilder.build();
        mNotification.flags = Notification.FLAG_NO_CLEAR;

        //显示notification
        notificationManager.notify(NOTIFICATION_ID, mNotification);



    }

    // 定义点击通知栏要进入的activity，若想不启动新的activity，
    //将activity设置为 android:launchMode="singleTask"
    public PendingIntent getDefalutIntent(int flags) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(NotificationActivity.this, PutActivity.class), flags);
        return pendingIntent;
    }

}
