package com.example.daotest.activity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.drawable.IconCompat;

import com.example.daotest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author tian on 2019/9/16
 */
public class ActivityNotification extends AppCompatActivity {

    @InjectView(R.id.btn_send)
    Button btnSend;
    @InjectView(R.id.btn_cancel)
    Button btnCancel;

    public static final int NOTIFICAION_ID = 0x123;
    public static final String CANNEL_ID = "crazyit";
    public NotificationManager nm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.inject(this);

        //获取系统的NotificationManager服务
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //设置通知Cannel的名字
        String name = "测试Channel";
        //创建通知
        NotificationChannel channel = new NotificationChannel(CANNEL_ID,
                name, NotificationManager.IMPORTANCE_HIGH);
        //设置通知的描述信息
        channel.setDescription("测试Channel的描述信息");
        //设置通知出现时的闪光灯
        channel.setLightColor(Color.RED);
        //设置通知出现时震动
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{0, 50, 100, 150});
        //channel.setSound(Uri.parse("android.resource://org.crazyit.ui/R.raw.msg"),null);
        //最后在NotificationManager上
        nm.createNotificationChannel(channel);
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @OnClick({R.id.btn_send, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_cancel:
                del();
                break;
            default:
                break;
        }
    }

    /**
     * 发送通知的方法
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void send()
    {
        // 创建一个启动其他Activity的Intent
        Intent intent = new Intent();
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Person p = new Person.Builder()
                .setName("孙悟空")
                .setIcon(Icon.createWithResource(this, R.drawable.icon_pause))
                .build();
        // 设置通知参与者
        Notification.MessagingStyle messagingStyle = new Notification.MessagingStyle(p);
        // 设置消息标题
        messagingStyle.setConversationTitle("一条新通知");
        // 创建一条消息
        Notification.MessagingStyle.Message message = new Notification.MessagingStyle.Message(
                "恭喜您，您加薪了，工资增加20%!", System.currentTimeMillis(), p);
        // 设置额外的数据
//		message.setData("image/jpeg", Uri.parse("file:///mnt/sdcard/list.png"));  // ②
        // 添加一条消息
        messagingStyle.addMessage(message);
        Notification notify = new Notification.Builder(this, CANNEL_ID)
                // 设置打开该通知，该通知自动消失
                .setAutoCancel(true)
                // 设置通知的图标
                .setSmallIcon(R.drawable.icon_complete)
                // 设置MessagingStyle
                .setStyle(messagingStyle)
                // 设置通知将要启动程序的Intent
                 .setContentIntent(pi)
                .build();
        // 发送通知
        nm.notify(NOTIFICAION_ID, notify);
    }
    /**
     *  为删除通知的按钮的点击事件定义事件处理方法
     */
    public void del()
    {
        // 取消通知
        nm.cancel(NOTIFICAION_ID);
    }
}
